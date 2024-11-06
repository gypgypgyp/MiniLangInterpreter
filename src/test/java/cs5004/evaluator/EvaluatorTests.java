package cs5004.evaluator;

import cs5004.ast.*;
import cs5004.core.BooleanValue;
import cs5004.core.Environment;
import cs5004.core.IntValue;
import cs5004.core.TypeError;
import cs5004.core.Value;
import cs5004.evalExceptions.ArityMismatchException;
import cs5004.evalExceptions.UndefinedFunctionException;
import cs5004.evalExceptions.UndefinedVariableException;
import cs5004.parser.Parser;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EvaluatorTests {
  @Test
  public void testVariableReference() {
    Assertions.assertEquals(
      new IntValue(4),
      evaluateExpr("x", new Environment<Value>().extend("x", new IntValue(4)))
    );

    try {
      evaluateExpr("x", new Environment<>());
      Assertions.fail("expected exception");
    } catch (UndefinedVariableException e) {
      Assertions.assertEquals("x", e.getVariableName());
    }
  }

  @Test
  public void testFunctionCall() {
    Assertions.assertEquals(
      new IntValue(3),
      evaluateExpr("(+ 1 2)", new Environment<>())
    );

    Assertions.assertEquals(
      new IntValue(3),
      evaluateExpr(
        "(f)",
        new Environment<Definition>().extend(
          "f",
          new Definition("f", List.of(), new IntLiteral(3))
        ),
          new Environment<>()
      )
    );

    Assertions.assertEquals(
      new IntValue(12),
      evaluateExpr(
        "(f 3 4)",
        new Environment<Definition>().extend(
          "f",
          new Definition(
            "f",
            List.of("x", "y"),
            Parser.parseExpression("test", "(* x y)")
          )
        ),
          new Environment<>()
      )
    );
  }

  @Test
  public void testIfExpression() {
    Assertions.assertEquals(
      new IntValue(1),
      evaluateExpr(
        "(if (> x 0) 1 2)",
        new Environment<Value>().extend("x", new IntValue(3))
      )
    );

    Assertions.assertEquals(
      new IntValue(2),
      evaluateExpr(
        "(if (> x 0) 1 2)",
        new Environment<Value>().extend("x", new IntValue(-2))
      )
    );

    try {
      evaluateExpr(
        "(if x 1 2)",
        new Environment<Value>().extend("x", new IntValue(45))
      );
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }
  }

  @Test
  public void testRecursiveFunctionCall() {
    Assertions.assertEquals(
      new IntValue(720),
      evaluateExpr(
        "(fact 6)",
          new Environment<>(
              List.of("fact"),
              List.of(
                  new Definition(
                      "fact",
                      List.of("x"),
                      Parser.parseExpression(
                          "test",
                          "(if (== x 0) 1 (* x (fact (- x 1))))"
                      )
                  )
              )
          ),
          new Environment<>()
      )
    );
  }

  @Test
  public void testFunctionCallErrors() {
    try {
      evaluateExpr(
        "(f 1)",
        new Environment<Definition>().extend(
          "f",
          new Definition(
            "f",
            List.of("x", "y"),
            new IntLiteral(3)
          )
        ),
          new Environment<>()
      );
      Assertions.fail("expected arity error");
    } catch (ArityMismatchException e) {
      // NOP
    }

    try {
      evaluateExpr(
        "(f 3)",
          new Environment<>(),
          new Environment<>()
      );
      Assertions.fail("expected undefined-function error");
    } catch (UndefinedFunctionException e) {
      // NOP
    }
  }

  @Test
  public void testAndExpression() {
    Assertions.assertEquals(
      new BooleanValue(true),
      evaluateExpr("(and true true)", new Environment<>())
    );

    Assertions.assertEquals(
      new BooleanValue(false),
      evaluateExpr("(and true false)", new Environment<>())
    );

    // should evaluate cleanly -- short-circuiting means that we never
    // evaluate the second argument
    Assertions.assertEquals(
      new BooleanValue(false),
      evaluateExpr("(and false (/ 3 0))", new Environment<>())
    );

    // Here, though, we do have to evaluate the second argument, so we
    // expect to fail with an exception.
    try {
      evaluateExpr("(and true (/ 3 0))", new Environment<>());
      Assertions.fail("expected exception");
    } catch (ArithmeticException e) {
      // NOP
    }

    try {
      evaluateExpr("(and 1 2)", new Environment<>());
      Assertions.fail("expected type error");
    } catch (TypeError e) {
      // NOP;
    }

    Assertions.assertEquals(
        new BooleanValue(false),
        evaluateExpr("(and false 2)", new Environment<>())
    );
  }

  @Test
  public void testOrExpression() {
    Assertions.assertEquals(
      new BooleanValue(true),
      evaluateExpr("(or false true)", new Environment<>())
    );

    Assertions.assertEquals(
      new BooleanValue(false),
      evaluateExpr("(or false false)", new Environment<>())
    );

    // should evaluate cleanly -- short-circuiting means that we never
    // evaluate the second argument
    Assertions.assertEquals(
      new BooleanValue(true),
      evaluateExpr("(or true (/ 3 0))", new Environment<>())
    );

    // Here, though, we do have to evaluate the second argument, so we
    // expect to fail with an exception.
    try {
      evaluateExpr("(or false (/ 3 0))", new Environment<>());
      Assertions.fail("expected exception");
    } catch (ArithmeticException e) {
      // NOP
    }

    try {
      evaluateExpr("(or 1 2)", new Environment<>());
      Assertions.fail("expected type error");
    } catch (TypeError e) {
      // NOP;
    }

    Assertions.assertEquals(
        new BooleanValue(true),
        evaluateExpr("(or true 2)", new Environment<>())
    );
  }

  @Test
  public void testLetExpression() {
    Assertions.assertEquals(
      new IntValue(14),
      evaluateExpr(
        "(let x 7 (* x 2))",
          new Environment<>()
      )
    );

    // and test variable shadowing
    Assertions.assertEquals(
      new IntValue(12),
      evaluateExpr(
        "(let x 3 (* x y))",
          new Environment<>(
              List.of("x", "y"),
              List.of(new IntValue(1), new IntValue(4))
          )
      )
    );
  }

  private Value evaluateExpr(
    String exprSource,
    Environment<Value> variableBindings
  ) {
    return evaluateExpr(
        exprSource,
        new Environment<>(),
        variableBindings
    );
  }

  private Value evaluateExpr(
      String exprSource,
      Environment<Definition> definitions,
      Environment<Value> variableBindings
  ) {
    return Parser.parseExpression("test data", exprSource).accept(
        new Evaluator(
            definitions,
            variableBindings
        )
    );
  }
}
