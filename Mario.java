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

	void pushOut(Brick b)
	{
		//coming from left side
		if ( prevX <= b.x )
		{
			x = b.x - w - 1;
			return;
		}
		//coming from right
		if ( prevX >= b.x + b.w)
		{
			x = b.x + b.w + 1;
			return;
		}
		//coming from bottom
		if ( prevY >= b.y + b.h)
		{
			vert_vel = 0.0;
			y = b.y + b.h + 1;
			return;
		}
		//coming from top
        if ( (prevY + h) <= b.y )
		{
		    vert_vel = 0.0;
		    y = b.y - h - 1;
		    return;
		}

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
			{
				pushOut(b);
			}
		}

		//get the location information and store it so we can have access to the last position
		// to help when pushing mario out of the boxes
        prevX = x;
		prevY = y;
	}


}


