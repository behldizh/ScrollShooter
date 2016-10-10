import java.awt.*;

/**
 * Created by likanov.k on 10.10.16.
 */
public class Enemies {
    //Fields
    private double x;
    private double y;
    private int r;
    private Color color;

    private double speed;

    private int type;
    private int rank;
    private double health;

    private double dx;
    private double dy;

    //Constructor
    public Enemies(int type, int rank){

        this.type = type;
        this.rank = rank;

        switch (type) {
            case (1): color = Color.GREEN;
                switch (rank) {
                    case(1):
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;

                        r = 7;

                        speed = 5;

                        double angle = Math.toRadians(Math.random()*360);
                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;


                }
        }

    }
    //Functions

    public void update() {

        x += dx;
        y += dy;

        if(x < 0 && dx < 0) dx = -dx;
        if(x > GamePanel.WIDTH && dx > 0) dx =-dx;
        if(y < 0 && dy < 0) dy = -dy;
        if(y > GamePanel.HEIGHT && dy > 0) dy = -dy;
    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillOval((int)(x - r), (int)(y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(color.brighter());
        g.fillOval((int)(x - r), (int)(y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
    }

}

