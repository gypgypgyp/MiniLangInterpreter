package cs5004.primitives;

import cs5004.core.BooleanValue;
import cs5004.core.TypeError;
import cs5004.core.Value;
import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * Implements the Equal operator.
 */
public class EqualsOperator extends AbstractPrimitive {

  /**
   * Applies the equality comparison operation to the provided list of arguments.
   * This method expects exactly two integer arguments and checks if they are equal.
   * It returns a BooleanValue indicating the result of the comparison.
   *
   * @param arguments A list of Value objects that should contain exactly two integers.
   * @return BooleanValue(true) if the two integers are equal, BooleanValue(false) otherwise.
   * @throws ArityMismatchException if the expected arity does not match the actual arity.
   * @throws TypeError if any of the arguments is not an integer.
   */
  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("==", 2, arguments.size());
    int left = arguments.get(0).asInteger();
    int right = arguments.get(1).asInteger();
    return new BooleanValue(left == right);
  }
}
