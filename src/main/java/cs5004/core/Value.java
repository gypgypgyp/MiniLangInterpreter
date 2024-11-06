package cs5004.core;

/**
 * Ancestor of classes that represent different kinds of values.
 * A "value", in this context, is anything that could be the result of evaluating an expression.
 */
public abstract class Value {

  /**
   * Format the value suitable for printing as the end result of a program
   * @return String representation of the value
   */
  public abstract String format();

  /**
   * Return the value as a Java integer. The default implementation
   * always throws a TypeError; subclasses should override when appropriate.
   * @return the underlying value as a Java int
   * @throws TypeError if the value does not represent an int
   */
  public int asInteger() {
    throw new TypeError("Expected int; got " + format());
  };

  /**
   * Return the value as a Java boolean. The default implementation
   * always throws a TypeError; subclasses should override when appropriate.
   * @return the underlying value as a Java boolean
   * @throws TypeError if the value does not represent a boolean
   */
  public boolean asBoolean() {
    throw new TypeError("Expected boolean; got " + format());
  }
}
