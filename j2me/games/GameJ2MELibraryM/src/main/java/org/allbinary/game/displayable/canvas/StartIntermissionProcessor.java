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

import org.allbinary.canvas.Processor;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class StartIntermissionProcessor extends Processor
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private AllBinaryGameCanvas gameCanvas;

    private final long WAIT = 5000;

    @JsConstructor
    public StartIntermissionProcessor(AllBinaryGameCanvas gameCanvas)
    {
        this.gameCanvas = gameCanvas;
    }

    @Override
    @JsMethod
    public void process() throws Exception
    {
        if (this.gameCanvas.getStartIntermissionInterface().getTimeDelayHelper().isElapsedTNT(this.WAIT))
        {
            this.logUtil.putF("Intermission End", this, this.commonStrings.PROCESS);
            this.gameCanvas.getStartIntermissionInterface().setEnabled(false);
        }
    }
}
