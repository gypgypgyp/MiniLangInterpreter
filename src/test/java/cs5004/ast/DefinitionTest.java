package cs5004.ast;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

class DefinitionTest {
  private List<String> arguments1 = Arrays.asList("x", "y");
  private List<String> arguments2 = Arrays.asList("x", "y");
  private List<String> arguments3 = Arrays.asList("x", "z");
  private Expression body = new IntLiteral(10);
  private Definition def1 = new Definition("func", arguments1, body);
  private Definition def2 = new Definition("func", arguments2, body);
  private Definition def3 = new Definition("func", arguments3, body);
  private Definition def4 = new Definition("func2", arguments1, body);

  @Test
  void getName() {
    Assertions.assertEquals(def1.getName(),"func");
  }

  @Test
  void getArguments() {
    Assertions.assertEquals(def1.getArguments(),arguments1);
  }

  @Test
  void getBody() {
    Assertions.assertEquals(def1.getBody(),body);
  }

  @Test
  void testEquals() {
    assertEquals(def1, def2);
    assertNotEquals(def1, def3);
    assertNotEquals(def1, def4);
    assertNotEquals(def1, null);
  }

  @Test
  void testHashCode() {
    int expectedHashCode = Objects.hash("func", arguments1, body);
    assertEquals(expectedHashCode, def1.hashCode());
  }

  @Test
  void testToString() {
    List<String> arguments = Arrays.asList("x", "y");
    Expression body = new IntLiteral(10);
    Definition def = new Definition("func", arguments, body);

    String expectedString = "Definition(func, x, y, 10)";
    assertEquals(expectedString, def.toString());
  }
}