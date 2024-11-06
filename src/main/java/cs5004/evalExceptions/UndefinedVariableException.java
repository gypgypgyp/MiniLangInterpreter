package cs5004.evalExceptions;

/**
 * Exception to indicate that we've encountered a reference to a variable that
 * isn't defined.
 */
public class UndefinedVariableException extends EvaluationException {

  /**
   * Name of the undefined variable
   */
  private final String variableName;

  /**
   * Create a new UndefinedVariableException.
   * @param variableName name of the undefined variable.
   */
  public UndefinedVariableException(String variableName) {
    super("Undefined variable: " + variableName);
    this.variableName = variableName;
  }

  public String getVariableName() {
    return variableName;
  }
}
