package cs5004.primitives;

import cs5004.core.IntValue;
import cs5004.core.TypeError;
import cs5004.core.Value;
import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * Implements the Division operator.
 */
public class DivisionOperator extends AbstractPrimitive {

  /**
   * Applies the division operation to the given list of arguments.
   * This method expects exactly two integer arguments and returns their quotient.
   * It throws an IllegalArgumentException when attempting to divide by zero.
   *
   * @param arguments A list of Value objects expected to contain exactly two integers.
   *                  The second integer (divisor) must not be zero.
   * @return IntValue containing the result of the division of the first integer by the second.
   * @throws ArityMismatchException if the expected arity does not match the actual arity.
   * @throws TypeError                if any of the arguments is not an integer.
   * @throws ArithmeticException if the second argument (divisor) is zero.
   */
  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("/", 2, arguments.size());
    int left = arguments.get(0).asInteger();
    int right = arguments.get(1).asInteger();
    if (right == 0) {
      throw new ArithmeticException();
    }
    return new IntValue(left / right);
  }
}
