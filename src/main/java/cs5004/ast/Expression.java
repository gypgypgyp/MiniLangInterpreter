package cs5004.ast;

import cs5004.core.Environment;
import cs5004.core.Value;

/**
 * Base class of the hierarchy representing various kinds of expressions.
 */
public abstract class Expression {

  /**
   * Accept a Visitor.
   * @param visitor visitor to perform computation
   * @param <T> the visitor's return type
   * @return result of visitor's computation
   */
  public abstract <T> T accept(ExpressionVisitor<T> visitor);

  /**
   * Create a string representation of the expression, suitable for
   * printing to users. This method should generate the same form that
   * the parser expects.
   * @return The formatted string representation of the expression.
   */
  public abstract String format();
}
