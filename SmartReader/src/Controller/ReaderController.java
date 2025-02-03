package Controller;

import javax.swing.*;

import Model.IReaderModel;
import View.IReaderView;

/**
 * Implementation of the controller interface.
 */
public class ReaderController implements IReaderController {
  private IReaderModel model;

  /**
   * Constructor for the controller.
   * @param model the model
   * @param view the view
   */
  public ReaderController(IReaderModel model, IReaderView view) {
    this.model = model;

    // Register the view as an observer of the model
    model.addObserver((Observer.ModelObserver) view);
  }

  public void handleNextClick() {
    if (model.hasNextChunk()) {
      model.nextChunk(); // Updates the model, which notifies the view
    } else {
      JOptionPane.showMessageDialog(null, "No more chunks to display!");
    }
  }

  public void handlePreviousClick() {
    if (model.hasPreviousChunk()) {
      model.previousChunk(); // Updates the model, which notifies the view
    } else {
      JOptionPane.showMessageDialog(null, "No more chunks to display!");
    } // Updates the model, which notifies the view
  }
}
