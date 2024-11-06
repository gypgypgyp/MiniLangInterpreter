package cs5004.primitives;

import java.util.HashMap;
import java.util.Map;

/**
 * Lookup table for primitive operations.
 */
public class PrimitiveTable {

  private final Map<String, Primitive> primitives;

  /**
   * Private constructor to prevent instantiation.
   */
  public PrimitiveTable() {
    primitives = new HashMap<>();

    primitives.put("not", new NotOperator());

    primitives.put("==", new EqualsOperator());
    primitives.put("!=", new NotEqualsOperator());
    primitives.put("<", new LessThanOperator());
    primitives.put("<=", new LessThanOrEqualOperator());
    primitives.put(">", new GreaterThanOperator());
    primitives.put(">=", new GreaterThanOrEqualOperator());

    primitives.put("+", new AdditionOperator());
    primitives.put("-", new SubtractionOperator());
    primitives.put("*", new MultiplicationOperator());
    primitives.put("/", new DivisionOperator());
    primitives.put("mod", new ModuloOperator());

  }

  /**
   * Looks up a primitive function or operator by name.
   * @param name name of the function to look up
   * @return Primitive object defining function, or null if not defined.
   */
  public Primitive lookup(String name) {
    return primitives.get(name);
  }

  private static void initializePrimitives() {
  }
}
