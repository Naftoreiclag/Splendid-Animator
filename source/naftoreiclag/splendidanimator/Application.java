/* Copyright (c) 2014 "Naftoreiclag" https://github.com/Naftoreiclag
 *
 * Distributed under the MIT License (http://opensource.org/licenses/mit-license.html)
 * See accompanying file LICENSE
 */

package naftoreiclag.splendidanimator;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.ARBVertexBufferObject.*;
import static org.lwjgl.opengl.GL11.*;

public class Application
{
	float x = 0, y = 0;
	float rotation = 0;
	
	long lastFrame;
	int fps;
	long lastFPS;
	

	public void run()
	{
		EasyOpenGL dt = new EasyOpenGL(800, 600);
		
		getDelta();
		lastFPS = getTime();

		while (!Display.isCloseRequested())
		{
			input();
			int delta = getDelta();

			update(delta);
			dt.egg();
			renderGL();
		}
		
		if(Display.isCloseRequested())
		{
			dt.cleanup();
		}
	}
	
	public void renderGL()
    {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GL11.glColor3f(0.5f, 0.5f, 1.0f);

            GL11.glPushMatrix();

            GL11.glBegin(GL11.GL_QUADS);
                    GL11.glVertex2f(x - 50, y - 50);
                    GL11.glVertex2f(x + 50, y - 50);
                    GL11.glVertex2f(x + 50, y + 50);
                    GL11.glVertex2f(x - 50, y + 50);
            GL11.glEnd();
            
            GL11.glPopMatrix();
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
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glLoadIdentity();
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		//glOrtho(0.0f, Display.getWidth(), Display.getHeight(), 0.0f, 1.0f, -1.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
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