/**
 * Created by likanov.k on 05.10.16.
 *
 * TODO
 *  Исправить баг в классе волн(после 4ой волны волны зацыкливаются)
 *  Исправить координаты кнопки в классе меню(почему-то не попадает в область)
 *  ПРИДУМАЙ ЗАДАНИЕ, ДЕЙЛАЙ ЧТО ХОЧЕШЬ С ЭТИМ ДЕРЬМОМ!!!!
 *
 */
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.*;

public class GamePanel extends JPanel implements Runnable {

//    Field

    public static int WIDTH = 600;
    public static int HEIGHT = 600;

    public static enum STATES{
        MENU,
        PLAY
    }

    public static STATES state = STATES.MENU;

    public static int mouseX;
    public static int mouseY;
    public static boolean leftMouse;

    private Thread th;
    private BufferedImage image;
    private Graphics2D g;

    private int FPS;
    private int millisToFps;
    private long timerFPS;
    private int sleepTime;

    public static Menu menu;
    public static GameBack background;
    public static Player player;
    public static ArrayList<Bullets> bullets;
    public static ArrayList<Enemies> enemies;
    public static Wave wave;


    //    Constructor
    public GamePanel () {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(new  Listeners());
        addMouseMotionListener(new Listeners());
        addMouseListener(new Listeners());

    }


    public void start() {
        th = new Thread(this);
        th.start();
    }
//      Function


    public void run() {

        FPS = 30;
        millisToFps = 1000/FPS;
        sleepTime = 0;

        leftMouse = false;
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        background = new GameBack();
        player = new Player();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        bullets = new ArrayList<Bullets>();
        enemies = new ArrayList<Enemies>();
        wave = new Wave();
        menu = new Menu();

        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage buffered = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) buffered.getGraphics();
        g3.setColor(new Color(255, 255, 255));
        g3.drawOval(0, 0, 4, 4);
        g3.drawLine(2, 0, 2, 4);
        g3.drawLine(0, 2, 4, 2);
        Cursor cursor = kit.createCustomCursor(buffered, new Point(3, 3), "myCursor");
        g3.dispose();
        // TODO remove enemies
//        enemies.add(new Enemies(1, 1));
//        enemies.add(new Enemies(1, 1));   Это можно в принципе удалить, но создавая такие объекты можно делать врагов



        while (true) { //TO-DO states

            this.setCursor(cursor);
            timerFPS = System.nanoTime();

            if(state.equals(STATES.MENU)) {
                background.update();
                background.draw(g);
                menu.update();
                menu.draw(g);
                gameDraw();
            }

            if(state.equals(STATES.PLAY)){
                gameRender();
                gameUpdate();
                gameDraw();
            }

            timerFPS = (System.nanoTime() - timerFPS)/1000000;
            if(millisToFps > timerFPS){
                sleepTime = (int)(millisToFps - timerFPS);
            }
            else sleepTime = 1;
            try {
                th.sleep(sleepTime);
//                System.out.println(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timerFPS = 0;
            sleepTime = 1;
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

        //Bullets-enemies collide
            for(int i=0; i < enemies.size(); i++)
            {
                Enemies e = enemies.get(i);
                double ex = e.getX();
                double ey = e.getY();

                for(int j=0; j < bullets.size(); j++)
                {

                    Bullets b = bullets.get(j);
                    double bx = b.getX();
                    double by = b.getY();
                    double dx = ex - bx;
                    double dy = ey - by;
                    double dist = Math.sqrt(dx * dx + dy * dy);
                    if((int)dist < e.getR() + b.getR())
                    {
                            e.hit();
                            bullets.remove(j);
                            boolean remove = e.remove();
                            if (remove) {
                                enemies.remove(i);
                                i--;
                                break;
                            }
                    }
                }
            }
        //Player-enemy collides
        for (int i = 0; i < enemies.size(); i++){
            Enemies e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();

            double px = player.getX();
            double py = player.getY();
            double dx = ex - px;
            double dy = ey - py;
            double dist = Math.sqrt(dx * dx + dy * dy);
            if((int)dist <= e.getR() + player.getR()) {
                e.hit();
                player.hit();

                boolean remove = e.remove();
                if (remove) {
                    enemies.remove(i);
                    i--;
                    break;
                }
            }
        }
        //Wave update
        wave.update();

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
        //Wave draw
        if(wave.showWave()){
            wave.draw(g);
        }
    }

    private void gameDraw()
    {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();

    }

}
