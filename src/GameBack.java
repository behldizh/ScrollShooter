/**
 * Created by likanov.k on 05.10.16.
 */

import java.awt.*;

public class GameBack {

    //Fields
    private Color color;

    //Constructor

    public GameBack(){
        color = Color.BLUE.darker() ;
    }

    //Functions

    public void update() {


    }
    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    }

}
