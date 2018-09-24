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
	static BufferedImage background_image = null;
	static BufferedImage ground_image = null;

	View(Controller c, Model model)
	{
		c.setView(this);
		this.model = model;
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
		if (ground_image == null)
		{
			try
			{
				ground_image = ImageIO.read(new File("ground.jpg"));
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
		//draw background 15 times
        for (int i = 0; i < 15; i++)
            g.drawImage(background_image, -model.scrollPos/2 + (1600*i) - 150, 0, null);
		//draw ground
		g.setColor(new Color(200, 77, 40));
		g.fillRect(0, 595, 4000, 700);
		for (int i = 0; i < 15; i++)
            g.drawImage(ground_image, -model.scrollPos + (i*1200)- 151, 595, null);
		//draw sprites
		for (int i = 0; i < model.sprites.size(); i++)
		{
			Sprite s = model.sprites.get(i);
			s.draw(g);
		}
	}
}
