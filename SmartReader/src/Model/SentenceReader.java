package Model;

public class SentenceReader extends AReader {

    /**
    * Constructor for the SentenceReader class.
    *
    * @throws IllegalArgumentException if the text is empty or null
    * @param text the text to be read
    */
    public SentenceReader(String text) {
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

    // Regex to match sentences ending with a period
    String[] sentences = this.text.split("(?<=\\.)");
    for (String sentence : sentences) {
      this.chunks.add(sentence.trim()); // Trim to remove any extra spaces
    }

    // Initialize the first chunk
    if (!this.chunks.isEmpty()) {
      this.currentChunk = this.chunks.remove(0);
    }
  }
}
