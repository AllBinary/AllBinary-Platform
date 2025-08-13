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
package org.allbinary.android.input;

import org.allbinary.input.event.VirtualKeyboardEvent;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;

public class VirtualKeyboard
{
    public static final VirtualKeyboard NULL_VIRTUAL_KEYBOARD = new VirtualKeyboard();
    
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }
    
    public void onVirtualKeyboardEvent(
            VirtualKeyboardEvent virtualKeyboardEvent)
    {
    }
    
    public void forceHide()
    {
    }
    
    public void hide()
    {
    }
}
