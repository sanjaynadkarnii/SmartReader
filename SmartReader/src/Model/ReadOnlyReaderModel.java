package Model;

/**
 * Contains the accessors of the reader model.
 */
public interface ReadOnlyReaderModel {

  /**
   * Returns the current chunk of text.
   *
   * @throws IllegalStateException if no chunk is currently being read
   * @return the current chunk of text
   */
  String getCurrentChunk();

  /**
   * Returns whether there are more chunks to read.
   *
   * @throws IllegalStateException if chunks have not been initialized
   * @return whether there are more chunks to read
   */
  boolean hasNextChunk();

  /**
   * Returns the number of remaining chunks.
   *
   * @throws IllegalStateException if chunks have not been initialized
   * @return the number of remaining chunks
   */
  int getChunkCount();
}
