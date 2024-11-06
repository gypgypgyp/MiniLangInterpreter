package cs5004.ast;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BooleanLiteralTest {
  private BooleanLiteral booleanLiteral1 = new BooleanLiteral(false);
  private BooleanLiteral booleanLiteral2 = new BooleanLiteral(true);
  private BooleanLiteral booleanLiteral3 = new BooleanLiteral(true);

  @Test
  void isValue() {
    assertTrue(booleanLiteral2.isValue());
    assertFalse(booleanLiteral1.isValue());
  }

  @Test
  void testEquals() {
    assertEquals(booleanLiteral2, booleanLiteral2);
    assertEquals(booleanLiteral2, booleanLiteral3);
    assertNotEquals(booleanLiteral1, booleanLiteral2);
    assertNotEquals(null, booleanLiteral1);
  }

  @Test
  void format() {
    assertEquals(booleanLiteral1.format(), "false");
  }
}