/**
 * Created by likanov.k on 05.10.16.
 */

import java.awt.*;
public class Player {

    //Fields
    private double x, y;
    private int r;

    private int speed;

    public double health;

    private double dx; //Move Coef
    private double dy;

    private Color color1;
    private Color color2;

    public static boolean up, down, left, right, isFire;


    //Constructor

    public Player() {
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;

        r = 5;

        speed = 8;

        health = 2;

        dx = 0;
        dy = 0;

        color1 = Color.white;

        up = false;
        down = false;
        left = false;
        right = false;

        isFire = false;
    }

    //Functions
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public int getR(){ return r; }

    public void hit() {
        health--;
        System.out.println(health);
    }


    public void update()
    {
        if(up && y > r) {
            dy = -speed;
        }
        if(down && y < GamePanel.HEIGHT - r)
        {
            dy = speed;
        }
        if(left && x > r)
        {
            dx = -speed;
        }
        if(right && x < GamePanel.WIDTH - r)
        {
            dx = speed;
        }

        if (up && left || up && right || down && left || down && right )
        {
            double angle = Math.toRadians(45);
            dy = dy * Math.sin(angle);
            dx = dx * Math.cos(angle);
        }

        y += dy;
        x += dx;

        dx = 0;
        dy = 0;

        //Firing

        if (isFire) {
            GamePanel.bullets.add(new Bullets());
        }

    }

    public void draw(Graphics2D g){
        g.setColor(color1);
        g.fillOval((int) (x-r),(int)(y-r), 2*r, 2*r);
        g.setStroke(new BasicStroke(3));
        g.setColor(color1.darker());
        g.drawOval((int) (x-r),(int)(y-r), 2*r, 2*r);
        g.setStroke(new BasicStroke(1));
    }

}
