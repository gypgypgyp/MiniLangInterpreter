package cs5004.ast;

/**
 * Represent a literal integer, such as the constant <code>17</code>.
 */
public class IntLiteral extends Expression {
  private final int value;

  /**
   * Construct a new integer literal.
   * @param value the value of the literal
   */
  public IntLiteral(int value) {
    this.value = value;
  }

  /**
   * Return the integer value of the literal.
   * @return The integer value of the literal.
   */
  public int getValue() {
    return value;
  }

  /**
   * Compare the current IntLiteral to another object for equality.
   * @param o The object to compare with the current IntLiteral.
   * @return True if the specified object is equal to the current IntLiteral; otherwise, false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntLiteral that = (IntLiteral) o;
    return value == that.value;
  }

  /**
   * Accept a visitor
   * @param visitor The visitor to perform computation with this integer literal.
   * @param <T> The visitor's return type
   * @return The result of the visitor's operation.
   */
  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  /**
   * Create a formatted string representation of the integer literal.
   * The format is simply the integer value of the literal as a string.
   * @return The string representation of the integer literal.
   */
  @Override
  public String format() {
    return String.valueOf(value);
  }
}
