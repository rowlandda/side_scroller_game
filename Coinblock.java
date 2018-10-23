import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Coinblock extends Sprite
{
    static BufferedImage coinblock_full = null;
    static BufferedImage coinblock_empty = null;
    int coins_left;
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

    //copy constructor
    Coinblock(Coinblock copy, Model newModel)
    {
        super(copy, newModel);
        coins_left = copy.coins_left;
        frames_since_last_coin = copy.frames_since_last_coin;
        model = newModel;
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

    public Coinblock cloneme(Sprite copy, Model newModel)
    {
        Coinblock c = new Coinblock((Coinblock)copy, newModel);
        return c;
    }

    Coinblock(Json ob, Model m)
    {
        super(ob, m);
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
            if ((doesCollide(s.x , s.y - 2, s.w, s.h)) && (s.isAMario()))
            {
                Mario m = (Mario) s;
                coinOut(m);
            }
        }
        frames_since_last_coin++;

    }

    void coinOut(Mario m)
    {
        //make sure we are coming from under the sprite and we have coins
        if ( ( m.prevY < y + h + 2) && (m.x < x + w) && (m.x + m.w > x) &&
                (frames_since_last_coin > 5) && (coins_left > 0) )
        {
            coins_left--;
            frames_since_last_coin = 0;
            //add a new coin directly above the coinblock
            Coin c = new Coin(x, y-75, model);
            model.sprites.add(c);
            m.coins++;
            //make coin pop out randomly
            c.vert_vel = -18.3;
            c.horiz_vel = rand.nextDouble() * 16 - 8;
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

