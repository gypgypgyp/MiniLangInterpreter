package cs5004.parser.sexp;

import cs5004.parser.Posn;
/**
 * Represents the "End of File" (EOF) marker as a symbolic expression.
 * This class is typically used to denote the end of input in parsing scenarios.
 */
public class Eof extends Sexp {
  public Eof(Posn p) {
    super(p);
  }

  @Override
  public String unparse() { return "#<eof>"; }

  @Override
  public String toString() {
    return String.format("Eof(%s)", super.fieldsToString());
  }
}
