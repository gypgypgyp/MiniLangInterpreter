package cs5004.ast;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IntLiteralTest {
  private IntLiteral literal1 = new IntLiteral(10);
  private IntLiteral literal2 = new IntLiteral(10);
  private IntLiteral literal3 = new IntLiteral(5);

  @Test
  void getValue() {
    assertEquals(literal1.getValue(), 10);
  }

  @Test
  void testEquals() {
    assertEquals(literal1, literal2);
    assertNotEquals(literal1, literal3);
    assertNotEquals(literal1, null);
  }

  @Test
  void format() {
    assertEquals(literal1.format(), "10");
  }
}