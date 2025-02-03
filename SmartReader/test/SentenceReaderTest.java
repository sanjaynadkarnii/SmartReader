
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;
import static org.junit.Assert.*;
import Model.SentenceReader;
import Model.AReader;
import Observer.ModelObserver;

public class SentenceReaderTest {
  private SentenceReader reader;
  private ModelObserver observer;

  @Before
  public void setUp() {
    reader = new SentenceReader();
    observer = new MockModelObserver();
    reader.addObserver(observer);
  }

  @Test
  public void testParseTextValidSentences() {
    reader.addText("Hello. How are you? I'm fine.");
    assertEquals("Hello.", reader.getCurrentChunk());
    assertTrue(reader.hasNextChunk());
    reader.nextChunk();
    assertEquals("How are you?", reader.getCurrentChunk());
    reader.nextChunk();
    assertEquals("I'm fine.", reader.getCurrentChunk());
    assertFalse(reader.hasNextChunk());
  }

  @Test
  public void testParseTextWithNullText() throws Exception {
    setTextViaReflection(null);
    assertThrows(IllegalStateException.class, () -> reader.parseText());
  }

  @Test
  public void testParseTextWithEmptyText() throws Exception {
    setTextViaReflection("");
    assertThrows(IllegalStateException.class, () -> reader.parseText());
  }

  @Test
  public void testAddTextValidation() {
    assertThrows(IllegalArgumentException.class, () -> reader.addText(null));
    assertThrows(IllegalArgumentException.class, () -> reader.addText(""));
  }

  @Test
  public void testChunkNavigation() {
    reader.addText("First. Second. Third.");

    assertEquals("First.", reader.getCurrentChunk());
    assertTrue(reader.hasNextChunk());
    assertEquals(2, reader.chunksLeft());

    reader.nextChunk();
    assertEquals("Second.", reader.getCurrentChunk());
    assertTrue(reader.hasPreviousChunk());
    assertTrue(reader.hasNextChunk());
    assertEquals(1, reader.chunksLeft());

    reader.nextChunk();
    assertEquals("Third.", reader.getCurrentChunk());
    assertFalse(reader.hasNextChunk());
    assertEquals(0, reader.chunksLeft());

    reader.previousChunk();
    assertEquals("Second.", reader.getCurrentChunk());
  }

  @Test
  public void testBoundaryExceptions() {
    // Test empty state
    assertThrows(IllegalStateException.class, () -> reader.nextChunk());
    assertThrows(IllegalStateException.class, () -> reader.previousChunk());

    // Test end of chunks
    reader.addText("Single sentence.");
    assertThrows(IllegalStateException.class, () -> reader.nextChunk());

    // Test beginning of chunks
    assertThrows(IndexOutOfBoundsException.class, () -> reader.previousChunk());
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