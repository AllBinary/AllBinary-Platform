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
package org.allbinary.game.displayable.canvas;

import jsinterop.annotations.JsType;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.time.GameTickTimeDelayHelper;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class DemoGameRunnable extends GameRunnable
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final DemoCanvas demoCanvas;
    
    @JsConstructor
    public DemoGameRunnable(DemoCanvas demoCanvas)
    {
        this.demoCanvas = demoCanvas;
    }

    private final GameTickTimeDelayHelper gameTickTimeDelayHelper = GameTickTimeDelayHelperFactory.getInstance();

    @Override
    @JsMethod
    public void run()
    {
        try
        {
            //this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.RUN);

            //final AllBinaryGameCanvas allBinaryGameCanvas = this.allBinaryGameCanvas;
            
            this.demoCanvas.getLoopTimeHelperP().setStartTime(this.gameTickTimeDelayHelper.setStartTime());

            this.demoCanvas.processGame();
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION,this, commonStrings.RUN, e);
        }
    }
    
    @Override
    @JsMethod
    public void processLoopSleep()
    throws Exception
    {
        this.demoCanvas.processLoopSleep();
    }
}

