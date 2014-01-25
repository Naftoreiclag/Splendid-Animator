package naftoreiclag.splendidanimator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main extends JFrame
{
	private static final long serialVersionUID = 6474492987861402629L;

	public Main()
	{
		JPanel panel = new JPanel();
		//panel.setLayout(null);
		
		this.getContentPane().add(panel);

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

		setTitle("Cool application");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args)
	{
		/*
		try
		{
			for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
			{
				if(info.getName().equals("Nimbus"))
				{
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch(Exception e)
		{
			try
			{
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			}
			catch(Exception ex)
			{
			}
		}
		*/

		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				Main application = new Main();
				application.setVisible(true);
			}
		});
	}
}