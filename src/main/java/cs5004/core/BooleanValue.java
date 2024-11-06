package cs5004.core;

import java.util.Objects;

/**
 * Represent a boolean value, either <code>true</code> or <code>false</code>.
 */
public class BooleanValue extends Value {
  private final boolean value;

  /**
   * Construct a new boolean value.
   * @param value the boolean value (true or false).
   */
  public BooleanValue(boolean value) {
    this.value = value;
  }

  /**
   * Return the boolean value stored in this object.
   * @return The boolean value (true or false).
   */
  public boolean getValue() {
    return value;
  }

  /**
   * Format the boolean value for printing as the end result of a program
   * @return String representation of the boolean value ("true" or "false").
   */
  @Override
  public String format() {
    return value ? "true" : "false";
  }

  /**
   * Return the string representation of the boolean value.
   * @return A string in the format "BooleanValue(true)" or "BooleanValue(false)".
   */
  @Override
  public String toString() {
    return String.format("BooleanValue(%s)", format());
  }

  /**
   * Check whether this boolean value is equal to another object.
   * @param o the object to compare with this boolean value.
   * @return true if the other object is a boolean value with the same value, otherwise false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BooleanValue that = (BooleanValue) o;
    return value == that.value;
  }

  /**
   * Compute the hash code for this boolean value.
   * @return The hash code for this boolean value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  /**
   * Return the boolean value of this boolean value.
   * This is provided for compatibility with other boolean-handling methods.
   * @return The boolean value (true or false).
   */
  @Override
  public boolean asBoolean() {
    return value;
  }
}
