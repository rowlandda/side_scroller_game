public class Mario
{
	//Mario previous pos
	int prevX;
	int prevY;

	int x;
	int y;
	int w = 60;
	int h = 95;
	double vert_vel;
	Model model;

	Mario(Model m)
	{
		model = m;
	}

	void lastPosition()
	{

	}

	boolean doesCollide(int _x, int _y, int _w, int _h)
	{
		if(x + w <= _x)
			return false;
		if(x >= _x + _w)
			return false;
		if(y + h <= _y)
			return false;
		if (y >= _y + _h)
			return false;
		return true;
	}

	void update()
	{
		vert_vel += 3.14159;
		y += vert_vel;
		//make ground stop Mario from falling
		if(y > 500)
		{
			vert_vel = 0.0;
			y= 500; // snap back to the ground
		}

		//collision detection
		for (int i = 0; i < this.model.bricks.size(); i++)
		{
			Brick b = model.bricks.get(i);
			if (doesCollide(b.x, b.y, b.w, b.h))
				System.out.println("Collision!!!!");
			else
				System.out.println(" ");
		}
	}


}


