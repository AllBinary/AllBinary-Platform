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

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class GameCanvasPauseRunnable extends GameRunnable
{
    private final AllBinaryGameCanvas allBinaryGameCanvas;
    
    public GameCanvasPauseRunnable(AllBinaryGameCanvas allBinaryGameCanvas)
    {
        this.allBinaryGameCanvas = allBinaryGameCanvas;
    }

    public void run()
    {
    }
    
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
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
        }        
        //allBinaryGameCanvas.processLoopSleep();
    }
}

