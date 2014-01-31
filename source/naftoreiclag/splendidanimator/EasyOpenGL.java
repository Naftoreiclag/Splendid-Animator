/* Copyright (c) 2014 "Naftoreiclag" https://github.com/Naftoreiclag
 *
 * Distributed under the MIT License (http://opensource.org/licenses/mit-license.html)
 * See accompanying file LICENSE
 */

package naftoreiclag.splendidanimator;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class EasyOpenGL
{
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
	
	// Send the stuff to GPU
	protected static void sendStuffToGPU()
	{
		// If the view port is resized
		if (Display.wasResized())
		{
			// Re-sync
			syncViewportAndDisplaySizes();
			
			System.out.println("egerafwefa");
		}
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
