/**
 * Created by likanov.k on 06.10.16.
 */

import java.awt.*;

public class Bullets {

    //Fields
        private double x;
        private double y;
        private int r;

        private int speed;

        private Color color1;

    //Constructor
        public Bullets(){
            x = GamePanel.player.getX();
            y = GamePanel.player.getY();
            r = 2;

            speed = 10;

            color1 = Color.WHITE;

        }
    //Functions
        public boolean remove(){
//             Remove bullets
            if (y < 0) {
                return true;
            }
            return false;
        }

        public void update() {
            y -= speed;
        }

        public void draw(Graphics2D g) {
            g.setColor(color1);
            g.fillOval((int)x, (int)y, r, 2 * r);
        }
}
