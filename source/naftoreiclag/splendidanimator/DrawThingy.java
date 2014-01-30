package naftoreiclag.splendidanimator;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class DrawThingy
{
	private int dispW;
	private int dispH;
	
	public DrawThingy(int dispW, int dispH)
	{
		this.dispW = dispW;
		this.dispH = dispH;
		
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
		
		updateDumbSyncStuff();
	}
	
	public void resize(int dispW, int dispH)
	{
		this.dispW = dispW;
		this.dispH = dispH;
	}

	public void egg()
	{
		if(Display.wasResized())
		{
			updateDumbSyncStuff();
			System.out.println("window was resized");
		}

		Display.update();
		Display.sync(60);
	}
	
	private void updateDumbSyncStuff()
	{
		glViewport(0, 0, dispW, dispH);
		glLoadIdentity();
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0.0f, dispW, dispH, 0.0f, 1.0f, -1.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	public void cleanup()
	{
		Display.destroy();
	}
}
