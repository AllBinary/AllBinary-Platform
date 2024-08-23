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
import org.allbinary.game.displayable.canvas.GameInputMappingCanvas;
import org.allbinary.game.displayable.canvas.GameInputMappingInstructionsCanvas;
import org.allbinary.game.displayable.canvas.GameRunnable;
import org.allbinary.game.displayable.canvas.NullDisplayable;
import org.allbinary.game.displayable.canvas.NullWaitGameRunnable;
import org.allbinary.game.layer.SWTUtil;
import org.allbinary.game.score.displayable.HighScoresCanvas;
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.thread.NullRunnable;
import org.allbinary.util.BasicArrayList;

public class CurrentDisplayableFactory
{
    private static final CurrentDisplayableFactory instance = new CurrentDisplayableFactory();

    public static CurrentDisplayableFactory getInstance()
    {
        return instance;
    }
    
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final CanvasStrings canvasStrings = CanvasStrings.getInstance();

    private final String SET_DISPLAYABLE = "setDisplayable";
    private final String SET_RUNNABLE = "setRunnable";
    private final String CLEAR_RUNNABLE = "clearRunnable";
    private final String SET_USED_RUNNABLE = "setUsedRunnable";
    
    private final String RUNNABLE = "Runnable: ";
    private final String RUNNABLE_WAS_ALREADY_IN_USE = "Runnable was already in use: ";

    public final BasicArrayList DEFAULT_CHILD_NAME_LIST = new BasicArrayList();

    private Displayable displayable = NullDisplayable.getInstance();
    private Displayable openGlReadydisplayable = NullDisplayable.getInstance();

    private GameRunnable runnable = NullWaitGameRunnable.getInstance();
    private GameRunnable usedRunnable = NullWaitGameRunnable.getInstance();

    private CurrentDisplayableFactory() {
        this.DEFAULT_CHILD_NAME_LIST.add(this.canvasStrings.ABOUT);
        this.DEFAULT_CHILD_NAME_LIST.add(this.canvasStrings.OPTIONS);
        this.DEFAULT_CHILD_NAME_LIST.add(GameInputMappingCanvas.NAME);
        this.DEFAULT_CHILD_NAME_LIST.add(GameInputMappingInstructionsCanvas.NAME);
        this.DEFAULT_CHILD_NAME_LIST.add(HighScoresCanvas.NAME);
    }
    
    public void setMyCanvas(final MyCanvas displayable)
    {
        if(this.displayable instanceof MyCanvas) {
            if (displayable.hasChild((MyCanvas) this.displayable)) {
                PreLogUtil.put(new StringMaker().append("Child Displayable is already set for: ").append(displayable).toString(), this, SET_DISPLAYABLE);
                return;
            }
        }
        
        this.setDisplayable(displayable);

    }

    public void setDisplayable(final Displayable displayable)
    {
        //ForcedLogUtil.log(SET_DISPLAYABLE + displayable, this);
        this.displayable = displayable;
        this.setOpenGlReadydisplayable(displayable);

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
        PreLogUtil.put(new StringMaker().append(RUNNABLE).append(runnable).toString(), this, SET_RUNNABLE);
        this.runnable = runnable;

        if(this.usedRunnable != this.runnable) {
            //this.usedRunnable = runnable;
            this.update();
        } else {
            PreLogUtil.put(new StringMaker().append(RUNNABLE_WAS_ALREADY_IN_USE).append(runnable).toString(), this, SET_RUNNABLE);
        }
    }

    public void clearRunnable()
    {
        PreLogUtil.put(new StringMaker().append(RUNNABLE).append(NullRunnable.getInstance()).toString(), this, CLEAR_RUNNABLE);
        this.setUsedRunnable(NullWaitGameRunnable.getInstance()); 
    }
    
    private void update()
    {
        synchronized (this)
        {
            final CommonSeps commonSeps = CommonSeps.getInstance();
            
            final Features features = Features.getInstance();

            final StringMaker stringMaker = new StringMaker();
            stringMaker.append(openGlReadydisplayable);
            if (SWTUtil.isSWT && !features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL)) {
                PreLogUtil.put(stringMaker.append(commonSeps.SPACE).append(SWTUtil.SWT).append(commonSeps.SPACE).append(RUNNABLE).append(NullRunnable.getInstance()).toString(), this, commonStrings.UPDATE);
                this.setUsedRunnable(NullWaitGameRunnable.getInstance());
            } else if (features.isDefault(HTMLFeatureFactory.getInstance().HTML))
            {
                PreLogUtil.put(stringMaker.append(commonSeps.SPACE).append(HTMLFeatureFactory.getInstance().HTML).append(commonSeps.SPACE).append(RUNNABLE).append(runnable).toString(), this, commonStrings.UPDATE);
                this.setUsedRunnable(runnable);
            } else if (openGlReadydisplayable instanceof DemoCanvas || 
                    openGlReadydisplayable instanceof AllBinaryGameCanvas ||
                features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL))
            {
                PreLogUtil.put(stringMaker.append(commonSeps.SPACE).append(OpenGLFeatureFactory.getInstance().OPENGL).append(commonSeps.SPACE).append(RUNNABLE).append(runnable).toString(), this, commonStrings.UPDATE);
                this.setUsedRunnable(runnable);
            }
            else
            {
                PreLogUtil.put(stringMaker.append(RUNNABLE).append(NullRunnable.getInstance()).toString(), this, commonStrings.UPDATE);
                this.setUsedRunnable(NullWaitGameRunnable.getInstance());
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
        PreLogUtil.put(new StringMaker().append(RUNNABLE).append(runnable).toString(), this, SET_USED_RUNNABLE);
        this.usedRunnable = usedRunnable;
    }

}
