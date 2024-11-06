package cs5004.parser.scanner;

/** Identifies the various kinds of tokens that the scanner can return.
 */
public enum TokenType {
  L_PAREN,
  R_PAREN,
  L_BRACE,
  R_BRACE,
  L_BRACKET,
  R_BRACKET,
  SYMBOL,
  INT_LIT,

  EOF
}
