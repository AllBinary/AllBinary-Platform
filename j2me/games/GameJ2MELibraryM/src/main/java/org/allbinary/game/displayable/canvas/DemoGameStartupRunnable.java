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
import org.allbinary.thread.ABRunnable;

public class DemoGameStartupRunnable extends ABRunnable
{
    private final DemoCanvas demoCanvas;

    public DemoGameStartupRunnable(DemoCanvas demoCanvas)
    {
        this.demoCanvas = demoCanvas;
    }
    
    public void run()
    {
        try
        {
            this.setRunning(true);
//            final CommonStrings commonStrings = CommonStrings.getInstance();
//            LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.RUN));

            this.demoCanvas.stopGameDemo();

            this.demoCanvas.create();

            this.demoCanvas.start();

            this.setRunning(false);
            
//            LogUtil.put(LogFactory.getInstance(commonStrings.END, this, commonStrings.RUN));
            
        }
        catch (Exception e)
        {
            this.setRunning(false);
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
        }
    }
    
    public static final int TYPE = 4;
    public int getType() {
        return TYPE;
    }
    
}

