package cs5004.ast;

import java.util.List;
import java.util.Objects;

/**
 * Represent a top-level function definition.
 */
public class Definition {
  private final String name;
  private final List<String> arguments;
  private final Expression body;

  /**
   * Construct a new function definition.
   * @param name the name of the function
   * @param arguments the name of the function's arguments, in order from left
   *                  to right.  Must not be null; can be empty.
   * @param body the function's body expression
   */
  public Definition(String name, List<String> arguments, Expression body) {
    this.name = Objects.requireNonNull(name);
    this.arguments = List.copyOf(Objects.requireNonNull(arguments));
    this.body = Objects.requireNonNull(body);
  }

  /**
   * Return the name of the function.
   * @return The name of the function.
   */
  public String getName() {
    return name;
  }

  /**
   * Return the list of argument names for the function
   * @return The list of argument names for the function.
   */
  public List<String> getArguments() {
    return arguments;
  }

  /**
   * Return the body expression of the function.
   * @return The body expression of the function.
   */
  public Expression getBody() {
    return body;
  }

  /**
   * Compares the current function definition to another object for equality.
   * @param o The object to compare with the current function definition.
   * @return True if the specified object is equal to the current function definition, otherwise false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Definition that = (Definition) o;
    return Objects.equals(name, that.name) && Objects.equals(
      arguments, that.arguments) && Objects.equals(body, that.body);
  }

  /**
   * Generate a hash code for the function definition based on its name, argument list, and body expression.
   * @return A hash code for the function definition.
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, arguments, body);
  }

  /**
   * Return a string representation of the function definition.
   * The format is "Definition(name, arguments, body)".
   * @return A string representation of the function definition.
   */
  @Override
  public String toString() {
    String argString = String.join(", ", arguments);
    return String.format("Definition(%s, %s, %s)", name, argString, body.format());
  }
}
