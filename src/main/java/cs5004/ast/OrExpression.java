package cs5004.ast;

import java.util.Objects;

/**
 * Represent a use of the <code>or</code> operator, such as
 * <code>or(true, false)</code>, with short-circuiting.
 */
public class OrExpression extends Expression {
  private final Expression left;
  private final Expression right;

  /**
   * Construct a new OR expression.
   * @param left OR expression's left-hand operand
   * @param right OR expression's right-hand operand
   */
  public OrExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  /**
   * Return the left operand.
   * @return The left operand.
   */
  public Expression getLeft() {
    return left;
  }

  /**
   * Return the right operand.
   * @return right operand
   */
  public Expression getRight() {
    return right;
  }

  /**
   * Compare the current OR expression to another object for equality.
   * @param o The object to compare with the current OR expression.
   * @return True if the specified object is equal to the current OR expression, otherwise false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrExpression that = (OrExpression) o;
    return Objects.equals(left, that.left) && Objects.equals(
      right, that.right);
  }

  /**
   * Accept a visitor
   * @param visitor The visitor to perform computation with this OR expression
   * @param <T> The visitor's return type
   * @return The result of the visitor's computation
   */
  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  /**
   * Create a formatted string representation of the OR expression.
   * The format is "or(left operand, right operand)".
   * @return The string representation of the OR expression.
   */
  @Override
  public String format() {
    return "or(" + left.format() + ", " + right.format() + ")";
  }
}
