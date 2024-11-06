package cs5004.primitives;

import cs5004.core.BooleanValue;
import cs5004.core.IntValue;
import cs5004.core.TypeError;
import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArithmeticOperatorTests {

  private final PrimitiveTable primitives;

  public ArithmeticOperatorTests() {
    primitives = new PrimitiveTable();
  }

  @Test
  public void testArithmeticOperatorsOnGoodArguments() {
    Primitive add = primitives.lookup("+");
    Primitive sub = primitives.lookup("-");
    Primitive mult = primitives.lookup("*");
    Primitive div = primitives.lookup("/");
    Primitive mod = primitives.lookup("mod");

    Assertions.assertEquals(
        new IntValue(4),
        add.apply(List.of(new IntValue(1), new IntValue(3)))
    );

    Assertions.assertEquals(
        new IntValue(-1),
        sub.apply(List.of(new IntValue(3), new IntValue(4)))
    );

    Assertions.assertEquals(
        new IntValue(12),
        mult.apply(List.of(new IntValue(2), new IntValue(6)))
    );

    Assertions.assertEquals(
        new IntValue(3),
        div.apply(List.of(new IntValue(17), new IntValue(5)))
    );

    Assertions.assertEquals(
        new IntValue(2),
        mod.apply(List.of(new IntValue(17), new IntValue(5)))
    );
  }

  @Test
  public void TestArithmeticOperatorArityError() {
    try {
      primitives.lookup("+")
          .apply(List.of(new IntValue(3)));
      Assertions.fail("expected exception");
    } catch (ArityMismatchException e) {
      // NOP
    }

    try {
      primitives.lookup("-")
          .apply(List.of(new IntValue(3)));
      Assertions.fail("expected exception");
    } catch (ArityMismatchException e) {
      // NOP
    }

    try {
      primitives.lookup("*")
          .apply(List.of(new IntValue(3)));
      Assertions.fail("expected exception");
    } catch (ArityMismatchException e) {
      // NOP
    }

    try {
      primitives.lookup("/")
          .apply(List.of(new IntValue(3)));
      Assertions.fail("expected exception");
    } catch (ArityMismatchException e) {
      // NOP
    }

    try {
      primitives.lookup("mod")
          .apply(List.of(new IntValue(3)));
      Assertions.fail("expected exception");
    } catch (ArityMismatchException e) {
      // NOP
    }
  }

  @Test
  public void TestArithmeticOperatorTypeError() {
    try {
      primitives.lookup("+")
          .apply(List.of(new IntValue(3), new BooleanValue(true)));
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }

    try {
      primitives.lookup("-")
          .apply(List.of(new IntValue(3), new BooleanValue(true)));
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }

    try {
      primitives.lookup("*")
          .apply(List.of(new IntValue(3), new BooleanValue(true)));
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }

    try {
      primitives.lookup("/")
          .apply(List.of(new IntValue(3), new BooleanValue(true)));
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }

    try {
      primitives.lookup("mod")
          .apply(List.of(new IntValue(3), new BooleanValue(true)));
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }
  }

  @Test
  public void testArithmeticOperatorArithmeticException() {
    try {
      primitives.lookup("/")
          .apply(List.of(new IntValue(3), new IntValue(0)));
      Assertions.fail("expected exception");
    } catch (ArithmeticException e) {
      // NOP
    }

    try {
      primitives.lookup("mod")
          .apply(List.of(new IntValue(3), new IntValue(0)));
      Assertions.fail("expected exception");
    } catch (ArithmeticException e) {
      // NOP
    }
  }
}
