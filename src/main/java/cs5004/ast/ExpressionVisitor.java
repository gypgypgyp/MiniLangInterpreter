package cs5004.ast;

import cs5004.core.Value;

/**
 * Visitor for computing with expressions.
 * @param <T> type of value produced by the visitor's operation.
 */
public interface ExpressionVisitor<T> {
  T visit(AndExpression andExpression);
  T visit(BooleanLiteral booleanLiteral);
  T visit(FunctionCall functionCall);
  T visit(IfExpression ifExpression);
  T visit(IntLiteral intLiteral);
  T visit(LetExpression letExpression);
  T visit(OrExpression orExpression);
  T visit(VariableReference reference);
}
