package cs5004.parser.sexp;

import cs5004.parser.Posn;
import java.util.Objects;
/**
 * Represents a boolean literal in an abstract syntax tree for a programming language.
 * This class holds a boolean value and extends {@link Sexp}, an abstract class for symbolic expressions.
 */
public final class BoolLit extends Sexp {
  private final boolean value;
  /**
   * Constructs a new boolean literal with a specified position and value.
   *
   * @param posn the position of the boolean literal in the source code.
   * @param value the boolean value of this literal.
   */
  public BoolLit(Posn posn, boolean value) {
    super(posn);
    this.value = value;
  }
  /**
   * Returns the value of this boolean literal.
   *
   * @return the boolean value.
   */
  public boolean getValue() { return value; }
  /**
   * Unparses this boolean literal into a standard Scheme representation.
   *
   * @return a string representation of this boolean literal in Scheme syntax.
   */
  @Override
  public String unparse() {
    return value ? "#t" : "#f";
  }
  /**
   * Provides a string representation of this boolean literal.
   *
   * @return a string in the format "BoolLit(position, value)" where position is the
   *         result of {@code super.fieldsToString()} and value is the boolean state.
   */
  @Override
  public String toString() {
    return String.format("BoolLit(%s, %s)", super.fieldsToString(), value);
  }
  /**
   * Compares this boolean literal to another object for equality. The result is {@code true}
   * if and only if the argument is not {@code null}, is a {@code BoolLit} object, and has
   * the same value as this object.
   *
   * @param o the object to compare with this {@code BoolLit} for equality.
   * @return {@code true} if the given object represents an equivalent {@code BoolLit};
   *         {@code false} otherwise.
   */
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
    BoolLit boolLit = (BoolLit) o;
    return value == boolLit.value;
  }
  /**
   * Returns a hash code for this boolean literal.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), value);
  }
}
