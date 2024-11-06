package cs5004.ast;

import java.util.Objects;

/**
 * Represent an expression that refers to a variable.
 */
public class VariableReference extends Expression {
  private final String variableName;

  /**
   * Construct a VariableReference expression
   * @param variableName name of the variable to reference
   */
  public VariableReference(String variableName) {
    this.variableName = variableName;
  }

  /**
   * Return the name of the variable being referenced.
   * @return The variable name.
   */
  public String getVariableName() {
    return variableName;
  }

  /**
   * Compare the current VariableReference to another object for equality.
   * @param o The object to compare with the current VariableReference.
   * @return True if the specified object is equal to the current VariableReference, otherwise false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VariableReference that = (VariableReference) o;
    return Objects.equals(variableName, that.variableName);
  }

  /**
   * Accept a visitor
   * @param visitor The visitor to perform computation with this VariableReference
   * @param <T> The visitor's return type
   * @return The result of the visitor's computation
   */
  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  /**
   * Create a formatted string representation of the Variable Reference.
   * The format is the name of the variable being referenced.
   * @return The string representation of the VariableReference.
   */
  @Override
  public String format() {
    return variableName;
  }
}
