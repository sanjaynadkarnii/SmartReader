package Model;

/**
 * Word-by-word reader model.
 */
public class WordReader extends AReader {

  /**
   * Constructor for the WordReader class.
   *
   * @throws IllegalArgumentException if the text is empty or null
   * @param text the text to be read
   */
  public WordReader(String text) {
    super(text);
    this.parseText();
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

    this.currentChunk = this.chunks.remove(0);
  }
}
