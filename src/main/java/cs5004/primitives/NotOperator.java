package cs5004.primitives;

import cs5004.core.BooleanValue;
import cs5004.core.TypeError;
import cs5004.core.Value;
import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * Implements the NOT operator.
 */
public class NotOperator extends AbstractPrimitive {

  /**
   * Applies the logical NOT operation to the provided list of arguments.
   * This method expects exactly one argument, which must be a boolean, and negates its value.
   * It returns a BooleanValue representing the negated result.
   *
   * @param arguments A list of Value objects that should contain exactly one boolean value.
   * @return BooleanValue representing the negated (inverted) boolean value.
   * @throws ArityMismatchException if the expected arity does not match the actual arity.
   * @throws TypeError if the argument is not a boolean.
   */
  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("not", 1, arguments.size());
    return new BooleanValue(!(arguments.get(0).asBoolean()));
  }
}
