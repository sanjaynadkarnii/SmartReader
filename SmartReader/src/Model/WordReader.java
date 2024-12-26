package Model;

/**
 * Word-by-word reader model.
 */
public class WordReader extends AReader {

  /**
   * Constructor for the WordReader class.
   *
   * @throws IllegalArgumentException if the text is empty or null
   */
  public WordReader() {
    super();
  }

  @Override
  public void parseText() {
    if (this.text == null) {
      throw new IllegalStateException("Text cannot be null.");
    }
    if (this.text.equals("")) {
      throw new IllegalStateException("Text cannot be empty.");
    }

    String[] words = this.text.split(" ");
    for (String word : words) {
      this.chunks.add(word);
    }

    this.currentChunk = this.chunks.get(0);
  }
}
