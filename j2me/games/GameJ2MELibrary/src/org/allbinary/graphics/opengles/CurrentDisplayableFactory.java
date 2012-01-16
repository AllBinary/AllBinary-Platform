package org.allbinary.graphics.opengles;

import javax.microedition.lcdui.Displayable;

import allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import allbinary.game.displayable.canvas.DemoCanvas;
import allbinary.game.displayable.canvas.GameRunnable;
import allbinary.game.displayable.canvas.NullDisplayable;

public class CurrentDisplayableFactory
{
    private static final CurrentDisplayableFactory instance = new CurrentDisplayableFactory();

    public static CurrentDisplayableFactory getInstance()
    {
        return instance;
    }
    
    private Displayable displayable = NullDisplayable.getInstance();
    private Displayable openGlReadydisplayable = NullDisplayable.getInstance();

    private GameRunnable runnable = GameRunnable.getInstance();
    private GameRunnable usedRunnable = GameRunnable.getInstance();
    
    public void setDisplayable(Displayable displayable)
    {
        this.displayable = displayable;
    }

    public void setOpenGlReadydisplayable(Displayable openGlReadydisplayable)
    {
        //Should not be needed since all resources except for progressimages are loaded after image to imagegl conversion
        //if(RendererStateFactory.getInstance().isSurfaceChanged())
        this.openGlReadydisplayable = openGlReadydisplayable;
        this.update();
    }

    public Displayable getOpenGlReadydisplayable()
    {
        return openGlReadydisplayable;
    }

    public void setRunnable(GameRunnable runnable)
    {
        this.runnable = runnable;

        //this.usedRunnable = runnable;
        this.update();
    }

    public void clearRunnable()
    {
        //PreLogUtil.put("Runnable: " + NullRunnable.getInstance(), this, "clearRunnable");
        this.usedRunnable = GameRunnable.getInstance(); 
    }
    
    private void update()
    {
        synchronized (this)
        {
            if (openGlReadydisplayable instanceof DemoCanvas || 
                    openGlReadydisplayable instanceof AllBinaryGameCanvas)
            {
                //PreLogUtil.put("Runnable: " + runnable, this, CommonStrings.getInstance().UPDATE);
                this.usedRunnable = runnable;
            }
            else
            {
                //PreLogUtil.put("Null Runnable", this, CommonStrings.getInstance().UPDATE);
                this.usedRunnable = GameRunnable.getInstance();
            }
        }
    }

    /*
    public void onDrawFrame(GL10 gl)
    {
        this.usedRunnable.run();

        //Now we're ready to draw some 3D objects

        // gl.glMatrixMode(GL10.GL_MODELVIEW);
        // gl.glLoadIdentity();

        // GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        this.openGlReadydisplayable.draw(gl);

        //x++; if(x > DisplayInfoSingleton.getInstance().getLastWidth()) { x = 0; } 
        //if(this.image != null) this.image.draw(gl, x, y, z);
    }
    */

    public Displayable getDisplayable()
    {
        return displayable;
    }

    public GameRunnable getUsedRunnable()
    {
        return usedRunnable;
    }

}
