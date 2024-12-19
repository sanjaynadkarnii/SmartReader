import Model.AReader;
import Model.SentenceReader;
import Model.WordReader;


public class SmartReader {
  public static void main(String[] args) {

    AReader model = new SentenceReader("Hello World. It's time.");
    System.out.println(model.getCurrentChunk());

  }
}