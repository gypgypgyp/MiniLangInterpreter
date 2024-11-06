package cs5004.primitives;

import cs5004.core.IntValue;
import cs5004.core.TypeError;
import cs5004.core.Value;
import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * Implements the Modulo operator.
 */
public class ModuloOperator extends AbstractPrimitive {

  /**
   * Applies the modulo operation to the provided list of arguments.
   * This method expects exactly two integer arguments and calculates
   * the remainder of the division of the first integer by the second.
   * It returns an IntValue containing the result of the modulo operation.
   *
   * @param arguments A list of Value objects that should contain exactly two integers.
   *                  The divisor must not be zero.
   * @return IntValue representing the result of the modulo operation.
   * @throws ArityMismatchException if the expected arity does not match the actual arity.
   * @throws TypeError                if any of the arguments is not an integer.
   * @throws ArithmeticException if the second argument (divisor) is zero.
   */
  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("%", 2, arguments.size());
    int left = arguments.get(0).asInteger();
    int right = arguments.get(1).asInteger();
    if (right == 0) {
      throw new ArithmeticException();
    }
    return new IntValue(left % right);
  }
}
