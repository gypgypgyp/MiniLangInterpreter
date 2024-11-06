package cs5004.ast;

import java.util.List;
import java.util.Objects;

/**
 * Represent a function-call expression, such as <code>f(3, y, g(x))</code>
 */
public class FunctionCall extends Expression {
  public final String functionName;
  public final List<Expression> arguments;

  /**
   * Construct a function-call expression
   * @param functionName the name of the function
   * @param arguments the argument expressions, in order from left to right.
   *                  Must not be null; can be empty.
   */
  public FunctionCall(String functionName, List<Expression> arguments) {
    this.functionName = functionName;
    this.arguments = arguments;
  }

  /**
   * Return the name of the function being called.
   * @return The name of the function being called.
   */
  public String getFunctionName() {
    return functionName;
  }

  /**
   * Return the list of argument expressions.
   * @return The list of argument expressions.
   */
  public List<Expression> getArguments() {
    return arguments;
  }

  /**
   * Compare the current function-call expression to another object for equality.
   * @param o The object to compare with the current function-call expression.
   * @return True if the specified object is equal to the current function-call expression, otherwise false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FunctionCall that = (FunctionCall) o;
    return Objects.equals(functionName, that.functionName)
      && Objects.equals(arguments, that.arguments);
  }

  /**
   * Accept a visitor
   * @param visitor The visitor to perform computation with this function-call expression
   * @param <T> The visitor's return type
   * @return The result of the visitor's computation
   */
  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  /**
   * Create a formatted string representation of the function-call expression.
   * The format is "function name(argument1, argument2, ...)".
   * @return A string representation of the function-call expression.
   */
  @Override
  public String format() {
    StringBuilder argString = new StringBuilder();
    if (!arguments.isEmpty()) {
      int i;
      for (i = 0; i < arguments.size() - 1; i++) {
        argString.append(arguments.get(i).format()).append(", ");
      }
      argString.append(arguments.get(i).format());
    }
    return functionName + "(" + argString + ")";
  }
}
