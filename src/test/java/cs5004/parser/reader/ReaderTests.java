package cs5004.parser.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs5004.parser.Posn;
import cs5004.parser.SexpParserException;
import cs5004.parser.sexp.*;
import org.junit.jupiter.api.Test;

public class ReaderTests {
  @Test
  public void testAtoms() throws SexpParserException {
    SexpReader r = SexpReader.makeTestReader("xyz 3 true false");

    assertEquals(
      new Symbol(new Posn(SexpReader.TEST_INPUT_NAME, 1, 0), "xyz"),
      r.read()
    );

    assertEquals(
      new IntLit(new Posn(SexpReader.TEST_INPUT_NAME, 1, 4), 3),
      r.read()
    );

    assertEquals(
      new BoolLit(new Posn(SexpReader.TEST_INPUT_NAME, 1, 6), true),
      r.read()
    );

    assertEquals(
      new BoolLit(new Posn(SexpReader.TEST_INPUT_NAME, 1, 11), false),
      r.read()
    );

    assertEquals(
      new Eof(new Posn(SexpReader.TEST_INPUT_NAME, 1, 16)),
      r.read()
    );
  }

  @Test
  public void testReadList() throws SexpParserException {
    assertEquals(
      new SexpList(
        new Posn(SexpReader.TEST_INPUT_NAME, 1, 0),
        java.util.List.of(
          new Symbol(new Posn(SexpReader.TEST_INPUT_NAME, 1, 1), "x"),
          new IntLit(new Posn(SexpReader.TEST_INPUT_NAME, 1, 3), 3),
          new Symbol(new Posn(SexpReader.TEST_INPUT_NAME, 1, 5), "y")
        )
      ),
      SexpReader.makeTestReader("(x 3 y)").read()
    );
  }

  @Test
  public void testUnterminatedList() {
    assertThrows(
      UnterminatedListException.class,
      () -> SexpReader.makeTestReader("(x 3 y").read()
    );
  }

  @Test
  public void testMismatchedList() {
    assertThrows(
      UnexpectedTokenException.class,
      () -> SexpReader.makeTestReader("(x 3 y])").read()
    );
  }
}
