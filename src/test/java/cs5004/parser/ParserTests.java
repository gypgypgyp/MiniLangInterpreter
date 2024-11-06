package cs5004.parser;

import cs5004.ast.*;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTests {
  @Test
  public void testParseIntLiteral() {
    Assertions.assertEquals(
      new IntLiteral(4),
      Parser.parseExpression("test source", "4")
    );
  }

  @Test
  public void testParseBooleanLiteral() {
    Assertions.assertEquals(
      new BooleanLiteral(true),
      Parser.parseExpression("test source", "true")
    );
  }

  @Test
  public void testVariableReference() {
    Assertions.assertEquals(
      new VariableReference("x"),
      Parser.parseExpression("test source", " x")
    );
  }

  @Test
  public void testParseFunctionCallExpr() {
    Assertions.assertEquals(
      new FunctionCall(
        "f",
        List.of(new IntLiteral(1), new BooleanLiteral(false))
      ),
      Parser.parseExpression("test source", "(f 1 false)")
    );
  }

  @Test
  public void testParseIfExpression() {
    Assertions.assertEquals(
      new IfExpression(
        new FunctionCall("f", List.of()),
        new IntLiteral(0),
        new IntLiteral(1)
      ),
      Parser.parseExpression("test source", "(if (f) 0 1)")
    );
  }

  @Test
  public void testLetExpression() {
    Assertions.assertEquals(
      new LetExpression(
        "x",
        new FunctionCall(
          "f",
          List.of(new VariableReference("y"))
        ),
        new FunctionCall(
          "*",
          List.of(new VariableReference("x"), new VariableReference("x"))
        )
      ),
      Parser.parseExpression("test source", "(let x (f y) (* x x))")
    );
  }

  @Test
  public void testAndExpression() {
    Assertions.assertEquals(
      new AndExpression(
        new FunctionCall(
          "<",
          List.of(
            new VariableReference("x"),
            new IntLiteral(0)
          )
        ),
        new FunctionCall(
          ">",
          List.of(
            new VariableReference("y"),
            new IntLiteral(3)
          )
        )
      ),
      Parser.parseExpression("test source", "(and (< x 0) (> y 3))")
    );

    try {
      Parser.parseExpression("test source", "(and)");
      Assertions.fail("expected parse error");
    } catch (ParseError e) {
      // NOP
    }
  }

  @Test
  public void testOrExpression() {
    Assertions.assertEquals(
      new OrExpression(
        new VariableReference("x"),
        new AndExpression(
          new VariableReference("y"),
          new VariableReference("z")
        )
      ),
      Parser.parseExpression("test source", "(or x (and y z))")
    );


    try {
      Parser.parseExpression("test source", "(or x)");
      Assertions.fail("expected ParseError");
    } catch (ParseError e) {
      // NOP
    }
  }
}
