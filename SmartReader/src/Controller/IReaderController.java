package Controller;

/**
 * Interface for any class attempting to control a reader and the methods required for such.
 */
public interface IReaderController {

  /**
   * Handles an event where a button indicating next chunk is clicked.
   */
  void handleNextClick();

  /**
   * Handles an event where a button indicating previous chunk is clicked.
   */
  void handlePreviousClick();
}
