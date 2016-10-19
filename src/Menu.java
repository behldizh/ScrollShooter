import java.awt.*;

/**
 * Created by likanov.k on 18.10.16.
 */
public class Menu {
    //Fields

    private int buttonWidth;
    private int buttonHeight;
    private Color color1;
    private String s;
    private int transparens = 0;
    //Constructor

    public Menu() {
        buttonWidth = 120;
        buttonHeight = 60;
        color1 = Color.cyan;
        s = "Hello World!";

    }

    //Functions

    public void update()
    {
        if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonWidth / 2 &&
                GamePanel.mouseX < GamePanel.WIDTH / 2 + buttonWidth / 2 &&
                GamePanel.mouseY > GamePanel.HEIGHT / 2 - buttonHeight / 2 &&
                GamePanel.mouseY < GamePanel.HEIGHT / 2 + buttonHeight / 2) {
            transparens = 60;
                if(GamePanel.leftMouse){
                    GamePanel.state = GamePanel.STATES.PLAY;
                }
        }
        else
        {
            transparens = 0;
        }
    }

    public void draw(Graphics2D g){
        g.setColor(color1);
        g.setStroke(new BasicStroke(3));
        g.drawRect(GamePanel.WIDTH / 2 - buttonWidth / 2, GamePanel.HEIGHT / 2 - buttonHeight / 2, buttonWidth, buttonHeight);
        g.setColor(new Color(255,255,255,transparens));
        g.fillRect(GamePanel.WIDTH / 2 - buttonWidth / 2, GamePanel.HEIGHT / 2 - buttonHeight / 2, buttonWidth, buttonHeight);
        g.setStroke(new BasicStroke(1));

        g.setColor(color1);
        g.setFont(new Font("Consolas", Font.BOLD, 16));
        long lenght = (int) (g.getFontMetrics().getStringBounds(s, g).getWidth());
        g.drawString(s, GamePanel.WIDTH / 2 - lenght / 2, GamePanel.HEIGHT / 2 + buttonHeight / 4);
    }
}
