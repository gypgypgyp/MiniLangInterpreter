package cs5004.primitives;

import cs5004.core.Value;
import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * Abstract class defining common operations used by many individual primitives.
 */
public abstract class AbstractPrimitive implements Primitive {
  @Override
  public abstract Value apply(List<Value> arguments);

  /**
   * Arity-check operation.
   * @param primitiveName Name of function we're checking
   * @param expectedArity expected number of arguments
   * @param actualArity number of arguments actually provided
   * @throws ArityMismatchException if the expected arity does not match the actual arity.
   */
  protected void arityCheck(
      String primitiveName,
      int expectedArity,
      int actualArity
  ) {
    if (expectedArity != actualArity) {
      throw new ArityMismatchException(
          primitiveName,
          expectedArity,
          actualArity
      );
    }
  }
}
