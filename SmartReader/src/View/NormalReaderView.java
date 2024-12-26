package View;

import Controller.IReaderController;
import Controller.ReaderController;
import Model.ReadOnlyReaderModel;
import Observer.ModelObserver;

import javax.swing.*;
import java.awt.*;

public class NormalReaderView implements IReaderView, ModelObserver {
  private ReadOnlyReaderModel model;
  private IReaderController controller;
  private JLabel label;

  public NormalReaderView(ReadOnlyReaderModel model) {
    this.model = model;
    this.controller = controller;
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
    frame.setSize(400, 200);

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    label = new JLabel(model.getCurrentChunk(), SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.PLAIN, 24));
    panel.add(label, BorderLayout.CENTER);

    frame.add(panel);
    frame.setVisible(true);

    // Add a mouse listener to notify the controller
    panel.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent e) {
        controller.handleMouseClick();
      }
    });
  }

  @Override
  public void update() {
    label.setText(model.getCurrentChunk());
  }

  private void onPanelClick() {
    JOptionPane.showMessageDialog(null, "Mouse clicked!");
    // This click event will notify the controller
  }
}
