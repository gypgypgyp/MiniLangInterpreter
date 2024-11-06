package cs5004.primitives;

import cs5004.core.BooleanValue;
import cs5004.core.IntValue;
import cs5004.core.TypeError;
import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RelationalOperatorTests {

  private final PrimitiveTable primitives;

  public RelationalOperatorTests() {
    primitives = new PrimitiveTable();
  }

  @Test
  public void testRelationalOperatorsOnGoodArguments() {
    Primitive eq = primitives.lookup("==");
    Primitive ne = primitives.lookup("!=");
    Primitive lt = primitives.lookup("<");
    Primitive le = primitives.lookup("<=");
    Primitive gt = primitives.lookup(">");
    Primitive ge = primitives.lookup(">=");

    Assertions.assertEquals(
        new BooleanValue(false),
        eq.apply(List.of(new IntValue(3), new IntValue(4)))
    );

    Assertions.assertEquals(
        new BooleanValue(true),
        eq.apply(List.of(new IntValue(3), new IntValue(3)))
    );

    Assertions.assertEquals(
        new BooleanValue(true),
        ne.apply(List.of(new IntValue(3), new IntValue(4)))
    );

    Assertions.assertEquals(
        new BooleanValue(false),
        ne.apply(List.of(new IntValue(3), new IntValue(3)))
    );

    Assertions.assertEquals(
        new BooleanValue(true),
        lt.apply(List.of(new IntValue(3), new IntValue(4)))
    );

    Assertions.assertEquals(
        new BooleanValue(false),
        lt.apply(List.of(new IntValue(3), new IntValue(3)))
    );

    Assertions.assertEquals(
        new BooleanValue(false),
        lt.apply(List.of(new IntValue(4), new IntValue(3)))
    );

    Assertions.assertEquals(
        new BooleanValue(true),
        le.apply(List.of(new IntValue(3), new IntValue(4)))
    );

    Assertions.assertEquals(
        new BooleanValue(true),
        le.apply(List.of(new IntValue(3), new IntValue(3)))
    );

    Assertions.assertEquals(
        new BooleanValue(false),
        le.apply(List.of(new IntValue(4), new IntValue(3)))
    );

    Assertions.assertEquals(
        new BooleanValue(false),
        gt.apply(List.of(new IntValue(3), new IntValue(4)))
    );

    Assertions.assertEquals(
        new BooleanValue(false),
        gt.apply(List.of(new IntValue(4), new IntValue(4)))
    );

    Assertions.assertEquals(
        new BooleanValue(true),
        gt.apply(List.of(new IntValue(4), new IntValue(3)))
    );

    Assertions.assertEquals(
        new BooleanValue(false),
        ge.apply(List.of(new IntValue(3), new IntValue(4)))
    );

    Assertions.assertEquals(
        new BooleanValue(true),
        ge.apply(List.of(new IntValue(4), new IntValue(4)))
    );

    Assertions.assertEquals(
        new BooleanValue(true),
        ge.apply(List.of(new IntValue(4), new IntValue(3)))
    );
  }

  @Test
  public void testRelationalOperatorArityMismatch() {
    try {
      primitives.lookup("==").apply(List.of(new IntValue(3)));
      Assertions.fail("expected exception");
    } catch (ArityMismatchException e) {
      // NOP
    }

    try {
      primitives.lookup(">=").apply(List.of(new IntValue(3)));
      Assertions.fail("expected exception");
    } catch (ArityMismatchException e) {
      // NOP
    }

    try {
      primitives.lookup("<=").apply(List.of(new IntValue(3)));
      Assertions.fail("expected exception");
    } catch (ArityMismatchException e) {
      // NOP
    }

    try {
      primitives.lookup(">").apply(List.of(new IntValue(3)));
      Assertions.fail("expected exception");
    } catch (ArityMismatchException e) {
      // NOP
    }

    try {
      primitives.lookup("<").apply(List.of(new IntValue(3)));
      Assertions.fail("expected exception");
    } catch (ArityMismatchException e) {
      // NOP
    }

    try {
      primitives.lookup("!=").apply(List.of(new IntValue(3)));
      Assertions.fail("expected exception");
    } catch (ArityMismatchException e) {
      // NOP
    }
  }

  @Test
  public void testRelationalOperatorTypeError() {
    try {
      primitives.lookup("==").apply(
          List.of(new IntValue(3), new BooleanValue(false))
      );
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }

    try {
      primitives.lookup("!=").apply(
          List.of(new IntValue(3), new BooleanValue(false))
      );
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }

    try {
      primitives.lookup("<=").apply(
          List.of(new IntValue(3), new BooleanValue(false))
      );
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }

    try {
      primitives.lookup(">=").apply(
          List.of(new IntValue(3), new BooleanValue(false))
      );
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }

    try {
      primitives.lookup("<").apply(
          List.of(new IntValue(3), new BooleanValue(false))
      );
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }

    try {
      primitives.lookup(">").apply(
          List.of(new IntValue(3), new BooleanValue(false))
      );
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }
  }
}
