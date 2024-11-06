package cs5004.ast;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class ProgramTest {

  private List<Definition> definitions = Arrays.asList(
      new Definition("func1", List.of("x"), new IntLiteral(1)),
      new Definition("func2", List.of("y"), new VariableReference("y"))
  );
  private Expression expression = new VariableReference("result");
  private Program program = new Program(definitions, expression);

  @Test
  void getDefinitions() {
    assertEquals(definitions, program.getDefinitions());
  }

  @Test
  void getExpression() {
    assertEquals(expression, program.getExpression());
  }
}