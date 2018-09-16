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
	static Image[] mario_images = null;
	static BufferedImage background_image = null;
	static BufferedImage brick_image = null;

	View(Controller c, Model model)
	{
		c.setView(this);
		this.model = model;
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
		if (background_image == null)
		{
			try
			{
				background_image = ImageIO.read(new File("background.png"));
			} catch (Exception e)
			{
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
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

	public void paintComponent(Graphics g)
	{
	    //clear screen
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//draw background 4 times
		g.drawImage(background_image, -model.scrollPos/2 - 150, 0, null);
		g.drawImage(background_image, -(model.scrollPos/2) + 1610, 0, null);
		g.drawImage(background_image, -(model.scrollPos/2) + (2*1610), 0, null);
		g.drawImage(background_image, -(model.scrollPos/2) + (3*1610), 0, null);
		//draw ground
		g.setColor(new Color(15, 200, 64));
		g.fillRect(0, 595, 4000, 700);
		//draw bricks
		g.setColor(new Color(0, 0, 0));
		for(int i = 0; i < model.bricks.size(); i++)
		{
			Brick b = model.bricks.get(i);
			g.drawImage(brick_image, b.x - model.scrollPos, b.y, b.w, b.h, null);
		}
		int marioFrame = (Math.abs(model.mario.x) / 20) % 5;
		g.drawImage(this.mario_images[marioFrame], model.mario.x - model.scrollPos, model.mario.y, null);
	}
}
