package cs5004.parser.sexp;

import java.util.Objects;
import cs5004.parser.Posn;

public final class Symbol extends Sexp {
  private final String name;

  public Symbol(Posn posn, String name) {
    super(posn);
    this.name = Objects.requireNonNull(name);
  }

  public String getName() { return name; }

  @Override
  public String unparse() { return name; }

  @Override
  public String toString() {
    return String.format("Symbol(%s, %s)", super.fieldsToString(), name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Symbol symbol = (Symbol) o;
    return Objects.equals(name, symbol.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name);
  }
}
