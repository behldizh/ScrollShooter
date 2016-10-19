/**
 * Created by likanov.k on 06.10.16.
 */

import java.awt.*;

public class Bullets {

    //Fields
        private double x;
        private double y;
        private int r;

        private double bulletDX;
        private double bulletDY;
        private double distX;
        private double distY;
        private double dist;

        private int speed;

        private Color color1;

    //Constructor
        public Bullets(){
            x = GamePanel.player.getX();
            y = GamePanel.player.getY();
            r = 2;

            speed = 7;

            distX = GamePanel.mouseX - x;
            distY = GamePanel.mouseY - y;
            dist = Math.sqrt(distX * distX + distY * distY);

            bulletDX = distX/dist * speed;
            bulletDY = distY/dist * speed;

            color1 = Color.RED;

        }
    //Functions
        public double getX(){return x;}
        public double getY(){return y;}
        public int getR(){return r;}


        public boolean remove(){
//             Remove bullets
            if (y < 0) {
                return true;
            }
            return false;
        }

        public void update() {

            y += bulletDY;
            x += bulletDX;
        }

        public void draw(Graphics2D g) {
            g.setColor(color1);
            g.fillOval((int)x, (int)y, r, 2 * r);
        }
}
