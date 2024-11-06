package cs5004.evalExceptions;

/**
 * Base class for all errors that can be thrown during evaluation.
 */
public abstract class EvaluationException extends RuntimeException {

  public EvaluationException(String message) {
    super(message);
  }
}
