package cs5004.parser.scanner;

import java.io.IOException;
import java.io.Serial;
import java.util.Objects;

/**
 * Wraps an IOException in a ScannerException.
 */
public final class ScannerIOException extends ScannerException {
  @Serial
  private static final long serialVersionUID = 1L;

  private final IOException e;

  public ScannerIOException(IOException e) {
    super("I/O error during tokenization", e);
    this.e = e;
  }

  @Override
  public String toString() {
    return String.format("ScannerIOException(%s)", e);
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
    ScannerIOException that = (ScannerIOException) o;
    return Objects.equals(e, that.e);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), e);
  }
}
