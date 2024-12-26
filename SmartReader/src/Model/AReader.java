package Model;

import Observer.ModelObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the implemented methods for the reader model for every type of reader.
 */
public abstract class AReader implements IReaderModel {
  protected String text;
  protected String currentChunk;
  protected List<String> chunks;
  private List<ModelObserver> observers; // List of observers
  private int currentChunkIndex;

  public AReader() {
    this.chunks = new ArrayList<>();
    this.currentChunk = "";
    this.observers = new ArrayList<>();
    this.currentChunkIndex = 0;
  }

  @Override
  public abstract void parseText();

  public void addText(String text) {
    if (text == null || text.isEmpty()) {
      throw new IllegalArgumentException("Text cannot be null or empty");
    }

    this.text = text;
    this.parseText();
  }

  @Override
  public void nextChunk() {
    if (!hasNextChunk()) {
      throw new IllegalStateException("No more chunks to read");
    }
    if (chunks.isEmpty() || chunks == null || currentChunk == null) {
      throw new IllegalStateException("Chunks have not been initialized");
    }

    currentChunkIndex++;
    this.currentChunk = chunks.get(currentChunkIndex);
    notifyObservers(); // Notify observers of the state change
  }

  @Override
  public void previousChunk() {
    if (chunks.isEmpty() || chunks == null || currentChunk == null) {
      throw new IllegalStateException("Chunks have not been initialized");
    }

    currentChunkIndex--;
    this.currentChunk = chunks.get(currentChunkIndex);
    notifyObservers(); // Notify observers of the state change
  }

  @Override
  public boolean hasNextChunk() {
    return currentChunkIndex < chunks.size() - 1;
  }

  @Override
  public boolean hasPreviousChunk() {
    return currentChunkIndex > 0;
  }

  public int chunksLeft() {
    return (chunks.size()-1) - currentChunkIndex;
  }

  @Override
  public String getCurrentChunk() {
    return this.currentChunk;
  }

  // Observer methods
  public void addObserver(ModelObserver observer) {
    this.observers.add(observer);
  }

  public void removeObserver(ModelObserver observer) {
    this.observers.remove(observer);
  }

  private void notifyObservers() {
    for (ModelObserver observer : observers) {
      observer.update();
    }
  }
}
