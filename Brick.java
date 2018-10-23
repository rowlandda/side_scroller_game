import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Brick extends Sprite
{
	static BufferedImage brick_image = null;

	Brick(int _x, int _y, int _w, int _h, Model m)
	{
	    name = "brick";
	    x = _x;
	    y = _y;
	    w = _w;
	    h = _h;
	    model = m;
		if (brick_image == null)
		{
			try
			{
				brick_image = ImageIO.read(new File("bricks.png"));
			} catch (Exception e)
			{
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
	}

	Brick(Brick copy, Model newModel)
	{
		super(copy, newModel);
		model = newModel;
		if (brick_image == null)
		{
			try
			{
				brick_image = ImageIO.read(new File("bricks.png"));
			} catch (Exception e)
			{
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
	}

	public Brick cloneme(Sprite copy, Model newModel)
	{
		Brick b = new Brick((Brick)copy, newModel);
		return b;
	}

	Brick(Json ob, Model m)
	{
		super(ob, m);
		if (brick_image == null)
		{
			try
			{
				brick_image = ImageIO.read(new File("bricks.png"));
			} catch (Exception e)
			{
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
	}

	@Override
	public boolean isABrick() { return true; }

	public void update()
	{

	}

	public void draw(Graphics g)
	{
		g.drawImage(brick_image, x - model.scrollPos, y, w, h, null);
	}
}
	
