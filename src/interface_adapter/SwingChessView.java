package interface_adapter;

import interface_adapter.ChessView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingChessView implements ChessView {

    private JFrame frame;

    public SwingChessView() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Chess Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            showMainMenu();  // Show the main menu when the frame is created
        });
    }

    @Override
    public void showMainMenu() {
        SwingUtilities.invokeLater(() -> {
            JPanel panel = new JPanel(new GridLayout(3, 1));

            JButton newGameButton = new JButton("New Game");
            newGameButton.addActionListener(e -> showNewGameScreen());

            JButton rulesButton = new JButton("Rules");
            rulesButton.addActionListener(e -> showRulesScreen());

            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> closeApplication());

            panel.add(newGameButton);
            panel.add(rulesButton);
            panel.add(exitButton);

            frame.getContentPane().removeAll();
            frame.getContentPane().add(panel);
            frame.revalidate();
            frame.repaint();
            frame.setVisible(true);
        });
    }

    @Override
    public void showNewGameScreen() {
        SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(frame, "Starting a new game!"));
    }

    @Override
    public void showRulesScreen() {
        SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(frame, "Displaying game rules!"));
    }

    @Override
    public void closeApplication() {
        SwingUtilities.invokeLater(() -> System.exit(0));
    }
}
