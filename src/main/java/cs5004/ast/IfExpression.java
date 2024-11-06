package cs5004.ast;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represent an IF expression, such as <code>if (x > 0), f(x), 0</code>
 */
public class IfExpression extends Expression {
  private final Expression condition;
  private final Expression consequent;
  private final Expression alternative;

  /**
   * Construct an <code>if</code> expression.
   * @param condition the condition expression
   * @param consequent the expression to be evaluated when condition is true
   * @param alternative the expression to be evaluated when condition is false
   */
  public IfExpression(
    Expression condition,
    Expression consequent,
    Expression alternative
  ) {
    this.condition = condition;
    this.consequent = consequent;
    this.alternative = alternative;
  }

  /**
   * Return the condition expression.
   * @return The condition expression.
   */
  public Expression getCondition() {
    return condition;
  }

  /**
   * Return the consequent expression.
   * @return The consequent expression.
   */
  public Expression getConsequent() {
    return consequent;
  }

  /**
   * Return the alternative expression.
   * @return The alternative expression.
   */
  public Expression getAlternative() {
    return alternative;
  }

  /**
   * Compare the current IF expression to another object for equality.
   * @param o The object to compare with the current IF expression.
   * @return True if the specified object is equal to the current IF expression; otherwise, false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IfExpression that = (IfExpression) o;
    return Objects.equals(condition, that.condition)
      && Objects.equals(consequent, that.consequent)
      && Objects.equals(alternative, that.alternative);
  }

  /**
   * Accept a visitor
   * @param visitor The visitor to perform computation with this IF expression;
   * @param <T> The visitor's return type
   * @return The result of the visitor's computation
   */
  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  /**
   * Create a formatted string representation of the IF expression.
   * The format is "if (condition), consequent, alternative".
   * @return The string representation of the IF expression.
   */
  @Override
  public String format() {
    return "if (" + condition.format() + "), " +
        consequent.format() + ", " + alternative.format();
  }
}
