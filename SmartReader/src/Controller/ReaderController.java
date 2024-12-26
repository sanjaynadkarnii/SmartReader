package Controller;

import javax.swing.*;

import Model.IReaderModel;
import View.IReaderView;

/**
 * Implementation of the controller interface.
 */
public class ReaderController implements IReaderController {
  private IReaderModel model;
  private IReaderView view;

  /**
   * Constructor for the controller.
   * @param model the model
   * @param view the view
   */
  public ReaderController(IReaderModel model, IReaderView view) {
    this.model = model;
    this.view = view;

    // Register the view as an observer of the model
    model.addObserver((Observer.ModelObserver) view);
  }

  public void handleMouseClick() {
    if (model.hasNextChunk()) {
      model.nextChunk(); // Updates the model, which notifies the view
    } else {
      JOptionPane.showMessageDialog(null, "No more chunks to display!");
    }
  }
}
