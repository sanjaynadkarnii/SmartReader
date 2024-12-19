package Model;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements the common methods for the reader model.
 */
public abstract class AReader implements IReaderModel {
  protected String text; // Accessible to subclasses
  protected String currentChunk;
  protected List<String> chunks;

  /**
   * Constructor for the AReader class.
   *
   * @throws IllegalArgumentException if the text is empty or null
   * @param text the text to be read
   */
  public AReader(String text) {
    if (text == null) {
      throw new IllegalArgumentException("Text cannot be null.");
    }
    if (text.equals("")) {
      throw new IllegalArgumentException("Text cannot be empty.");
    }

    this.text = text;
    this.chunks = new ArrayList<>();
    this.currentChunk = "";
  }

  @Override
  public abstract void parseText();

  public void nextChunk() {
    if (chunks == null) {
      throw new IllegalStateException("Chunks have not been initialized.");
    }
    if (!this.hasNextChunk()) {
      throw new IllegalStateException("No more chunks to read.");
    }

    this.currentChunk = this.chunks.remove(0);
  }

  public boolean hasNextChunk() {
    if (chunks == null) {
      throw new IllegalStateException("Chunks have not been initialized.");
    }
    return !this.chunks.isEmpty();
  }

  public String getCurrentChunk() {
    if (this.currentChunk == null || this.currentChunk.equals("")) {
      throw new IllegalStateException("No chunk is currently being read.");
    }
    return this.currentChunk;
  }

  public int getChunkCount() {
    if (chunks == null) {
      throw new IllegalStateException("Chunks have not been initialized.");
    }
    return this.chunks.size();
  }
}
