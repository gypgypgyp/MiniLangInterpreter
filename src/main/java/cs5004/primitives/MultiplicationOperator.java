package cs5004.primitives;

import cs5004.core.IntValue;
import cs5004.core.TypeError;
import cs5004.core.Value;
import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * Implements the multiplication operator.
 */
public class MultiplicationOperator extends AbstractPrimitive {

  /**
   * Applies the multiplication operation to the provided list of arguments.
   * This method expects exactly two integer arguments and calculates their product.
   * It returns an IntValue representing the result of the multiplication.
   *
   * @param arguments A list of Value objects that should contain exactly two integers.
   * @return IntValue representing the product of the two integers.
   * @throws ArityMismatchException if the expected arity does not match the actual arity.
   * @throws TypeError if any of the arguments is not an integer.
   */
  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("*", 2, arguments.size());
    int left = arguments.get(0).asInteger();
    int right = arguments.get(1).asInteger();
    return new IntValue(left * right);
  }
}
