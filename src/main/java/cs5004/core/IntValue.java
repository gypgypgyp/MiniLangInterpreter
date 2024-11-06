package cs5004.core;

import java.util.Objects;

/**
 * Represent an integer value.
 */
public class IntValue extends Value {
  private final int value;

  /**
   * Construct a new integer value.
   * @param value the value of the integer value.
   */
  public IntValue(int value) {
    this.value = value;
  }

  /**
   * Return the integer value stored in this object.
   * @return The value of the integer value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Return the string representation of the integer value.
   * The format is "IntValue(value)".
   * @return A string representation of the integer value.
   */
  @Override
  public String toString() {
    return String.format("IntValue(%d)", value);
  }

  /**
   * Format the integer value as a string.
   * @return A string representation of the integer value.
   */
  @Override
  public String format() {
    return String.format("%d", value);
  }

  /**
   * Checks whether this integer value is equal to another object.
   * @param o the object to compare with this integer value.
   * @return true if the other object is an integer value with the same value, otherwise false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntValue intValue = (IntValue) o;
    return value == intValue.value;
  }

  /**
   * Compute the hash code for this integer value based on its value.
   * @return The hash code for this integer value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  /**
   * Return the value of this integer value.
   * @return The integer value.
   */
  @Override
  public int asInteger() { return value; }
}
