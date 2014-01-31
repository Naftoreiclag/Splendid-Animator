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

	public void run()
	{
		EasyOpenGL dt = new EasyOpenGL(800, 600);
		

		while (!Display.isCloseRequested())
		{
			input();
			update(1);
			dt.sendStuffToGPU();
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