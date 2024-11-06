package cs5004.parser;


import java.io.Serial;
import java.util.Objects;

/**
 * Source location information for a single token or AST node.  Tracks source
 * name (i.e., filename), line (1-based), and column (0-based).
 */
public record Posn(String source, int line, int column)
  implements java.io.Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  public Posn(String source, int line, int column) {
    this.source = Objects.requireNonNull(source);
    if (line < 1) {
      throw new IllegalArgumentException("line must be positive; got " + line);
    }
    if (column < 0) {
      throw new IllegalArgumentException(
        "column must be non-negative; got " + column
      );
    }
    this.line = line;
    this.column = column;
  }

  @Override
  public String toString() {
    return String.format("%s:%d:%d", source, line, column);
  }
}
