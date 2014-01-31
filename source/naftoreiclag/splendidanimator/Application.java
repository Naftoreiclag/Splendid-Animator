/* Copyright (c) 2014 "Naftoreiclag" https://github.com/Naftoreiclag
 *
 * Distributed under the MIT License (http://opensource.org/licenses/mit-license.html)
 * See accompanying file LICENSE
 */

package naftoreiclag.splendidanimator;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Application
{
	float x = 0, y = 0;
	float rotation = 0;

	public void run()
	{
		try
		{
			EasyOpenGL.initialize(800, 600);
		} catch (LWJGLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		while (!Display.isCloseRequested())
		{
			EasyOpenGL.updateDisplay();
			input();
			update(20);
			renderGL();
		}
		
		if(Display.isCloseRequested())
		{
			EasyOpenGL.cleanup();
		}
	}
	
	public void renderGL()
    {
            GL11.glColor3f(0.5f, 0.5f, 1.0f);

            GL11.glPushMatrix();

            GL11.glBegin(GL11.GL_QUADS);
                    GL11.glVertex2f(x - 50, y - 50);
                    GL11.glVertex2f(x + 50, y - 50);
                    GL11.glVertex2f(x + 50, y + 50);
                    GL11.glVertex2f(x - 50, y + 50);
            GL11.glEnd();

            
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