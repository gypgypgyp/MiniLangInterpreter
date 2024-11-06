package cs5004.ast;

import java.util.Objects;

/**
 * Represent a use of the boolean AND operator, such as
 * <code>and(true, false)</code>, with short-circuiting.
 */
public class AndExpression extends Expression {
  private final Expression left;
  private final Expression right;

  /**
   * Construct a new AND expression.
   * @param left AND expression's left-hand argument
   * @param right AND expression's right-hand argument
   */
  public AndExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  /**
   * Returns the left operand.
   * @return left operand
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
   * Compare the current AND expression to another object for equality.
   * @param o The object to compare with the current AND expression.
   * @return True if the specified object is equal to the current AND expression, otherwise false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AndExpression that = (AndExpression) o;
    return Objects.equals(left, that.left)
      && Objects.equals(right, that.right);
  }

  /**
   * Accept a visitor
   * @param visitor The visitor to perform computation with this AND expression
   * @param <T> The visitor's return type
   * @return The result of the visitor's computation
   */
  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  /**
   * Create a formatted string representation of the AND expression.
   * The format is "and(left operand, right operand)".
   * @return The formatted string representation of the AND expression.
   */
  @Override
  public String format() {
    return "and(" + left.format() + ", " + right.format() + ")";
  }
}
