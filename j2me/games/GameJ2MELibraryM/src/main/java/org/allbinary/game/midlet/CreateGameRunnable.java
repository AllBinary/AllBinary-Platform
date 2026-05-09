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
package org.allbinary.game.midlet;

import java.util.Hashtable;

import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.graphics.displayable.command.MyCommandsFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class CreateGameRunnable implements Runnable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final DemoGameMidlet demoGameMidlet;
    private final Hashtable hashtable;
    
    private final DemoGameMidletEvent startGameMidletEvent;
    
    public CreateGameRunnable(DemoGameMidlet demoGameMidlet, Hashtable hashtable)
    {
        this.demoGameMidlet = demoGameMidlet;
        this.hashtable = hashtable;
        
        this.startGameMidletEvent = new DemoGameMidletEvent(
                this, DemoGameMidletStateFactory.getInstance().START_GAME);        
    }
 
    @Override
    public void run()
    {
        try
        {
            this.logUtil.putF(this.commonStrings.START_RUNNABLE, this, this.commonStrings.RUN);

            final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
            
            this.demoGameMidlet.commandAction(
                    MyCommandsFactory.getInstance().SET_DISPLAYABLE,
                    progressCanvas);

            //progressCanvas.waitUntilDisplayed();

            this.demoGameMidlet.stopGameCanvasRunnableInterface();

            // mediaInit();

            this.demoGameMidlet.setGameCanvasRunnableInterface(
                    this.demoGameMidlet.createGameCanvasRunnableInterface());

            this.demoGameMidlet.getGameCanvasRunnableInterface().setLoadStateHashtable(this.hashtable);

            // this.setDisplay((Displayable)
            // this.getGameCanvasRunnableInterface());
            
            this.demoGameMidlet.startGameCanvasRunnableInterface();

            DemoGameMidletEventHandler.getInstance().fireEvent(
                    this.startGameMidletEvent);
            
            this.logUtil.putF(this.commonStrings.END_RUNNABLE, this, this.commonStrings.RUN);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.RUN, e);
        }
    }
}
