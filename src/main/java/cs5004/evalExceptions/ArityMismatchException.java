package cs5004.evalExceptions;

import java.util.Objects;

/**
 * Exception thrown to indicate an arity mismatch: a function or primitive
 * is applied to the wrong number of arguments.
 */
public class ArityMismatchException extends EvaluationException {
  private final String functionName;
  private final int expectedArity;
  private final int actualArity;

  /**
   * arity mismatch exception
   * @param functionName name of the function
   * @param expectedArity excepted arity
   * @param actualArity actual arity
   */
  public ArityMismatchException(
    String functionName,
    int expectedArity,
    int actualArity
  ) {
    super(
      String.format(
        "arity error: %s expects %d argument%s, got %d",
        functionName,
        expectedArity,
        expectedArity == 1 ? "" : "s",
        actualArity
      )
    );
    this.functionName = Objects.requireNonNull(functionName);
    this.expectedArity = expectedArity;
    this.actualArity = actualArity;
  }
  /**
   * Returns the name of the function that triggered this exception.
   *
   * @return the function name.
   */
  public String getFunctionName() {
    return functionName;
  }
  /**
   * Returns the number of arguments expected by the function.
   *
   * @return the expected number of arguments.
   */
  public int getExpectedArity() {
    return expectedArity;
  }
  /**
   * Returns the number of arguments actually passed to the function.
   *
   * @return the actual number of arguments.
   */
  public int getActualArity() {
    return actualArity;
  }
}
