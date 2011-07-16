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
package allbinary.game.displayable.canvas;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.canvas.Processor;

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
