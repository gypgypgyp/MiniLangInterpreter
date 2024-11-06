package cs5004.parser.sexp;

import cs5004.parser.Posn;
import java.util.Objects;

/**
 * Root of the s-expression class hierarchy.
 */
public abstract class Sexp {
  private final Posn posn;
  /**
   * Constructs an S-expression with a specified position in the source code.
   *
   * @param p the position of this expression within the source code, must not be null.
   * @throws NullPointerException if the provided position is null.
   */
  public Sexp(Posn p) {
    posn = Objects.requireNonNull(p);
  }
  /**
   * Gets the position of this s-expression in the source code.
   *
   * @return the position object associated with this s-expression.
   */
  public Posn getPosn() { return posn; }
  /**
   * Provides a string representation of the essential fields of this s-expression.
   * This method is used internally for generating part of the output in toString methods
   * in derived classes.
   *
   * @return a string describing the fields of this s-expression.
   */
  protected String fieldsToString() {
    return posn.toString();
  }

  /// Returns a string containing equivalent concrete syntax to this
  /// s-expression.
  /**
   * Converts this s-expression to its equivalent concrete syntax.
   * This method must be implemented by all concrete subclasses to provide
   * a string representation in the concrete syntax of the language.
   *
   * @return a string containing the concrete syntax equivalent of this s-expression.
   */
  public abstract String unparse();
  /**
   * Compares this s-expression with another object for equality. This method returns true
   * if the other object is of the same class and has a matching position.
   *
   * @param o the object to be compared for equality with this s-expression.
   * @return true if the specified object is equal to this s-expression, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sexp sexp = (Sexp) o;
    return Objects.equals(posn, sexp.posn);
  }
  /**
   * Computes the hash code for this s-expression based on its position.
   *
   * @return the hash code for this s-expression.
   */
  @Override
  public int hashCode() {
    return Objects.hash(posn);
  }
}
