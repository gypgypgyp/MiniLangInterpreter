package cs5004.parser.reader;

import cs5004.parser.Posn;
import cs5004.parser.SexpParserException;
import java.io.Serial;

/**
 * Base class for
 */
public abstract class ReaderException extends SexpParserException {
  @Serial
  private static final long serialVersionUID = 1L;

  public ReaderException(String msg, Posn posn) {
    super(msg, posn);
  }
}
