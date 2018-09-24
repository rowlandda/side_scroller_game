import java.awt.*;
import java.util.Iterator;
import javax.imageio.ImageIO;
import java.io.File;

public class Mario extends Sprite
{
	//Mario previous pos
	int prevX;
	int prevY;
	double vert_vel;
	int frames_since_last_jump;
	boolean left = false;
	static Image[] mario_images = null;
	Model model;

	Mario(Model m)
	{
		name = "mario";
		x = 0;
		y = 0;
		w = 60;
		h = 95;
		model = m;
		frames_since_last_jump = 0;
		if (mario_images == null)
		{
			mario_images = new Image[5];
			try
			{
				mario_images[0] = ImageIO.read(new File("mario1.png"));
				mario_images[1] = ImageIO.read(new File("mario2.png"));
				mario_images[2] = ImageIO.read(new File("mario3.png"));
				mario_images[3] = ImageIO.read(new File("mario4.png"));
				mario_images[4] = ImageIO.read(new File("mario5.png"));
			} catch (Exception e) {
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}

	}
	Mario(Json ob)
	{
		super(ob);
	}
	//if collision with a sprite occurs and you want mario to not clip into it
	// use this method to keep him out
	void pushOut(Sprite s)
	{
		//coming from top
		if ( (prevY + h) <= s.y )
		{
			vert_vel = 0.0;
			y = s.y - h - 1;
			frames_since_last_jump = 0;
			return;
		}
		//coming from bottom
		if ( prevY >= s.y + s.h)
		{
			vert_vel = 0.0;
			y = s.y + s.h + 1;
			return;
		}
		//coming from left side
		if ( prevX <= s.x )
		{
			x = s.x - w - 1;
			return;
		}
		//coming from right
		if ( prevX >= s.x + s.w)
		{
			x = s.x + s.w + 1;
			return;
		}
	}

	public void update()
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
        Iterator<Sprite> it = model.sprites.iterator();
		while (it.hasNext())
		{
			Sprite s = it.next();
			if ( (doesCollide(s.x, s.y, s.w, s.h)) && (s.isABrick()) )
			{
				pushOut(s);
			}
		}
		//get the location information and store it so we can have access to the last position
		// to help when pushing mario out of the boxes
        prevX = x;
		prevY = y;
		//this variable helps us control when mario is allowed to jump
		frames_since_last_jump++;
	}

	public void draw(Graphics g)
	{
		//get the index of the mario animation array
		int marioFrame = (Math.abs(x) / 20) % 5;
		//if going left flip the mario image
		if (left)
			g.drawImage(this.mario_images[marioFrame], model.mario.x - model.scrollPos + model.mario.w, model.mario.y, -model.mario.w, model.mario.h, null);
		else
			g.drawImage(this.mario_images[marioFrame], model.mario.x - model.scrollPos, model.mario.y, model.mario.w, model.mario.h, null);

	}
}


