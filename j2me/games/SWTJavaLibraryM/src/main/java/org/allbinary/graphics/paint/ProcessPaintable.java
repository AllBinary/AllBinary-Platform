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
package org.allbinary.graphics.paint;

//This is used instead of interface for performance considerations since interfaces are a little slow

import javax.microedition.lcdui.Display;
import org.microemu.MIDletAccess;
import org.microemu.MIDletBridge;

public class ProcessPaintable extends Paintable
{
    public void process()
    {
        final MIDletAccess midletAccess = MIDletBridge.getMIDletAccess();
        if (midletAccess == null) {
            return;
        }
        final Display.DisplayAccessor displayAccess = (Display.DisplayAccessor) midletAccess.getDisplayAccess();
        if (displayAccess == null) {
            return;
        }
        displayAccess.repaint();
    }
}
