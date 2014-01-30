/* Copyright (c) 2014 "Naftoreiclag" https://github.com/Naftoreiclag
 *
 * Distributed under the MIT License (http://opensource.org/licenses/mit-license.html)
 * See accompanying file LICENSE
 */

package naftoreiclag.splendidanimator;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Application
{
	float x = 0, y = 0;
	float rotation = 0;
	
	long lastFrame;
	int fps;
	long lastFPS;
	
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

		initGL();
		
		getDelta();
		lastFPS = getTime();

		Display.setResizable(true);

		while (!Display.isCloseRequested())
		{
			input();
			int delta = getDelta();
			
			update(delta);

			Display.update();
			Display.sync(60);
			
		}
		
		if (Display.isCloseRequested())
		{
			Display.destroy();
		}
	}
	
	public void update(int delta)
	{
		rotation += 0.15f * delta;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) x -= 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) x += 0.35f * delta;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) y -= 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) y += 0.35f * delta;
		
		if (x < 0) x = 0;
		if (x > 800) x = 800;
		if (y < 0) y = 0;
		if (y > 600) y = 600;
		
		updateFPS();
	}
	
	public int getDelta()
	{
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	public long getTime()
	{
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public void updateFPS()
	{
		if (getTime() - lastFPS > 1000)
		{
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps ++;
	}

	public void initGL()
	{
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		GL11.glLoadIdentity();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0f, Display.getWidth(), Display.getHeight(), 0.0f, 1.0f, -1.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
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
		/*
		(new Thread(new Runnable()
        {
            @Override
            public void run()
            {
            	Application application = new Application();
        		application.run();
            }
        })).start();*/
		
		Application application = new Application();
		application.run();
	}
}