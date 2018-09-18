import java.util.Iterator;

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
	int frames_since_last_jump;
	boolean left = false;
	Model model;

	Mario(Model m)
	{
		model = m;
		frames_since_last_jump = 0;

	}
	//if collision with a brick occurs this method pushes mario outside said brick
	void pushOut(Brick b)
	{
		//coming from top
		if ( (prevY + h) <= b.y )
		{
			vert_vel = 0.0;
			y = b.y - h - 1;
			frames_since_last_jump = 0;
			return;
		}
		//coming from bottom
		if ( prevY >= b.y + b.h)
		{
			vert_vel = 0.0;
			y = b.y + b.h + 1;
			return;
		}
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
	}

	//checks for a collision between mario sprite and any other rectangle shape
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
		model.scrollPos = x - 150;
		vert_vel += 3.14159;
		y += vert_vel;
		//make ground stop Mario from falling
		if(y > 500)
		{
			vert_vel = 0.0;
			y= 500; // snap back to the ground
			frames_since_last_jump = 0;
		}

		//collision detection
        Iterator<Brick> it = model.bricks.iterator();
		while (it.hasNext())
		{
			Brick b = it.next();
			if (doesCollide(b.x, b.y, b.w, b.h))
				pushOut(b);
		}
		//get the location information and store it so we can have access to the last position
		// to help when pushing mario out of the boxes
        prevX = x;
		prevY = y;
		//this variable helps us control when mario is allowed to jump
		frames_since_last_jump++;
	}


}


