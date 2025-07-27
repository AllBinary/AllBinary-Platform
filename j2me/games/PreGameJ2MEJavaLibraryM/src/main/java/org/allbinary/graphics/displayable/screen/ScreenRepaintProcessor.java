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
package org.allbinary.graphics.displayable.screen;

import javax.microedition.lcdui.Displayable;

import org.allbinary.canvas.Processor;

public class ScreenRepaintProcessor extends Processor
{
    private final Displayable displayable;

    public ScreenRepaintProcessor(Displayable displayable)
    {
        this.displayable = displayable;
    }

    @Override
    public void process() throws Exception
    {
        ScreenRepaintUtil.repaint(displayable);
    }
}
