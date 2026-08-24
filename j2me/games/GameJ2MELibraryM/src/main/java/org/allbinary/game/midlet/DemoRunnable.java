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

import jsinterop.annotations.JsType;

import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.graphics.displayable.command.MyCommandsFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class DemoRunnable implements Runnable
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final DemoGameMidlet demoGameMidlet;

    private final DemoGameMidletEvent startDemoGameMidletEvent;
    
    @JsConstructor
    public DemoRunnable(DemoGameMidlet demoGameMidlet)
    {
        this.demoGameMidlet = demoGameMidlet;
        
        this.startDemoGameMidletEvent =
            new DemoGameMidletEvent(this.demoGameMidlet,
                DemoGameMidletStateFactory.getInstance().START_DEMO);
    }
    
    @Override
    @JsMethod
    public void run()
    {
        try
        {
            this.logUtil.putF(
                    new StringMaker().append(CommonLabels.getInstance().START_LABEL).append("GameCanvasRunnableInterface").toString(), this, this.commonStrings.RUN);

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

            this.logUtil.putF(this.commonStrings.END_RUNNABLE, this, this.commonStrings.RUN);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.RUN, e);
        }

    }
}
