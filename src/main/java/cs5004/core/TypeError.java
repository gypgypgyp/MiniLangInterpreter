package cs5004.core;

/**
 * Exception thrown to indicate a type error, as when a primitive, function, or
 * operator is applied to argument(s) of unexpected or incorrect types.
 */
public class TypeError extends RuntimeException {

  /**
   * Construct a new type error exception.
   * @param msg message
   */
  public TypeError(String msg) {
    super(msg);
  }
}
