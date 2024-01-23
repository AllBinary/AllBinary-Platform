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

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.graphics.displayable.command.MyCommandsFactory;
import org.allbinary.logic.string.CommonLabels;
import org.allbinary.logic.string.StringMaker;

public class DemoRunnable implements Runnable
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final DemoGameMidlet demoGameMidlet;

    private final DemoGameMidletEvent startDemoGameMidletEvent;
    
    public DemoRunnable(DemoGameMidlet demoGameMidlet)
    {
        this.demoGameMidlet = demoGameMidlet;
        
        this.startDemoGameMidletEvent =
            new DemoGameMidletEvent(this.demoGameMidlet,
                DemoGameMidletStateFactory.getInstance().START_DEMO);
    }
    
    public void run()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(
                    new StringMaker().append(CommonLabels.getInstance().START_LABEL).append("GameCanvasRunnableInterface").toString(),
                    this, commonStrings.RUN));

            this.demoGameMidlet.commandAction(
                    MyCommandsFactory.getInstance().SET_DISPLAYABLE,
                    ProgressCanvasFactory.getInstance());

            //ProgressCanvasFactory.getInstance().waitUntilDisplayed();

            // mediaInit();
            
            this.demoGameMidlet.setGameCanvasRunnableInterface(
                    this.demoGameMidlet.createDemoGameCanvasRunnableInterface());

            this.demoGameMidlet.demoSetup();
            
            // this.setDisplay((Displayable)
            // this.getGameCanvasRunnableInterface());

            DemoGameMidletEventHandler.getInstance().fireEvent(
                    this.startDemoGameMidletEvent);

            this.demoGameMidlet.startGameCanvasRunnableInterface();
            
            this.demoGameMidlet.postDemoSetup();

            LogUtil.put(LogFactory.getInstance(commonStrings.END_RUNNABLE, this, commonStrings.RUN));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
        }

    }
}
