package cs5004.evalExceptions;

import java.util.Objects;

/**
 * Exception indicating that we've tried to call a function that isn't
 * defined.
 */
public class UndefinedFunctionException extends EvaluationException {

  /**
   * Name of the function we've tried to call
   */
  private final String functionName;

  /**
   * Create a new UndefinedFunctionException
   * @param functionName name of the function we tried to call
   */
  public UndefinedFunctionException(String functionName) {
    super("undefined function " + functionName);
    this.functionName = functionName;
  }

  /**
   * Return the name of the function we tried to call
   * @return name of the function we tried to call
   */
  public String getFunctionName() {
    return functionName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UndefinedFunctionException that = (UndefinedFunctionException) o;
    return Objects.equals(functionName, that.functionName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(functionName);
  }

  @Override
  public String toString() {
    return String.format("UndefinedFunctionException(\"%s\")", functionName);
  }
}
