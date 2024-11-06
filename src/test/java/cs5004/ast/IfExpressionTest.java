package cs5004.ast;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IfExpressionTest {
  private Expression condition1 = new BooleanLiteral(true);
  private Expression consequent1 = new IntLiteral(1);
  private Expression alternative1 = new IntLiteral(0);
  private Expression condition2 = new BooleanLiteral(true);
  private Expression consequent2 = new IntLiteral(1);
  private Expression alternative2 = new IntLiteral(0);

  private IfExpression ifExpr1 = new IfExpression(condition1, consequent1, alternative1);
  private IfExpression ifExpr2 = new IfExpression(condition2, consequent2, alternative2);
  private IfExpression ifExpr3 = new IfExpression(condition1, alternative1, consequent1);

  @Test
  void getCondition() {
    assertEquals(ifExpr1.getCondition(), condition1);
  }

  @Test
  void getConsequent() {
    assertEquals(ifExpr1.getConsequent(), consequent1);
  }

  @Test
  void getAlternative() {
    assertEquals(ifExpr1.getAlternative(), alternative1);
  }

  @Test
  void testEquals() {
    assertEquals(ifExpr1, ifExpr1);
    assertEquals(ifExpr1, ifExpr2);
    assertNotEquals(ifExpr1, ifExpr3);
    assertNotEquals(ifExpr1, new Object());
    assertNotEquals(ifExpr1, null);
  }

  @Test
  void format() {
    assertEquals(ifExpr1.format(), "if (true), 1, 0");
  }
}