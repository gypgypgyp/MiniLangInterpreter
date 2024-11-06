package cs5004.ast;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class FunctionCallTest {
  private Expression arg1 = new IntLiteral(3);
  private Expression arg2 = new VariableReference("y");
  private List<Expression> arguments1 = Arrays.asList(arg1, arg2);
  private FunctionCall functionCall1 = new FunctionCall("f", arguments1);

  @Test
  void getFunctionName() {
    assertEquals(functionCall1.getFunctionName(),"f");
  }

  @Test
  void getArguments() {
    assertEquals(functionCall1.getArguments(),arguments1);
  }

  @Test
  void testEquals() {
    List<Expression> arguments2 = Arrays.asList(new IntLiteral(3), new VariableReference("y"));
    List<Expression> differentArguments = List.of(new IntLiteral(4));

    FunctionCall functionCall2 = new FunctionCall("f", arguments2);
    FunctionCall functionCallDifferentArgs = new FunctionCall("f", differentArguments);
    FunctionCall functionCallDifferentName = new FunctionCall("g", arguments1);

    assertEquals(functionCall1, functionCall1);
    assertEquals(functionCall1, functionCall2);
    assertNotEquals(functionCall1, functionCallDifferentArgs);
    assertNotEquals(functionCall1, functionCallDifferentName);
    assertNotEquals(functionCall1, null);
  }

  @Test
  void format() {
    Expression arg1 = new IntLiteral(3);
    Expression arg2 = new VariableReference("y");
    List<Expression> arguments = Arrays.asList(arg1, arg2);
    FunctionCall functionCall = new FunctionCall("f", arguments);
    String expectedFormat = "f(3, y)";
    assertEquals(expectedFormat, functionCall.format());
  }
}