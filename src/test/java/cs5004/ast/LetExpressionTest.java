package cs5004.ast;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LetExpressionTest {

  private Expression rhs1 = new IntLiteral(5);
  private Expression body1 = new VariableReference("x");
  private Expression rhs2 = new IntLiteral(5);
  private Expression body2 = new VariableReference("x");

  private LetExpression letExpr1 = new LetExpression("x", rhs1, body1);
  private LetExpression letExpr2 = new LetExpression("x", rhs2, body2);
  private LetExpression letExpr3 = new LetExpression("y", rhs1, body1);

  @Test
  void getVarName() {
    assertEquals(letExpr1.getVarName(), "x");
  }

  @Test
  void getRhs() {
    assertEquals(letExpr1.getRhs(), rhs1);
  }

  @Test
  void getBody() {
    assertEquals(letExpr1.getBody(), body1);
  }

  @Test
  void testEquals() {
    assertEquals(letExpr1, letExpr1);
    assertEquals(letExpr1, letExpr2);
    assertNotEquals(letExpr1, letExpr3);
    assertNotEquals(letExpr1, new Object());
    assertNotEquals(letExpr1, null);
  }

  @Test
  void format() {
    assertEquals(letExpr1.format(), "let x = 5 in x");
  }
}