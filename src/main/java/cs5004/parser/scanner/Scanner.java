package cs5004.parser.scanner;

import cs5004.parser.Posn;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

/** Performs lexical analysis of character input.
 */
public class Scanner {
  /// Name of the input source, for position information.
  private final String inputName;

  /// Line number of next character to be read; starts at 1.
  private int line;

  /// Column number of next character to be read; starts at 0.
  private int col;

  /// Underlying input source, with one character of lookahead.
  private final LookaheadReader reader;

  /**
   * Represents a single token read from the input.
   *
   * <p>
   *   If the token type is SYMBOL or STRING_LIT, then <code>value</code>
   *   must be a String.  If the token type is INT_LIT, then value must be
   *   an Integer.  If the token type is DOUBLE_LIT, then value must be a
   *   Double.  Otherwise, value must be null.
   * </p>
   *
   * @param type the token's type
   * @param posn source location information for the token's first character
   * @param value the value, if appropriate, of the token.
   */
  public record Token(TokenType type, Posn posn, Object value) {
    public Token {
      Objects.requireNonNull(type);
      Objects.requireNonNull(posn);
      switch (type) {
        case SYMBOL:
          if (!(value instanceof String)) {
            throw new IllegalArgumentException(
              String.format(
                "%s: %s token requires a string value; got %s",
                posn,
                type,
                value
              )
            );
          }
          break;
        case INT_LIT:
          if (!(value instanceof Integer)) {
            throw new IllegalArgumentException(
              String.format(
                "%s: INT_LIT token requires integer value; got %s",
                posn,
                value
              )
            );
          }
          break;
        default:
          if (value != null) {
            throw new IllegalArgumentException(
              String.format(
                "%s: %s token has unexpected value: %s",
                posn,
                type,
                value
              )
            );
          }
          break;
      }
    }

    @Override
    public String toString() {
      if (value instanceof String) {
        return String.format("Token(%s, %s, \"%s\")", type, posn, value);
      } else {
        return String.format("Token(%s, %s, %s)", type, posn, value);
      }
    }
  }

  /// A single token lookahead buffer; null indicates an empty buffer.
  private Token lookaheadToken;

  /**
   * Construct a scanner.
   *
   * @param inputName the name of the input source, for position information
   * @param input source of raw character input.
   */
  public Scanner(String inputName, java.io.Reader input) {
    this.inputName = Objects.requireNonNull(inputName);
    reader = new LookaheadReader(Objects.requireNonNull(input));
    line = 1;
    col = 0;
    lookaheadToken = null;
  }

  public static final String TEST_INPUT_NAME = "test-input";

  /**
   * Construct a scanner that reads from a string value, for unit tests.
   *
   * @param input text data to be read as input
   * @return a newly-created Scanner object
   */
  public static Scanner makeTestScanner(String input) {
    return new Scanner(TEST_INPUT_NAME, new java.io.StringReader(input));
  }

    /**
   * Reads and consumes a single input character, updating line and
   * col.
   * <p>
   * If the next input characters are \r\n, returns a single \n
   * character.
   */
  private int readChar() throws ScannerIOException {
    try {
      int ch = reader.readChar();

      if (ch == '\n') {
        ++line;
        col = 0;
      } else if (ch == '\r') {
        if (reader.peekChar() == '\n') {
          reader.readChar();
          ch = '\n';
        }
        ++line;
        col = 0;
      } else if (ch == '\t') {
        col += 8;
        col -= col % 8;
      } else {
        ++col;
      }

      return ch;
    } catch (IOException e) {
      throw new ScannerIOException(e);
    }
  }

  /**
   * Reads but does not consume a single character from the input.  Does not
   * update source location information.
   * @return the next character to be read from the input; -1 if EOF.
   * @throws ScannerIOException upon an I/O error reading from the underlying
   *   input source.
   */
  private int peekChar() throws ScannerIOException {
    try {
      return reader.peekChar();
    } catch (IOException e) {
      throw new ScannerIOException(e);
    }
  }

  /**
   * Indicates whether the scanner has consumed all available input.
   *
   * @return true if the scanner has reached the end of input; false otherwise.
   * @throws ScannerException upon an I/O error from the underlying input.
   */
  private boolean eof() throws ScannerException {
    try {
      return reader.eof();
    } catch (IOException e) {
      throw new ScannerIOException(e);
    }
  }

  /**
   * Reads, consumes, and returns a single token.
   */
  public Token getToken() throws ScannerException {
    Token result = peekToken();
    lookaheadToken = null;
    return result;
  }

  /**
   * Reads and returns a single token without consuming it.
   * <p>
   *   This method might consume characters from the input source but leaves the
   *   token in the input stream.
   * </p>
   *
   * @throws ScannerException if we need to read a token from the input but
   * cannot successfully do so.
   */
  public Token peekToken() throws ScannerException {
    if (lookaheadToken == null) {
      lookaheadToken = recognizeToken();
    }
    return lookaheadToken;
  }

  /**
   * Does the main work of recognizing a token in the input stream.
   * <p>
   *   Consumes characters from the input stream.
   * </p>
   *
   * @throws ScannerException if there is an error reading from the input or
   * if the input is not a valid token.
   */
  private Token recognizeToken() throws ScannerException {
    while (!eof()) {
      int chCol = col;
      char ch = (char) readChar();

      switch (ch) {
      case '\n':
      case '\r':
      case '\t':
      case ' ':
        // NOP
        break;
      case '(':
        return punctToken(TokenType.L_PAREN, chCol);
      case ')':
        return punctToken(TokenType.R_PAREN, chCol);
      case '{':
        return punctToken(TokenType.L_BRACE, chCol);
      case '}':
        return punctToken(TokenType.R_BRACE, chCol);
      case '[':
        return punctToken(TokenType.L_BRACKET, chCol);
      case ']':
        return punctToken(TokenType.R_BRACKET, chCol);
      case ';':
        handleLineComment();
        break;
      default:
        return handleAtom(ch, chCol);
      }
    }

    return punctToken(TokenType.EOF, col);
  }

  /**
   * Skips over the contents of a line comment.
   */
  private void handleLineComment() throws ScannerException {
    while (true) {
      int ch = readChar();
      if (ch == '\n' || ch == '\r' || ch < 0) {
        return;
      }
    }
  }

  /**
   * Handles reading other kinds of atomic tokens (numeric literals, symbols)
   * from the input.
   * <p>
   *   Broadly, we read until we encounter whitespace or a syntactically
   *   meaningful character (grouping characters, like parentheses; quotes;
   *   hash characters; whitespace; end of file).  We then interpret the token
   *   as an integer literal, a double literal, or a symbol as possible, with
   *   earlier choices taking precedence over later ones.
   * </p>
   * @param firstChar first character in the atom's concrete syntax
   * @param firstCharCol column of first character in the atom
   * @return Token describing the atom
   * @throws ScannerIOException on underlying input/output errors
   */
  private Token handleAtom(char firstChar, int firstCharCol)
      throws ScannerIOException {

    StringBuilder builder = new StringBuilder();
    builder.append(firstChar);

    int ch;
    while ((ch = peekChar()) >= 0) {
      switch (ch) {
        case ' ':
        case '\n':
        case '\r':
        case '\t':
        case '(':
        case ')':
        case '{':
        case '}':
        case '[':
        case ']':
          return processAtomicToken(builder.toString(), firstCharCol);
        default:
          readChar();
          builder.append((char) ch);
          break;
      }
    }
    return processAtomicToken(builder.toString(), firstCharCol);
  }

  /// regular expression describing integer literals.
  private static final Pattern intLitPattern =
    Pattern.compile("^[+-]?\\d+$");

  /**
   * Identify an atom as a symbol, integer, or double and convert to the
   * appropriate Java representation.
   * @param tokenString concrete syntax for the token.
   * @param firstCharCol column number for the concrete syntax's first
   *                     character
   * @return token object describing the atom.
   */
  private Token processAtomicToken(String tokenString, int firstCharCol) {
    if (intLitPattern.matcher(tokenString).matches()) {
      return new Token(
        TokenType.INT_LIT,
        new Posn(inputName, line, firstCharCol),
        Integer.parseInt(tokenString)
      );
    } else {
      return new Token(
        TokenType.SYMBOL,
        new Posn(inputName, line, firstCharCol),
        tokenString
      );
    }
  }

  /**
   * Creates a new token with no associated value and current
   * position.
   */
  private Token punctToken(TokenType t, int col) {
    return new Token(t, new Posn(inputName, line, col), null);
  }
}
