import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

public class Coinblock extends Sprite
{
    static BufferedImage coinblock_full = null;
    static BufferedImage coinblock_empty = null;
    int coins_left;
    Model model;


    Coinblock(int _x, int _y, Model m)
    {
        name = "coinblock";
        coins_left = 5;
        x = _x;
        y = _y;
        w = 89;
        h = 83;
        model = m;
        if (coinblock_full == null)
        {
            try
            {
                coinblock_full = ImageIO.read(new File("coinblockfull.png"));
            } catch (Exception e)
            {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
        if (coinblock_empty == null)
        {
            try
            {
                coinblock_empty = ImageIO.read(new File("coinblockempty.png"));
            } catch (Exception e)
            {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
    }


    Coinblock(Json ob)
    {
        super(ob);
        coins_left = 5;
        if (coinblock_full == null)
        {
            try
            {
                coinblock_full = ImageIO.read(new File("coinblockfull.png"));
            } catch (Exception e)
            {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
        if (coinblock_empty == null)
        {
            try
            {
                coinblock_empty = ImageIO.read(new File("coinblockempty.png"));
            } catch (Exception e)
            {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
    }

    @Override
    public boolean isACoinblock() { return true; }

    @Override
    public void update()
    {
        Iterator<Sprite> it = model.sprites.iterator();
        while (it.hasNext())
        {
            Sprite s = it.next();
            //s.y - 2 because mario's pushout() will keep this from ever happening otherwise
            if ( (doesCollide(s.x, s.y - 2, s.w, s.h)) && (s.isAMario()) )
            {
                coinOut(s);
            }
        }

    }

    void coinOut(Sprite s)
    {
        //coming from under the sprite
        if ( s.prevY <= y + h - 1)
        {
           coins_left--;
        }
    }

    @Override
    public void draw(Graphics g)
    {
        if (coins_left < 1)
            g.drawImage(coinblock_empty, x - model.scrollPos, y, w, h, null);
        else
            g.drawImage(coinblock_full, x - model.scrollPos, y, w, h, null);

    }
}

