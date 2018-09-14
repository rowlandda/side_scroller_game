import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	Model model;
	Image[] mario_images;

	View(Controller c, Model model)
	{
		mario_images = new Image[5];
		c.setView(this);
		this.model = model;
		try
		{
			mario_images[0] = ImageIO.read(new File("mario1.png"));
			mario_images[1] = ImageIO.read(new File("mario2.png"));
			mario_images[2] = ImageIO.read(new File("mario3.png"));
			mario_images[3] = ImageIO.read(new File("mario4.png"));
			mario_images[4] = ImageIO.read(new File("mario5.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	public void paintComponent(Graphics g)
	{
	    //clear screen
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//draw ground
		g.setColor(new Color(15, 200, 64));
		g.fillRect(0, 595, 900, 700);
		//draw bricks
		g.setColor(new Color(0, 0, 0));
		for(int i = 0; i < model.bricks.size(); i++)
		{
			Brick b = model.bricks.get(i);
			g.drawRect(b.x, b.y, b.w, b.h);
		}
		int marioFrame = (Math.abs(model.mario.x) / 20) % 5;
		g.drawImage(this.mario_images[marioFrame], model.mario.x, model.mario.y, null);

	}
}
