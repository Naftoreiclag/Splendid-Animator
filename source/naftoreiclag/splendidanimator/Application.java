/* Copyright (c) 2014 "Naftoreiclag" https://github.com/Naftoreiclag
 *
 * Distributed under the MIT License (http://opensource.org/licenses/mit-license.html)
 * See accompanying file LICENSE
 */

package naftoreiclag.splendidanimator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Application
{
	public void run()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		}
		catch (Exception e)
		{
			System.err.println("Could not create display!");
			e.printStackTrace();
			System.exit(0);
		}
		
		Display.setResizable(true);

		while (!Display.isCloseRequested())
		{
			input();
			Display.update();
		}

		Display.destroy();
	}

	public void input()
	{
		if (Mouse.isButtonDown(0))
		{
			int x = Mouse.getX();
			int y = Mouse.getY();

			System.out.println("mouse down x: " + x + " y: " + y);
		}
	}

	public static void main(String[] args)
	{
		Application application = new Application();
		application.run();
	}
}