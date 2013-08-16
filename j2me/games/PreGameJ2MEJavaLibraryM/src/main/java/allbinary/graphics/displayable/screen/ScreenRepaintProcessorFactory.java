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
package allbinary.graphics.displayable.screen;

import javax.microedition.lcdui.Displayable;

import allbinary.J2MEUtil;
import allbinary.canvas.Processor;

public class ScreenRepaintProcessorFactory {

    private static final ScreenRepaintProcessorFactory instance =
            new ScreenRepaintProcessorFactory();

    /**
     * @return the instance
     */
    public static ScreenRepaintProcessorFactory getInstance()
    {
        return instance;
    }

    private ScreenRepaintProcessorFactory()
    {

    }

    public Processor getInstance(Displayable displayable)
    {
        if(!J2MEUtil.isJ2ME())
        {
            return new ScreenRepaintProcessor(displayable);
        }
        else
        {
            return Processor.getInstance();
        }
    }
}
