/**
 * Created by likanov.k on 05.10.16.
 */
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.*;

public class GamePanel extends JPanel implements Runnable {

//    Field

    public static int WIDTH = 400;
    public static int HEIGHT = 400;

    private Thread th;
    private BufferedImage image;
    private Graphics2D g;
    public static GameBack background;
    public static Player player;

    public static ArrayList<Bullets> bullets;
    public static ArrayList<Enemies> enemies;


    //    Constructor
    public GamePanel () {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(new  Listeners());

    }


    public void start() {
        th = new Thread(this);
        th.start();
    }
//      Function
    public void run() {

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        background = new GameBack();
        player = new Player();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        bullets = new ArrayList<Bullets>();
        enemies = new ArrayList<Enemies>();
        // TODO remove enemies
        enemies.add(new Enemies(1, 1));
        enemies.add(new Enemies(1, 1));



        while (true) { //TO-DO states

            gameRender();
            gameUpdate();
            gameDraw();

            try {
                th.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void gameUpdate(){
//        Background update
        background.update();
//        Player update
        player.update();
//        Bullets update
        for (int i = 0; i < bullets.size(); i++ )
        {
            bullets.get(i).update();
            boolean remove = bullets.get(i).remove();
            if(remove) {
                bullets.remove(i);
                i--;
            }
        }

        //Enemies update
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }

    }
    public void gameRender(){
        // Background draw
        background.draw(g);
        //Player draw
        player.draw(g);
        // Bullets draw
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).draw(g);
        }

        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).draw(g);
        }
    }

    private void gameDraw()
    {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();

    }

}
