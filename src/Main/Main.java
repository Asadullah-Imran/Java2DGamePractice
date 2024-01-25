package Main;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        //Creating JFrame for my Game.
        JFrame window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");

        // Adding the game panel to the window.
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // Loading configuration settings.
        gamePanel.config.loadConfig();

    // Packing the window to fit the preferred size and layouts of its subcomponents.
        window.pack();

    // Centering the window on the screen.
        window.setLocationRelativeTo(null);

    // Making the window visible.
        window.setVisible(true);

    // Setting up game objects and configurations.
        gamePanel.setupGame();

    // Starting the game thread.
        gamePanel.startGameThread();

    }
}