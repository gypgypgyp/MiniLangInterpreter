package cs5004.parser;
/**
 * Represents an error that occurs during the parsing process. This exception
 * is thrown when the parser encounters syntactic or semantic errors in the input,
 * providing details about the location (position) in the source code where the error was detected.
 */
public class ParseError extends RuntimeException {
  private final Posn posn;
  public ParseError(Posn p, String msg) {
    super(p.toString() + ": " + msg);
    posn = p;
  }
  public Posn getPosn() {
    return posn;
  }
}
