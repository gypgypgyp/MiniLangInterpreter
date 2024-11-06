package cs5004.ast;

import java.util.Objects;

/**
 * Represent an expression that introduces a new variable definition,
 * such as <code>let x = g(y) in x * x</code>
 */
public class LetExpression extends Expression {
  private final String varName;
  private final Expression rhs;
  private final Expression body;

  /**
   * Construct a new <code>let</code> expression.
   * @param varName the name of the variable being defined
   * @param rhs the expression giving the value of the variable
   * @param body the expression within which the variable is defined
   */
  public LetExpression(String varName, Expression rhs, Expression body) {
    this.varName = varName;
    this.rhs = rhs;
    this.body = body;
  }

  /**
   * Return the name of the variable being defined.
   * @return The name of the variable.
   */
  public String getVarName() {
    return varName;
  }

  /**
   * Return the right-hand side expression that provides the value of the variable.
   * @return The right-hand side expression.
   */
  public Expression getRhs() {
    return rhs;
  }

  /**
   * Return the body expression within which the variable is defined.
   * @return The body expression.
   */
  public Expression getBody() {
    return body;
  }

  /**
   * Compare the current LET expression to another object for equality.
   * @param o The object to compare with the current LetExpression.
   * @return True if the specified object is equal to the current LetExpression; otherwise, false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LetExpression that = (LetExpression) o;
    return Objects.equals(varName, that.varName)
      && Objects.equals(rhs, that.rhs) && Objects.equals(body,
      that.body);
  }

  /**
   * Accept a visitor
   * @param visitor The visitor to perform computation with this LET expression
   * @param <T> The visitor's return type
   * @return The result of the visitor's computation
   */
  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  /**
   * Create a formatted string representation of the LET expression.
   * The format is "let variable name = RHS expression in body".
   * @return The string representation of the LET expression.
   */
  @Override
  public String format() {
    return "let " + varName + " = " + rhs.format() + " in " + body.format();
  }
}
