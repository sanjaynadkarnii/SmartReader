import Controller.IReaderController;
import Controller.ReaderController;
import Model.IReaderModel;
import Model.SentenceReader;
import Model.WordReader;
import View.NormalReaderView;

/**
 * Main class for the SmartReader program.
 */
public class SmartReader {
  public static void main(String[] args) {
    String s = "You deserve an explanation, so please don't skip this 1-minute read. We're sorry to interrupt, but this message will only be up for a short time. We ask you to reflect on the number of times you visited Wikipedia this past year and whether you're able to give $2.75 to the Wikimedia Foundation. If everyone reading this gave just $2.75, we'd hit our goal in a few hours.";
    IReaderModel model1 = new WordReader();
    IReaderModel model2 = new SentenceReader();
    model1.addText(s);
    model2.addText(s);
    NormalReaderView view = new NormalReaderView(model1);
    IReaderController controller = new ReaderController(model1, view);

    view.setController(controller);
    view.display();
  }
}
