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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class GameCanvasPauseRunnable extends GameRunnable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final AllBinaryGameCanvas allBinaryGameCanvas;
    
    public GameCanvasPauseRunnable(AllBinaryGameCanvas allBinaryGameCanvas)
    {
        this.allBinaryGameCanvas = allBinaryGameCanvas;
    }

    @Override
    public void run()
    {
    }
    
    @Override
    public void processLoopSleep()
    throws Exception
    {
        try
        {
            allBinaryGameCanvas.processSleep();
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
        }        
        //allBinaryGameCanvas.processLoopSleep();
    }
}

