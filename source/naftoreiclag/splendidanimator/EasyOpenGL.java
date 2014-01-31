/* Copyright (c) 2014 "Naftoreiclag" https://github.com/Naftoreiclag
 *
 * Distributed under the MIT License (http://opensource.org/licenses/mit-license.html)
 * See accompanying file LICENSE
 */

package naftoreiclag.splendidanimator;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class EasyOpenGL
{
	// Shapes
	private static List<RenderThingy> shapes = new LinkedList();
	
	// Initializer
	protected static void initialize(int dispW, int dispH) throws LWJGLException
	{
		// Make display
		Display.setDisplayMode(new DisplayMode(800, 600));
		Display.setResizable(true);
		Display.create();
		
		// Set up OpenGL view port
		syncViewportAndDisplaySizes();
	}
	
	// Send the stuff to GPU and put on display
	protected static void updateDisplay()
	{
		// If the view port is resized
		if (Display.wasResized())
		{
			// Re-sync
			syncViewportAndDisplaySizes();
		}
		
		//
		clearGPU();
		
		// Draw stuff
		sendStuffToGPU();
		
		// Update the display
		Display.update();
		
		// Make sure we go at 60 FPS
		Display.sync(60);
	}
	
	//
	private static void clearGPU()
	{
        	GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}

	//
	private static void sendStuffToGPU()
	{
		GL11.glColor3f(0.5f, 1.0f, 0.5f);
		
		GL11.glPushMatrix();
		
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f(0, 0);
				GL11.glVertex2f(EasyOpenGL.getDisplayWidth(), 0);
				GL11.glVertex2f(EasyOpenGL.getDisplayWidth(), EasyOpenGL.getDisplayHeight());
				GL11.glVertex2f(0, EasyOpenGL.getDisplayHeight());
			GL11.glEnd();
		
		GL11.glPopMatrix();
	}
	
	//
	public static void registerShape(RenderThingy shape)
	{
		shapes.add(shape);
	}
	
	//
	public static void unregisterShape(RenderThingy shape)
	{
		shapes.remove(shape);
	}

	// Cleanup
	protected static void cleanup()
	{
		// Delete the display
		Display.destroy();
	}

	// Resize the display
	public static void resizeDisplay(int dispW, int dispH) throws LWJGLException
	{
		Display.setDisplayMode(new DisplayMode(dispW, dispH));
	}
	
	//
	public static int getDisplayWidth()
	{
		return Display.getWidth();
	}
	
	//
	public static int getDisplayHeight()
	{
		return Display.getHeight();
	}

	// Sync stuff
	private static void syncViewportAndDisplaySizes()
	{
		// Get the target size
		final int dispW = Display.getWidth();
		final int dispH = Display.getHeight();
		
		// Set image output size
		glViewport(0, 0, dispW, dispH);
		glLoadIdentity();
		
		// Reset projection matrix
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		
		// Set the coordinates of the display
		glOrtho(0.0f, dispW, dispH, 0.0f, 1.0f, -1.0f);
		
		// Reset model view
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
}
