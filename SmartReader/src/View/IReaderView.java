package View;

import Controller.IReaderController;

public interface IReaderView {

  String toString();

  void display();

  void setController(IReaderController controller);
}
