/* Copyright (c) 2014 "Naftoreiclag" https://github.com/Naftoreiclag
 *
 * Distributed under the MIT License (http://opensource.org/licenses/mit-license.html)
 * See accompanying file LICENSE
 */

package naftoreiclag.splendidanimator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

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
		// Set up look and feel
		setLookAndFeel();
		
		// Make our window
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				// Use standard window decoration
				JFrame.setDefaultLookAndFeelDecorated(false);
				
				// Start application
				Application application = new Application();
				application.setVisible(true);
			}
		});
	}

	// Try set it to be Seaglass. If that fails make it Nimbus. If that fails make it Metal.
	public static void setLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		}
		catch (Exception e1)
		{
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
			catch (Exception e2)
			{
				try
				{
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				}
				catch (Exception e3)
				{
					e3.printStackTrace();
				}
			}
		}
	}
}