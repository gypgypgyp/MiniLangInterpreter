package cs5004.evaluator;

import cs5004.ast.AndExpression;
import cs5004.ast.BooleanLiteral;
import cs5004.ast.Definition;
import cs5004.ast.Expression;
import cs5004.ast.ExpressionVisitor;
import cs5004.ast.FunctionCall;
import cs5004.ast.IfExpression;
import cs5004.ast.IntLiteral;
import cs5004.ast.LetExpression;
import cs5004.ast.OrExpression;
import cs5004.ast.VariableReference;
import cs5004.core.*;
import cs5004.evalExceptions.ArityMismatchException;
import cs5004.evalExceptions.UndefinedFunctionException;
import cs5004.evalExceptions.UndefinedVariableException;
import cs5004.primitives.PrimitiveTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Evaluator class is responsible for evaluating expressions in a programming language.
 * It implements the ExpressionVisitor interface, and defines methods for visiting and
 * evaluating each type of expression.

 * It maintains two environments:
 * - definitions: an environment that stores function definitions
 * - variables: an environment that stores variable bindings

 * withVariableBinding() method allows creating a new Evaluator instance with a modified
 * variables environment, which can be useful for evaluating expressions in different contexts.

 * The visit() methods for each kind of expression type handle the evaluation of that expression.
 * The implementations use the accept() method to recursively evaluate sub-expressions
 * and combine the results to produce the final value.

 * Author: Xi Yang
 */
public class Evaluator implements ExpressionVisitor<Value> {
  private final Environment<Definition> definitions;
  private final Environment<Value> variables;


  /**
   * Constructs a new Evaluator instance with the given definitions and variables environments.
   * @param definitions the environment that stores function definitions
   * @param variables the environment that stores variable bindings
   */
  public Evaluator(
    Environment<Definition> definitions,
    Environment<Value> variables
  ) {
    this.definitions = Objects.requireNonNull(definitions);
    this.variables = Objects.requireNonNull(variables);
  }

  /**
   * Creates a new Evaluator instance with the same definitions environment,
   * but a modified variables' environment.
   * @param variableName the name of the variable to bind
   * @param variableValue the value to bind the variable to
   * @return a new Evaluator instance with the updated variables environment
   */
  public Evaluator withVariableBinding(
    String variableName,
    Value variableValue
  ) {
    return new Evaluator(
      definitions,
      variables.extend(variableName, variableValue)
    );
  }

  /**
   * Visits an AndExpression and evaluates it.
   * @param andExpression the AndExpression to visit
   * @return the evaluated value
   */
  @Override
  public Value visit(AndExpression andExpression) {
    Value leftValue = andExpression.getLeft().accept(this);

    // Check left operand type and value, if the left operand is false, return false (short-circuiting)
    if (!leftValue.asBoolean())
      return new BooleanValue(false);

    // Check the right operand type and value
    Value rightValue = andExpression.getRight().accept(this);

    // Else return the boolean value of the right operand
    return new BooleanValue(rightValue.asBoolean());
  }

  /**
   * Visits a BooleanLiteral and evaluates it.
   * @param booleanLiteral the BooleanLiteral to visit
   * @return the evaluated BooleanValue
   */
  @Override
  public BooleanValue visit(BooleanLiteral booleanLiteral) {
    return new BooleanValue(booleanLiteral.isValue());
  }

  /**
   * Visits a FunctionCall and evaluates it.
   * @param functionCall the FunctionCall to visit
   * @return the evaluated Value
   */
  @Override
  public Value visit(FunctionCall functionCall) {
    // Evaluate each of the argument expressions (if any) and store the values in a new list
    //f(h(2))
    List<Expression> arguments = functionCall.getArguments();
    List<Value> argumentsValues = new ArrayList<>();

    for (Expression argument : arguments) {
      argumentsValues.add(argument.accept(this));
    }

    // Look up the function name in the definitions environment
    String functionName = functionCall.getFunctionName();
    Definition functionDefinition = definitions.lookup(functionName);

    // If definition found in the current environment
    if (functionDefinition != null) {
      // Check arity: If the number of arguments do not match, throw an exception
      if (functionDefinition.getArguments().size() != arguments.size()) {
        throw new ArityMismatchException(functionName,
            functionDefinition.getArguments().size(),
            arguments.size());
      }

      // Create a new value environment with entries for all arguments, in order to use the arguments inside the function
      // In this environment, key is the argument's name, value is its value
      List<String> argumentsNames = functionDefinition.getArguments();
      Environment<Value> newEnvironment = new Environment<>(argumentsNames, argumentsValues);
      Evaluator evaluator = new Evaluator(definitions, newEnvironment);
      return functionDefinition.getBody().accept(evaluator);
    } else {
      // Check if we can find the function in primitive table
      PrimitiveTable primitiveTable = new PrimitiveTable();
      if (primitiveTable.lookup(functionName) != null)
        return primitiveTable.lookup(functionName).apply(argumentsValues);
    }

    throw new UndefinedFunctionException("No such function defined!");
  }

  /**
   * Visits an IfExpression and evaluates it.
   * @param ifExpression the IfExpression to visit
   * @return the evaluated Value
   */
  @Override
  public Value visit(IfExpression ifExpression) {
    Value conditionValue = ifExpression.getCondition().accept(this);
    if (conditionValue.asBoolean()) {
      return ifExpression.getConsequent().accept(this);
    }
    return ifExpression.getAlternative().accept(this);
  }

  /**
   * Visits an IntLiteral and evaluates it.
   * @param intLiteral the IntLiteral to visit
   * @return the evaluated IntValue
   */
  @Override
  public IntValue visit(IntLiteral intLiteral) {
    return new IntValue(intLiteral.getValue());
  }

  /**
   * Visits a LetExpression and evaluates it.
   * @param letExpression the LetExpression to visit
   * @return the evaluated Value
   */
  @Override
  public Value visit(LetExpression letExpression) {
    // Create a variable environment using the variable names
    Value rhsValue = letExpression.getRhs().accept(this);
    Evaluator evaluator = withVariableBinding(letExpression.getVarName(), rhsValue);
    return letExpression.getBody().accept(evaluator);
  }

  /**
   * Visits an OrExpression and evaluates it.
   * @param orExpression the OrExpression to visit
   * @return the evaluated Value
   */
  @Override
  public Value visit(OrExpression orExpression) {
    boolean leftValue = orExpression.getLeft().accept(this).asBoolean();
    if (leftValue) {
      return new BooleanValue(true);
    }
    return new BooleanValue(orExpression.getRight().accept(this).asBoolean());
  }

  /**
   * Visits a VariableReference and evaluates it.
   * @param reference the VariableReference to visit
   * @return the evaluated Value
   */
  @Override
  public Value visit(VariableReference reference) {
    Value value = variables.lookup(reference.getVariableName());
    if (value != null) {
      return value;
    }
    throw new UndefinedVariableException(reference.getVariableName());
  }
}
