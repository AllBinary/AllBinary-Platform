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

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.canvas.Processor;

public class StartIntermissionProcessor extends Processor
{
    private AllBinaryGameCanvas gameCanvas;

    private final int WAIT = 5000;

    public StartIntermissionProcessor(AllBinaryGameCanvas gameCanvas)
    {
        this.gameCanvas = gameCanvas;
    }

    public void process() throws Exception
    {
        if (this.gameCanvas.getStartIntermissionInterface().getTimeDelayHelper().isElapsed(WAIT))
        {
            LogUtil.put(LogFactory.getInstance("Intermission End", this, CommonStrings.getInstance().PROCESS));
            this.gameCanvas.getStartIntermissionInterface().setEnabled(false);
        }
    }
}
