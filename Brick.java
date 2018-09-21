import java.awt.*;
import java.awt.image.BufferedImage;

public class Brick extends Sprite
{
	int x;
	int y;
	int w;
	int h;

	Brick(int _x, int _y, int _w, int _h)
	{
		x = _x;
		y = _y;
		w = _w;
		h = _h;
	}

	public void update()
	{

	}

	public void draw(Graphics g)
	{

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
	
