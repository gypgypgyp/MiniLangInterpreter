package cs5004.primitives;

import cs5004.core.BooleanValue;
import cs5004.core.IntValue;
import cs5004.core.TypeError;
import cs5004.evalExceptions.ArityMismatchException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimitiveTests {

  private final PrimitiveTable primitives;

  public PrimitiveTests() {
    primitives = new PrimitiveTable();
  }

  @Test
  public void testPrimitiveLookup() {
    Assertions.assertNotNull(primitives.lookup("not"));
    Assertions.assertNull(primitives.lookup("noSuchPrimitive"));
  }

  @Test
  public void testNotPrimitive() {
    Primitive not = primitives.lookup("not");

    Assertions.assertEquals(
        new BooleanValue(true),
        not.apply(List.of(new BooleanValue(false)))
    );

    Assertions.assertEquals(
        new BooleanValue(false),
        not.apply(List.of(new BooleanValue(true)))
    );

    try {
      not.apply(new ArrayList<>());
      Assertions.fail("expected exception");
    } catch (ArityMismatchException e) {
      // NOP
    }

    try {
      not.apply(List.of(new IntValue(3)));
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }
  }
}