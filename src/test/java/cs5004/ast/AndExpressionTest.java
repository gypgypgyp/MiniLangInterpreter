package cs5004.ast;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AndExpressionTest {
  private Expression left1 = new IntLiteral(1);
  private Expression right1 = new BooleanLiteral(false);
  private Expression left2 = new IntLiteral(1);
  private Expression right2 = new BooleanLiteral(false);

  private AndExpression expr1 = new AndExpression(left1, right1);
  private AndExpression expr2 = new AndExpression(left2, right2);
  private AndExpression expr3 = new AndExpression(left1, left2);

  @Test
  void testGetLeft() {
    assertEquals(expr1.getLeft(), new IntLiteral(1));
  }

  @Test
  void testGetRight() {
    assertEquals(expr1.getRight(), new BooleanLiteral(false));
  }

  @Test
  void testEquals() {
    assertEquals(expr1, expr1);
    assertEquals(expr1, expr2);
    assertNotEquals(expr1, expr3);
    assertNotEquals(expr2, new Object());
    assertNotNull(expr1);
    assertNotEquals(expr2, null);
  }

  @Test
  void format() {
    assertEquals(expr1.format(), "and(1, false)");
  }
}