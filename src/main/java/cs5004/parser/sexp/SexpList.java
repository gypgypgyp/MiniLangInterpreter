package cs5004.parser.sexp;

import cs5004.parser.Posn;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class SexpList extends Sexp {
  /// Items in the list.  Immutable, never null; might be empty.
  /// Elements are also never null.
  public final List<Sexp> items;

  public SexpList(Posn posn, List<Sexp> items) {
    super(posn);
    this.items = List.copyOf(Objects.requireNonNull(items));
    for (Sexp item : this.items) {
      Objects.requireNonNull(item);
    }
  }

  /// Returns the items in this list, as an immutable Java list.
  public List<Sexp> getItems() { return items; }

  @Override
  public String unparse() {
    return items.stream()
        .map(Sexp::unparse)
        .collect(Collectors.joining(" ", "(", ")"));
  }

  @Override
  public String toString() {
    return String.format("SexpList(%s, %s)",
        super.fieldsToString(),
        items.stream()
            .map(Sexp::toString)
            .collect(Collectors.joining(", ", "{", "}"))
    );
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
    SexpList list = (SexpList) o;
    return Objects.equals(items, list.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), items);
  }
}
