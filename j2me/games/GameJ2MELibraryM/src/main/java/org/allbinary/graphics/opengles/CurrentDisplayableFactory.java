/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.graphics.opengles;

import javax.microedition.lcdui.Displayable;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.HTMLFeatureFactory;

import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.displayable.canvas.DemoCanvas;
import org.allbinary.game.displayable.canvas.GameRunnable;
import org.allbinary.game.displayable.canvas.NullDisplayable;
import org.allbinary.game.displayable.canvas.NullWaitRunnable;
import org.allbinary.game.layer.SWTUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.thread.NullRunnable;

public class CurrentDisplayableFactory
{
    private static final CurrentDisplayableFactory instance = new CurrentDisplayableFactory();

    public static CurrentDisplayableFactory getInstance()
    {
        return instance;
    }
    
    private Displayable displayable = NullDisplayable.getInstance();
    private Displayable openGlReadydisplayable = NullDisplayable.getInstance();

    private GameRunnable runnable = NullWaitRunnable.getInstance();
    private GameRunnable usedRunnable = NullWaitRunnable.getInstance();

    private final String RUNNABLE = "Runnable: ";
    
    public void setDisplayable(final Displayable displayable)
    {
        this.displayable = displayable;
    }

    public void setOpenGlReadydisplayable(final Displayable openGlReadydisplayable)
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

    public void setRunnable(final GameRunnable runnable)
    {
        PreLogUtil.put(new StringMaker().append(RUNNABLE).append(runnable).toString(), this, "setRunnable");
        this.runnable = runnable;

        //this.usedRunnable = runnable;
        this.update();
    }

    public void clearRunnable()
    {
        PreLogUtil.put(new StringMaker().append(RUNNABLE).append(NullRunnable.getInstance()).toString(), this, "clearRunnable");
        this.setUsedRunnable(NullWaitRunnable.getInstance()); 
    }
    
    private void update()
    {
        synchronized (this)
        {
            final Features features = Features.getInstance();

            if (SWTUtil.isSWT && !features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL)) {
                PreLogUtil.put(new StringMaker().append("SWT ").append(RUNNABLE).append(NullRunnable.getInstance()).toString(), this, CommonStrings.getInstance().UPDATE);
                this.setUsedRunnable(NullWaitRunnable.getInstance());
            } else if (features.isDefault(HTMLFeatureFactory.getInstance().HTML))
            {
                PreLogUtil.put(new StringMaker().append("HTML").append(RUNNABLE).append(runnable).toString(), this, CommonStrings.getInstance().UPDATE);
                this.setUsedRunnable(runnable);
            } else if (openGlReadydisplayable instanceof DemoCanvas || 
                    openGlReadydisplayable instanceof AllBinaryGameCanvas)
            {
                PreLogUtil.put(new StringMaker().append("Displayable: ").append(openGlReadydisplayable).append(RUNNABLE).append(runnable).toString(), this, CommonStrings.getInstance().UPDATE);
                this.setUsedRunnable(runnable);
            }
            else
            {
                PreLogUtil.put(new StringMaker().append(openGlReadydisplayable).append(RUNNABLE).append(NullRunnable.getInstance()).toString(), this, CommonStrings.getInstance().UPDATE);
                this.setUsedRunnable(NullWaitRunnable.getInstance());
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

    /**
     * @param usedRunnable the usedRunnable to set
     */
    public void setUsedRunnable(GameRunnable usedRunnable)
    {
        PreLogUtil.put(new StringMaker().append(RUNNABLE).append(runnable).toString(), this, "setUsedRunnable");
        this.usedRunnable = usedRunnable;
    }

}
