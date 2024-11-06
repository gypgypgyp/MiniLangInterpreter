package cs5004.ast;

import java.util.Objects;

/**
 * Represent a literal boolean value -- i.e., the constants.
 * Either <code>true</code> or <code>false</code>.
 */
public class BooleanLiteral extends Expression {
  private final boolean value;

  /**
   * Construct a new boolean literal expression.
   * @param value the value of the literal expression
   */
  public BooleanLiteral(boolean value) {
    this.value = value;
  }

  /**
   * Return the boolean value of the literal expression.
   * @return The boolean value of the literal expression,
   *         either <code>true</code> or <code>false</code>.
   */
  public boolean isValue() {
    return value;
  }

  /**
   * Compares the current boolean literal to another object for equality.
   * @param o The object to compare with the current boolean literal.
   * @return True if the specified object is equal to the current boolean literal, otherwise false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BooleanLiteral that = (BooleanLiteral) o;
    return value == that.value;
  }

  /**
   * Accept a visitor
   * @param visitor The visitor to perform computation with this literal boolean value.
   * @param <T> The visitor's return type
   * @return The result of the visitor's operation.
   */
  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  /**
   * Create a formatted string representation of the boolean literal expression.
   * @return Either "true" or "false".
   */
  @Override
  public String format() {
    return value ? "true" : "false";
  }
}
