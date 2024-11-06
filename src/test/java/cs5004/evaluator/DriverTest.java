package cs5004.evaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs5004.core.IntValue;
import cs5004.core.Value;
import org.junit.jupiter.api.Test;

public class DriverTest {
  @Test
  public void testEvaluateProgram() {
    Driver driver = new Driver();
    String sourceText = "(define (square x) (* x x) )\n" +
        "(square 5)";
    Value result = driver.evaluateFromSource(sourceText);
    assertEquals(new IntValue(25), result);
  }
}

