package cs5004.parser.reader;

import cs5004.parser.Posn;
import cs5004.parser.SexpParserException;
import cs5004.parser.scanner.Scanner;
import cs5004.parser.scanner.TokenType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import cs5004.parser.sexp.*;

/**
 * Reads s-expressions.
 */
public class SexpReader {
  private final Scanner scanner;

  public SexpReader(Scanner scanner) {
    this.scanner = Objects.requireNonNull(scanner);
  }

  public SexpReader(String inputName, java.io.Reader input) {
    this.scanner = new Scanner(inputName, input);
  }

  public static final String TEST_INPUT_NAME = Scanner.TEST_INPUT_NAME;

  public static SexpReader makeTestReader(String input) {
    return new SexpReader(Scanner.makeTestScanner(input));
  }

  public Sexp read() throws SexpParserException {
    while (true) {
      Scanner.Token token = scanner.getToken();
      switch (token.type()) {
        case L_PAREN: return readList(token.posn(), TokenType.R_PAREN);
        case R_PAREN: throw new UnexpectedTokenException(token);
        case L_BRACE: return readList(token.posn(), TokenType.R_BRACE);
        case R_BRACE: throw new UnexpectedTokenException(token);
        case L_BRACKET: return readList(token.posn(), TokenType.R_BRACKET);
        case R_BRACKET: throw new UnexpectedTokenException(token);
        case SYMBOL:
          return readSymbol(token.posn(), (String) token.value());
        case INT_LIT:
          return new IntLit(token.posn(), (Integer) token.value());
        case EOF:
          return new Eof(token.posn());
      }
    }
  }

  private Sexp readSymbol(Posn posn, String sourceText) {
    if (sourceText.equals("true")) {
      return new BoolLit(posn, true);
    } else if (sourceText.equals("false")) {
      return new BoolLit(posn, false);
    } else {
      return new Symbol(posn, sourceText);
    }
  }

  private Sexp readList(Posn openPosn, TokenType closeToken)
    throws SexpParserException {

    List<Sexp> elements = new ArrayList<>();

    while (true) {
      Scanner.Token token = scanner.peekToken();
      if (token.type() == closeToken) {
        scanner.getToken();  // consume the close character
        return new SexpList(openPosn, elements);
      }
      if (
        token.type() == TokenType.R_PAREN ||
        token.type() == TokenType.R_BRACE ||
        token.type() == TokenType.R_BRACKET
      ) {
        // separate exception for mismatched list?
        throw new UnexpectedTokenException(token);
      }
      if (token.type() == TokenType.EOF) {
        throw new UnterminatedListException(openPosn);
      }

      elements.add(read());
    }
  }
}
