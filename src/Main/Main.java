package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");

        //Adding game panel
        GamePanel gamePanel= new GamePanel();

        //adding components
        window.add(gamePanel);

        window.pack();//Cause this window to be sized to fit the preferred size and layouts of its subcomponents (GamePanel)


        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}