/**
 * Created by likanov.k on 05.10.16.
 */

import javax.swing.JFrame;

public class Game extends JFrame {

    public static void main(String[] args)
    {

        GamePanel panel = new GamePanel();
        JFrame a = new JFrame("New Game");
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        a.setContentPane(panel);
        a.pack();
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        panel.start();

    }

}
