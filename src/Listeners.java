import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by likanov.k on 05.10.16.
 */
public class Listeners implements KeyListener {



    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            Player.up = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            Player.down = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            Player.left = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            Player.right = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            Player.isFire = true;
        }


    }


    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            Player.up = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            Player.down = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            Player.left = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            Player.right = false;
        }
        if (key == KeyEvent.VK_SPACE) {
            Player.isFire = false;
        }



    }
}
