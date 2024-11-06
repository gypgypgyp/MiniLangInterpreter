package cs5004.ast;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrExpressionTest {

  private Expression left1 = new BooleanLiteral(true);
  private Expression right1 = new BooleanLiteral(false);
  private Expression left2 = new BooleanLiteral(true);
  private Expression right2 = new BooleanLiteral(false);
  private Expression left3 = new BooleanLiteral(false);
  private Expression right3 = new BooleanLiteral(true);

  private OrExpression orExpr1 = new OrExpression(left1, right1);
  private OrExpression orExpr2 = new OrExpression(left2, right2);
  private OrExpression orExpr3 = new OrExpression(left3, right3);

  @Test
  void getLeft() {
    assertEquals(orExpr1.getLeft(), left1);
  }

  @Test
  void getRight() {
    assertEquals(orExpr1.getRight(), right1);
  }

  @Test
  void testEquals() {
    assertEquals(orExpr1, orExpr1);
    assertEquals(orExpr1, orExpr2);
    assertNotEquals(orExpr1, orExpr3);
    assertNotEquals(orExpr1, new Object());
    assertNotEquals(orExpr1, null);
  }

  @Test
  void format() {
    assertEquals(orExpr1.format(), "or(true, false)");
  }
}