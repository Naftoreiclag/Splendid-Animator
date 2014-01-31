package naftoreiclag.splendidanimator;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class EasyOpenGL
{
	// Display size
	private int dispW;
	private int dispH;
	
	// Constructor
	public EasyOpenGL(int dispW, int dispH)
	{
		// Set display size
		this.dispW = dispW;
		this.dispH = dispH;
		
		// Try make the display
		try
		{
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		}
		catch (Exception e)
		{
			System.err.println("Could not create display!");
			e.printStackTrace();
		}
		
		// Display settings
		Display.setResizable(true);
		
		// Set up OpenGL view port
		syncViewportAndDisplaySizes();
	}
	
	// resize the display
	public void resizeDisplay(int dispW, int dispH)
	{
		this.dispW = dispW;
		this.dispH = dispH;
	}

	// Send the stuff to GPU
	public void sendStuffToGPU()
	{
		if(Display.wasResized())
		{
			syncViewportAndDisplaySizes();
			System.out.println("window was resized");
		}

		Display.update();
		Display.sync(60);
	}
	
	// Sync stuff
	private void syncViewportAndDisplaySizes()
	{
		glViewport(0, 0, dispW, dispH);
		glLoadIdentity();
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0.0f, dispW, dispH, 0.0f, 1.0f, -1.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	// Cleanup
	public void cleanup()
	{
		// Delete the display
		Display.destroy();
	}
}
