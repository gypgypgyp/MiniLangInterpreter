package cs5004.core;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnvironmentTests {

  @Test
  public void testEmptyEnvironment() {
    Assertions.assertNull(new Environment<Integer>().lookup("x"));
  }

  @Test
  public void testNonEmptyEnvironment() {
    Environment<Integer> env = new Environment<>(
        List.of("x", "y", "z"),
        List.of(4, 5, 6)
    );

    Assertions.assertEquals(5, env.lookup("y"));
    Assertions.assertNull(env.lookup("q"));
  }

  @Test
  public void testEnvironmentConstruction() {
    try {
      new Environment<Integer>(
          List.of("a", "b", "c"),
          List.of(1, 2)
      );
      Assertions.fail("expected exception");
    } catch (IllegalArgumentException e) {
      // NOP
    }
  }

  @Test
  public void testExtendedEnvironment() {
    Environment<Integer> base = new Environment<>(
        List.of("x", "y", "z"),
        List.of(1, 2, 3)
    );

    Environment<Integer> env =
        base.extend("a", 4)
            .extend("y", 5);
    Assertions.assertEquals(1, env.lookup("x"));
    Assertions.assertEquals(4, env.lookup("a"));
    Assertions.assertEquals(5, env.lookup("y"));
    Assertions.assertNull(env.lookup("f"));
  }
}
