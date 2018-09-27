import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.Random;

public class Coinblock extends Sprite
{
    static BufferedImage coinblock_full = null;
    static BufferedImage coinblock_empty = null;
    int coins_left;
    Model model;
    //this takes care of a bug of mario getting more than one coin per bump
    int frames_since_last_coin;
    static Random rand = new Random();


    Coinblock(int _x, int _y, Model m)
    {
        name = "coinblock";
        coins_left = 5;
        frames_since_last_coin = 0;
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
        frames_since_last_coin = 0;
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
    public boolean isABrick() { return true; }

    @Override
    public void update()
    {
        for (int i = 0; i < model.sprites.size(); i++)
        {
            Sprite s = model.sprites.get(i);
            //s.y - 2 because mario's pushout() will keep this from ever happening otherwise
            if ((doesCollide(s.x - 2, s.y - 2, s.w, s.h)) && (s.isAMario()))
            {
                coinOut(s);
            }
        }
        frames_since_last_coin++;

    }

    void coinOut(Sprite s)
    {
        //coming from under the sprite and we have coins
        if ( ( s.prevY < y + h - 2) && (frames_since_last_coin > 5) && (coins_left > 0))
        {
            coins_left--;
            frames_since_last_coin = 0;
            Coin c = new Coin(x, y-75, model);
            model.sprites.add(c);
            c.vert_vel = -18.3;
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

