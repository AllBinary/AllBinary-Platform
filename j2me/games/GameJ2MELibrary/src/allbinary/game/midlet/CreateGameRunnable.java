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
package allbinary.game.midlet;

import java.util.Hashtable;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import allbinary.graphics.displayable.command.MyCommandsFactory;

public class CreateGameRunnable implements Runnable
{
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
    
    public void run()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_RUNNABLE, this, CommonStrings.getInstance().RUN));

            this.demoGameMidlet.commandAction(
                    MyCommandsFactory.getInstance().SET_DISPLAYABLE,
                    ProgressCanvasFactory.getInstance());

            //ProgressCanvasFactory.getInstance().waitUntilDisplayed();

            this.demoGameMidlet.stopGameCanvasRunnableInterface();

            // mediaInit();

            this.demoGameMidlet.setGameCanvasRunnableInterface(
                    this.demoGameMidlet.createGameCanvasRunnableInterface());

            this.demoGameMidlet.getGameCanvasRunnableInterface().setLoadStateHashtable(hashtable);

            // this.setDisplay((Displayable)
            // this.getGameCanvasRunnableInterface());
            
            this.demoGameMidlet.startGameCanvasRunnableInterface();

            DemoGameMidletEventHandler.getInstance().fireEvent(
                    this.startGameMidletEvent);
            
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END_RUNNABLE, this, CommonStrings.getInstance().RUN));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
        }
    }
}
