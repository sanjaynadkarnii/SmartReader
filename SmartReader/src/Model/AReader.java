package Model;

import Observer.ModelObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class AReader implements IReaderModel {
  protected String text;
  protected String currentChunk;
  protected List<String> chunks;
  private List<ModelObserver> observers; // List of observers

  public AReader(String text) {
    if (text == null || text.isEmpty()) {
      throw new IllegalArgumentException("Text cannot be null or empty");
    }

    this.text = text;
    this.chunks = new ArrayList<>();
    this.currentChunk = "";
    this.observers = new ArrayList<>();
  }

  @Override
  public abstract void parseText();

  @Override
  public void nextChunk() {
    if (!hasNextChunk()) {
      throw new IllegalStateException("No more chunks to read");
    }
    this.currentChunk = chunks.remove(0);
    notifyObservers(); // Notify observers of the state change
  }

  @Override
  public boolean hasNextChunk() {
    return !chunks.isEmpty();
  }

  public int getChunkCount() {
    return chunks.size();
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
