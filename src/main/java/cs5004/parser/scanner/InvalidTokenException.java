package cs5004.parser.scanner;

import java.io.Serial;
import java.util.Objects;
import cs5004.parser.Posn;

/**
 * Exception thrown upon encountering an invalid combination of characters,
 * like '#' right before the end of the file.
 */
public class InvalidTokenException extends ScannerException {
  @Serial
  private static final long serialVersionUID = 1L;

  /// Token string read from input
  private final String token;

  public InvalidTokenException(Posn posn, String token) {
    super(String.format("Invalid token \"%s\"", token), posn);
    this.token = token;
  }

  public String getToken() { return token; }

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
    InvalidTokenException that = (InvalidTokenException) o;
    return Objects.equals(token, that.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), token);
  }
}
