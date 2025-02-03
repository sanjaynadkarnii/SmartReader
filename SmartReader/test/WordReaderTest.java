

import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;
import static org.junit.Assert.*;

import Model.WordReader;
import Model.AReader;
import Observer.ModelObserver;

public class WordReaderTest {
  private WordReader reader;
  private ModelObserver observer;

  @Before
  public void setUp() {
    reader = new WordReader();
    observer = new MockModelObserver();
    reader.addObserver(observer);
  }

  @Test
  public void testParseTextValidWords() {
    reader.addText("Hello world from JUnit");
    assertEquals("Hello", reader.getCurrentChunk());
    reader.nextChunk();
    assertEquals("world", reader.getCurrentChunk());
    reader.nextChunk();
    assertEquals("from", reader.getCurrentChunk());
    reader.nextChunk();
    assertEquals("JUnit", reader.getCurrentChunk());
    assertFalse(reader.hasNextChunk());
  }

  @Test
  public void testParseTextEdgeCases() throws Exception {
    setTextViaReflection(null);
    assertThrows(IllegalStateException.class, () -> reader.parseText());

    setTextViaReflection("");
    assertThrows(IllegalStateException.class, () -> reader.parseText());
  }

  @Test
  public void testChunkNavigation() {
    reader.addText("One two three four");

    assertEquals("One", reader.getCurrentChunk());
    assertEquals(3, reader.chunksLeft());

    reader.nextChunk();
    assertEquals("two", reader.getCurrentChunk());
    assertTrue(reader.hasPreviousChunk());

    reader.nextChunk();
    reader.nextChunk();
    assertFalse(reader.hasNextChunk());

    reader.previousChunk();
    assertEquals("three", reader.getCurrentChunk());
  }

  @Test
  public void testBoundaryConditions() {
    // Test empty state
    assertThrows(IllegalStateException.class, () -> reader.nextChunk());
    assertThrows(IllegalStateException.class, () -> reader.previousChunk());

    // Test single word
    reader.addText("Singleton");
    assertThrows(IllegalStateException.class, () -> reader.nextChunk());
    assertThrows(IndexOutOfBoundsException.class, () -> reader.previousChunk());
  }

  @Test
  public void testChunkCounting() {
    reader.addText("A B C D E");
    assertEquals(4, reader.chunksLeft());

    reader.nextChunk();
    assertEquals(3, reader.chunksLeft());

    reader.nextChunk();
    reader.nextChunk();
    assertEquals(1, reader.chunksLeft());
  }

  private void setTextViaReflection(String text) throws Exception {
    Field textField = AReader.class.getDeclaredField("text");
    textField.setAccessible(true);
    textField.set(reader, text);
  }

  private static class MockModelObserver implements ModelObserver {
    private int updateCount = 0;

    @Override
    public void update() {
      updateCount++;
    }

    public int getUpdateCount() {
      return updateCount;
    }
  }
}