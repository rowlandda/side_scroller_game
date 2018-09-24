import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Brick extends Sprite
{
	static BufferedImage brick_image = null;
	Model model;

	Brick(int _x, int _y, int _w, int _h, Model m)
	{
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

	@Override
	public boolean isABrick() { return true; }

	public void update()
	{

	}

	public void draw(Graphics g)
	{
		for(int i = 0; i < model.sprites.size(); i++)
		{
			if (model.sprites.get(i).isABrick())
			{
				Sprite s = model.sprites.get(i);
				g.drawImage(brick_image, s.x - model.scrollPos, s.y, s.w, s.h, null);
			}
		}

	}

	//create brick from a json representation of one
	Brick(Json ob)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
	}
	//save brick position data as json file
	public Json marshall()
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		return ob;
	}	

}
	
