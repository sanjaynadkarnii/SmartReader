package View;

import Controller.IReaderController;

/**
 * Interface for any class attempting to view a reader and the methods required for such.
 */
public interface IReaderView {

  /**
   * Text representation of the model.
   *
   * @return the text representation of the model
   */
  String toString();

  /**
   * Displays the graphical view.
   */
  void display();

  /**
   * Sets the controller to link view and model.
   *
   * @param controller the controller
   */
  void setController(IReaderController controller);
}
