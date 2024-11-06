package cs5004.parser.reader;

import java.io.Serial;
import java.util.Objects;
import cs5004.parser.scanner.Scanner;

/**
 * Exception thrown when the reader encounters an unexpected token: generally
 * this is a closing punctuation character -- ')', '}', or ']' -- when
 * we're not in a list, or if we're in a list opened with a different
 * punctuation character.
 */
public class UnexpectedTokenException extends ReaderException {
  @Serial
  private static final long serialVersionUID = 1L;

  /// Unexpected token.
  private final Scanner.Token token;

  /**
   * Construct the exception
   * @param token the unexpected token read from the input
   */
  public UnexpectedTokenException(Scanner.Token token) {
    super(
      String.format("unexpected token %s", token.toString()),
      Objects.requireNonNull(token.posn())
    );
    this.token = token;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    UnexpectedTokenException that = (UnexpectedTokenException) o;
    return Objects.equals(token, that.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), token);
  }
}
