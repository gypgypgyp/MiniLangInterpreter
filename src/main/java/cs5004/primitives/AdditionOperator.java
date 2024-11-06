package cs5004.primitives;

import cs5004.core.IntValue;
import cs5004.core.TypeError;
import cs5004.core.Value;
import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * Implements the Addition operator.
 */
public class AdditionOperator extends AbstractPrimitive {

  /**
   * Applies the addition operation to the given list of arguments.
   * This method expects exactly two integer arguments and returns their sum.
   *
   * @param arguments A list of Value objects expected to contain exactly two integers.
   * @return IntValue containing the sum of the two integers.
   * @throws ArityMismatchException if the expected arity does not match the actual arity.
   * @throws TypeError if any of the arguments is not an integer.
   */
  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("+", 2, arguments.size());
    int left = arguments.get(0).asInteger();
    int right = arguments.get(1).asInteger();
    return new IntValue(left + right);
  }
}
