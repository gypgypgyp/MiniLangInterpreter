package cs5004.primitives;

import cs5004.core.Value;
import java.util.List;

/**
 * Interface for implementations of primitive (i.e., built-in) functions
 * and operators.
 */
public interface Primitive {
  /**
   * Applies the primitive to argument values and returns the result.
   * @param arguments Argument values, in order from left to right
   * @return result of applying the function to the supplied arguments
   * @throws cs5004.evalExceptions.ArityMismatchException if the number
   *   of arguments do not match the primitive's arity
   * @throws cs5004.core.TypeError if any of the arguments do not have
   *   the type expected by the primitive.
   */
  Value apply(List<Value> arguments);
}
