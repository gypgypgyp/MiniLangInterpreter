package cs5004.parser;

import java.io.Serial;
import java.util.Objects;

public abstract class SexpParserException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  private final Posn posn;

  public SexpParserException(String msg, Posn posn) {
    super(msg);
    this.posn = posn;
  }

  public SexpParserException(String msg, Exception cause) {
    super(msg, cause);
    this.posn = null;
  }

  public Posn getPosn() { return posn; }

  protected String fieldsToString() {
    return Objects.toString(posn);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    // We deliberately do not call super.equals here, as Exception doesn't
    // override the equals method.
    SexpParserException that = (SexpParserException) o;
    return Objects.equals(posn, that.posn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(posn);
  }

  /**
   * Prepends a string representation of the error location to the
   * exception's message
   * @return annotated message
   */
  @Override
  public String getMessage() {
    return posn.toString() + ": " + super.getMessage();
  }
}
