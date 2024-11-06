package cs5004.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Lookup table for names of things that can appear in a program, such as
 * variable values or function definitions.
 * @param <V> The type of the value associated with the names.
 */
public class Environment<V> {
  private final Map<String, V> bindings;
  private final Environment<V> base;

  /**
   * Create a new empty environment.
   */
  public Environment() {
    bindings = new HashMap<>();
    base = null;
  }

  /**
   * Create an environment in which the names are associated with the values, pairwise.
   * The first name is associated with the first value, and so on.
   * @param names the names to add to the environment
   * @param values the corresponding values
   * @throws IllegalArgumentException if names and values have different sizes.
   */
  public Environment(List<String> names, List<V> values) {
    bindings = makeMap(names, values);
    base = null;
  }

  /**
   * Construct a new environment with specified bindings and base environment.
   * @param bindings A map containing the bindings (name-value pairs).
   * @param base The base environment; can be null.
   */
  private Environment(Map<String, V> bindings, Environment<V> base) {
    this.bindings = Objects.requireNonNull(bindings);
    this.base = base;
  }

  /**
   * Creates a new environment, with all entries of the current environment,
   * plus the one specified by the arguments.
   * If the current environment already has a value for name, the new binding
   * will override this in the resulting environment.
   * @param name name of the entry to be added
   * @param value associated value
   * @return a new environment in which name is associated with value, and all
   * other names are as in this environment.
   */
  public Environment<V> extend(String name, V value) {
    return new Environment<>(Map.of(name, value), this);
  }

  /**
   * Construct a map of name-value pairs from the given lists of names and values.
   * @param names  List of names.
   * @param values List of values.
   * @return A map containing the name-value pairs.
   * @throws IllegalArgumentException if the sizes of names and values are different.
   */
  private Map<String, V> makeMap(List<String> names, List<V> values) {
    Objects.requireNonNull(names);
    Objects.requireNonNull(values);

    if (names.size() != values.size()) {
      throw new IllegalArgumentException("names & values must have same size");
    }

    Map<String, V> result = new HashMap<>();

    for (int i = 0; i < names.size(); ++i) {
      Objects.requireNonNull(names.get(i));
      Objects.requireNonNull(values.get(i));
      result.put(names.get(i), values.get(i));
    }
    return result;
  }

  /**
   * Looks up a name in the environment.
   * @param name name to look up
   * @return value associated with name in the environment; null if not found
   */
  public V lookup(String name) {
    V result = bindings.get(name);
    if (result != null) {
      return result;
    } else if (base != null) {
      return base.lookup(name);
    } else {
      return null;
    }
  }
}
