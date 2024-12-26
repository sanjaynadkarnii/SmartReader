package Model;

import Observer.ModelObserver;

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

  /**
   * Updates the current chunk to the previous chunk.
   *
   * @throws IllegalStateException if the chunks have not been initialized
   */
  void previousChunk();

  /**
   * Adds an observer to the list of observers.
   * @param observer the observer to add
   */
  void addObserver(ModelObserver observer);

  /**
   * Removes an observer from the list of observers.
   * @param observer the observer to remove
   */
  void removeObserver(ModelObserver observer);

  /**
   * Adds text to the reader model.
   *
   * @param text the text to add
   */
  void addText(String text);

}
