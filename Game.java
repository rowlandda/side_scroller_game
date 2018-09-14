import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{

	Controller controller;
	Model model;
	View view;

	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		this.setTitle("Mario");
		this.setSize(800, 800);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
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
