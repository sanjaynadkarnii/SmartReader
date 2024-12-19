package Model;

/**
 * Contains the mutable methods for the reader model.
 */
public interface IReaderModel extends ReadOnlyReaderModel {

  /**
   * Parses the text into chunks.
   *
   * @throws IllegalStateException if the text is empty or null
   */
  void parseText();

  /**
   * Updates the current chunk to the next chunk.
   *
   * @throws IllegalStateException if there are no more chunks to read
   * @throws IllegalStateException if the chunks have not been initialized
   */
  void nextChunk();
}
