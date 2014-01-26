/* Copyright (c) 2014 "Naftoreiclag" https://github.com/Naftoreiclag
 *
 * Distributed under the MIT License (http://opensource.org/licenses/mit-license.html)
 * See accompanying file LICENSE
 */

package naftoreiclag.splendidanimator;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Application
{
	public void start()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}

		while (!Display.isCloseRequested())
		{
			Display.update();
		}

		Display.destroy();
	}

	public static void main(String[] args)
	{
		Application application = new Application();
		application.start();
	}
}