package cs5004.parser.scanner;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

/**
 * Read characters from a Reader with a single character of lookahead.
 */
class LookaheadReader implements java.io.Closeable {
  /// Underlying data source.  Must not be null.
  private final Reader source;

  /// Tracks whether we have a character in the buffer.
  private boolean hasStoredChar;

  /// Single-character lookahead buffer.  Contents are valid only when
  /// hasStoredChar is true.
  private int storedChar;

  /**
   * Constructs a LookaheadReader from the given input source.
   */
  public LookaheadReader(Reader source) {
    this.source = Objects.requireNonNull(source);
    this.hasStoredChar = false;
    this.storedChar = 0;
  }

  /**
   * Reads and consumes a single char from the underlying source.
   * Returns -1 if end of file.
   */
  public int readChar() throws IOException {
    if (hasStoredChar) {
      hasStoredChar = false;
      return storedChar;
    } else {
      return source.read();
    }
  }

  /**
   * Reads but does not consume a single char from the underlying
   * source.  Returns -1 if end of file.
   */
  public int peekChar() throws IOException {
    if (!hasStoredChar) {
      storedChar = source.read();
      hasStoredChar = true;
    }
    return storedChar;
  }

  /**
   * Returns true if the reader has reached the end of the input, and
   * false otherwise.
   */
  public boolean eof() throws IOException {
    return (peekChar() == -1);
  }

  /**
   * Closes the reader and its underlying data source.
   */
  public void close() throws IOException {
    source.close();
  }
}
