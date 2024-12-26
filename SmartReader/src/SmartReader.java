import Controller.IReaderController;
import Controller.ReaderController;
import Model.IReaderModel;
import Model.SentenceReader;
import Model.WordReader;
import View.NormalReaderView;

public class SmartReader {
  public static void main(String[] args) {
    IReaderModel model1 = new WordReader("Hello World. It's time to read.");
    IReaderModel model2 = new SentenceReader("Hello World. It's time to read.");
    NormalReaderView view = new NormalReaderView(model2);
    IReaderController controller = new ReaderController(model2, view);

    view.setController(controller);
    view.display();
  }
}
