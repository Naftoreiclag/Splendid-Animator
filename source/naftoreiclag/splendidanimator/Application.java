package naftoreiclag.splendidanimator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Application extends JFrame
{
	private static final long serialVersionUID = 6474492987861402629L;

	public Application()
	{
		JPanel panel = new JPanel();
		

		JButton button = new JButton("Swag");
		
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		});

		panel.add(button);
		
		this.getContentPane().add(panel);
		this.setTitle("Splendid Animator");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				Application application = new Application();
				application.setVisible(true);
			}
		});
	}
}