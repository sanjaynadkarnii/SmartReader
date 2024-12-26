package View;

import Controller.IReaderController;
import Model.ReadOnlyReaderModel;
import Observer.ModelObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/**
 * Implementation of the view interface.
 */
public class NormalReaderView implements IReaderView, ModelObserver {
  private ReadOnlyReaderModel model;
  private IReaderController controller;
  private JLabel chunkLabel;
  private JLabel chunksLeftLabel;

  /**
   * Constructor for the view.
   *
   * @throw IllegalArgumentException if the model is null
   * @param model the reader model
   */
  public NormalReaderView(ReadOnlyReaderModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.model = model;
  }

  @Override
  public void display() {
    createWindow();
  }

  @Override
  public void setController(IReaderController controller) {
    this.controller = controller;
  }

  private void createWindow() {
    JFrame frame = new JFrame("Reader");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 800);

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    chunkLabel = new JLabel(model.getCurrentChunk(), SwingConstants.CENTER);
    chunkLabel.setFont(new Font("Times New Roman", Font.PLAIN, 72));
    panel.add(chunkLabel, BorderLayout.CENTER);

    chunksLeftLabel = new JLabel(
            "Chunks left: " + model.chunksLeft(), SwingConstants.NORTH_EAST);
    chunksLeftLabel.setFont(new Font("Times New Roman", Font.PLAIN, 48));
    panel.add(chunksLeftLabel, BorderLayout.NORTH);

    // Ensure the panel is focusable
    panel.setFocusable(true);

    // Add a mouse listener to notify the controller for moving next
    panel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        controller.handleNextClick();
      }
    });

    // Add a key listener to notify the controller for moving next
    panel.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_RIGHT) {
          controller.handleNextClick();
        }
      }
    });

    // Add a key listener to notify the controller for moving back
    panel.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_LEFT) {
          controller.handlePreviousClick();
        }
      }
    });

    // Request focus for the panel
    frame.add(panel);
    frame.setVisible(true);
    panel.requestFocusInWindow();
  }


  @Override
  public void update() {
    chunkLabel.setText(model.getCurrentChunk());
    chunksLeftLabel.setText("Chunks left: " + model.chunksLeft());
  }

  private void onPanelClick() {
    JOptionPane.showMessageDialog(null, "Mouse clicked!");
    // This click event will notify the controller
  }
}
