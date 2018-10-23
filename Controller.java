import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keySpace;
	int mouseDownX;
	int mouseDownY;

	Controller(Model m)
	{
		model = m;
	}

	public void actionPerformed(ActionEvent e)
	{
	}

	void setView(View v)
	{
		view = v;
	}

	public void mousePressed(MouseEvent e)
	{
		mouseDownX = e.getX();
		mouseDownY = e.getY();	
	}

	public void mouseReleased(MouseEvent e) 
	{
		int x1 = mouseDownX + model.scrollPos;
		int x2 = e.getX() + model.scrollPos;
		int y1 = mouseDownY;
		int y2 = e.getY();
		int left = Math.min(x1, x2);
		int right = Math.max(x1, x2);
		int top = Math.min(y1, y2);
		int bottom = Math.max(y1, y2);
//		//uncomment to add some coinblocks wherever you click
//		Coinblock c = new Coinblock(left, top, model);
//		model.sprites.add(c);
//		//uncomment to add some bricks
//		Brick b = new Brick(left, top, right - left, bottom - top, model);
//		model.sprites.add(b)
	}
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: keySpace = true; break;
			case KeyEvent.VK_S: model.save("map.json"); break;
			case KeyEvent.VK_L: model.load("map.json"); break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE: keySpace = false; break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		if(keyRight)
		{
			model.getMario().right();
		}
		if(keyLeft)
		{
		    model.getMario().left();
		}
		if(keySpace)
		{
        	model.getMario().jump();
		}
	}
}
