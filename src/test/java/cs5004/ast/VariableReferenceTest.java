package cs5004.ast;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class VariableReferenceTest {

  private VariableReference ref1 = new VariableReference("x");
  private VariableReference ref2 = new VariableReference("x");
  private VariableReference ref3 = new VariableReference("y");

  @Test
  void getVariableName() {
    assertEquals(ref1.getVariableName(), "x");
  }

  @Test
  void testEquals() {
    assertEquals(ref1, ref2);
    assertNotEquals(ref1, ref3);
    assertNotEquals(ref1, new Object());
  }

  @Test
  void format() {
    assertEquals("x", ref1.format());
  }
}