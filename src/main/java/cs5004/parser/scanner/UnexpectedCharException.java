package cs5004.parser.scanner;

import java.io.Serial;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import cs5004.parser.Posn;

/**
 * Exception thrown to indicate an unexpected character, such as a non-
 * supported character after a hash sign.
 */
public final class UnexpectedCharException extends ScannerException {
  @Serial
  private static final long serialVersionUID = 1L;

  private final char unexpectedChar;

  private final Set<Character> expectedChars;

  public UnexpectedCharException(
      Posn posn,
      char unexpectedChar,
      char... expectedChars
  ) {
    super(
      String.format(
        "%s: unexpected character '%c'",
          Objects.requireNonNull(posn),
        unexpectedChar
      ),
      posn
    );
    this.unexpectedChar = unexpectedChar;

    Set<Character> expectedCharSet = new HashSet<>();
    for (char c : expectedChars) {
      expectedCharSet.add(c);
    }
    this.expectedChars = Set.copyOf(expectedCharSet);
  }

  public char getUnexpectedChar() { return unexpectedChar; }

  public Set<Character> getExpectedChars() { return expectedChars; }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof UnexpectedCharException otherExn)) {
      return false;
    }

    return super.equals(otherExn)
      && unexpectedChar == otherExn.unexpectedChar;
  }

  @Override
  public String toString() {
    return String.format(
      "UnexpectedCharException(%s, '%c', %s)",
      super.fieldsToString(),
      unexpectedChar,
      expectedChars.stream()
        .sorted()
        .map(c -> Character.toString(c))
        .collect(Collectors.joining(", ", "[", "]"))
    );
  }
}
