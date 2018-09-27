import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Coin extends Sprite
{

    static BufferedImage coin_image = null;
    Model model;
    double vert_vel;
    double horiz_vel;

    Coin(int _x, int _y, Model m)
    {
        name = "coin";
        x = _x;
        y = _y;
        w = 75;
        h = 75;
        model = m;
        vert_vel = 3.14;
        if (coin_image == null)
        {
            try
            {
                coin_image = ImageIO.read(new File("coin.png"));
            } catch (Exception e)
            {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }

    }

    Coin(Json ob)
    {
        super(ob);
    }

    @Override
    public void update()
    {
        vert_vel += 3.143123;
        y += vert_vel;
            x += horiz_vel;
    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(coin_image, x - model.scrollPos, y, w, h, null);
    }
}
