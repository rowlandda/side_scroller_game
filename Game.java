import javax.swing.*;
import java.awt.Toolkit;

public class Game extends JFrame
{

	Controller controller;
	Model model;
	View view;
	ImageIcon icon;

	public Game()
	{
	    //set mario program icon
	    icon = new ImageIcon("marioIcon.png");
	    setIconImage(icon.getImage());
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		this.setTitle("Mario");
		this.setSize(1200, 800);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
		model.load("map.json");
	}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}

	public void run()
	{
		try
		{
			Thread.sleep(250);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	while(true)
	{
		controller.update();
		model.update();
		view.repaint(); // Indirectly calls View.paintComponent
		Toolkit.getDefaultToolkit().sync(); // Updates screen

		// Go to sleep for 50 miliseconds
		try
		{
			Thread.sleep(25);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
    }
}
