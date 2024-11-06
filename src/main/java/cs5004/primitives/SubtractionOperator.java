package cs5004.primitives;

import cs5004.core.IntValue;
import cs5004.core.TypeError;
import cs5004.core.Value;
import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * Implements the Subtraction operator.
 */
public class SubtractionOperator extends AbstractPrimitive {

  /**
   * Applies the subtraction operation to the provided list of arguments.
   * This method expects exactly two integer arguments and calculates their difference.
   * It returns an IntValue representing the result of the subtraction.
   *
   * @param arguments A list of Value objects that should contain exactly two integers.
   * @return IntValue representing the difference of the two integers.
   * @throws ArityMismatchException if the expected arity does not match the actual arity.
   * @throws TypeError if any of the arguments is not an integer.
   */
  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("-", 2, arguments.size());
    Value leftArg = arguments.get(0);
    Value rightArg = arguments.get(1);
    int left = leftArg.asInteger();
    int right = rightArg.asInteger();
    return new IntValue(left - right);
  }
}
