package cs5004.parser.reader;

import cs5004.parser.Posn;
import java.io.Serial;
import java.util.Objects;

/**
 * Exception that signals a list without closing punctuation.
 */
public class UnterminatedListException extends ReaderException {
  @Serial
  private static final long serialVersionUID = 1L;

  public UnterminatedListException(Posn posn) {
    super("unterminated list", Objects.requireNonNull(posn));
  }
}
