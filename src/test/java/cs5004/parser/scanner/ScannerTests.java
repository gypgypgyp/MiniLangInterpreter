package cs5004.parser.scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs5004.parser.Posn;
import org.junit.jupiter.api.Test;

public class ScannerTests {
  @Test
  public void testEmptyInput() throws ScannerException {
    Scanner s = Scanner.makeTestScanner("");
    assertEquals(
      eofToken(1, 0),
      s.getToken()
    );
    // and verify that repeated reads return the same result.
    assertEquals(
      eofToken(1, 0),
      s.getToken()
    );
  }

  @Test
  public void testOnlyWhitespace() throws ScannerException {
    Scanner s = Scanner.makeTestScanner("  ");
    assertEquals(
      eofToken(1, 2),
      s.getToken()
    );
    // ensure that repeated calls to getToken at EOF also work.
    assertEquals(
      eofToken(1, 2),
      s.getToken()
    );
  }

  @Test
  public void testPunctuation() throws ScannerException {
    Scanner s = Scanner.makeTestScanner("(");
    assertEquals(
      token(TokenType.L_PAREN, 1, 0),
      s.getToken()
    );
    assertEquals(
      eofToken(1, 1),
      s.getToken()
    );
  }

  @Test
  public void testCorrectSpacing() throws ScannerException {
    Scanner s = Scanner.makeTestScanner(".  .\t.\n.\r .\r\n .");
    assertEquals(
      symbolToken(1, 0, "."),
      s.getToken()
    );
    assertEquals(
      symbolToken(1, 3, "."),
      s.getToken()
    );
    assertEquals(
      symbolToken(1, 8, "."),
      s.getToken()
    );
    assertEquals(
      symbolToken(2, 0, "."),
      s.getToken()
    );
    assertEquals(
      symbolToken(3, 1, "."),
      s.getToken()
    );
    assertEquals(
      symbolToken(4, 1, "."),
      s.getToken()
    );
    assertEquals(
      eofToken(4, 2),
      s.getToken()
    );
  }

  @Test
  public void testLineComment() throws ScannerException {
    Scanner s = Scanner.makeTestScanner(" xyz ;; comment\na");
    assertEquals(
      symbolToken(1, 1, "xyz"),
      s.getToken()
    );
    assertEquals(
      symbolToken(2, 0, "a"),
      s.getToken()
    );
    assertEquals(
      eofToken(2, 1),
      s.getToken()
    );
  }

  @Test
  public void testIntegerLiteral() throws ScannerException {
    Scanner s = Scanner.makeTestScanner("34 -25\n +16 0009");
    assertEquals(
      intToken(1, 0, 34),
      s.getToken()
    );
    assertEquals(
      intToken(1, 3, -25),
      s.getToken()
    );
    assertEquals(
      intToken(2, 1, 16),
      s.getToken()
    );
    assertEquals(
      intToken(2, 5, 9),
      s.getToken()
    );
    assertEquals(
      eofToken(2, 9),
      s.getToken()
    );
  }

  @Test
  public void testSymbols() throws ScannerException {
    Scanner s = Scanner.makeTestScanner("0x64\nhello(3.4.5)16 3-2");

    assertEquals(
      symbolToken(1, 0, "0x64"),
      s.getToken()
    );
    assertEquals(
      symbolToken(2, 0, "hello"),
      s.getToken()
    );
    assertEquals(
      token(TokenType.L_PAREN, 2, 5),
      s.getToken()
    );
    assertEquals(
      symbolToken(2, 6, "3.4.5"),
      s.getToken()
    );
    assertEquals(
      token(TokenType.R_PAREN, 2, 11),
      s.getToken()
    );
    assertEquals(
      intToken(2, 12, 16),
      s.getToken()
    );
    assertEquals(
      symbolToken(2, 15, "3-2"),
      s.getToken()
    );
  }

  private Scanner.Token token(TokenType tt, int line, int col) {
    return new Scanner.Token(
      tt,
      new Posn(Scanner.TEST_INPUT_NAME, line, col),
      null
    );
  }

  private Scanner.Token symbolToken(
    int line,
    int col,
    String txt
  ) {
    return new Scanner.Token(
      TokenType.SYMBOL,
      new Posn(Scanner.TEST_INPUT_NAME, line, col),
      txt
    );
  }

  private Scanner.Token intToken(int line, int col, int value) {
    return new Scanner.Token(
      TokenType.INT_LIT,
      new Posn(Scanner.TEST_INPUT_NAME, line, col),
      value
    );
  }

  private Scanner.Token eofToken(int line, int col) {
    return new Scanner.Token(
      TokenType.EOF,
      new Posn(Scanner.TEST_INPUT_NAME, line, col),
      null
    );
  }
}
