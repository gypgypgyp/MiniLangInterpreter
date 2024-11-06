package cs5004.evaluator;

import cs5004.ast.Definition;
import cs5004.ast.Program;
import cs5004.core.Environment;
import cs5004.core.Value;
import cs5004.parser.Parser;
import java.util.ArrayList;
import java.util.List;
/**
 * A driver class that facilitates the evaluation of source code
 * represented as strings into its corresponding value representation
 * based on a custom parsing and evaluation system.
 */
public class Driver {
  public Value evaluateFromSource(String sourceText) {
    return evaluateProgram(Parser.parseProgram("input", sourceText));
  }

  public Value evaluateProgram(Program program) {
    List<Definition> definitionList = program.getDefinitions();
    List<String> definitionNames = new ArrayList<>(definitionList.size());
    for (Definition d: definitionList) {
      definitionNames.add(d.getName());
    }
    Environment<Definition> definitions =
      new Environment<>(definitionNames, definitionList);
    return program.getExpression().accept(
      new Evaluator(definitions, new Environment<>())
    );
  }
}
