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
package org.allbinary.android.input.api1;

import org.allbinary.android.input.VirtualKeyboard;

import org.allbinary.input.event.VirtualKeyboardEventListenerInterface;
import android.app.Activity;
import org.allbinary.input.event.VirtualKeyboardEvent;
import org.allbinary.logic.util.event.AllBinaryEventObject;

public class VirtualKeyboardAPI1 extends VirtualKeyboard
implements VirtualKeyboardEventListenerInterface
{
    public static int MIN_API = 1;
    public static int MAX_API = 2;
    
    public VirtualKeyboardAPI1(final Activity activity)
    {
    }

    //Needed for polyglot do not remove
    @Override
    public void onVirtualKeyboardEvent(final VirtualKeyboardEvent virtualKeyboardEvent)
    {
        super.onVirtualKeyboardEvent(virtualKeyboardEvent);
    }

    //Needed for polyglot do not remove
    @Override
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        super.onEvent(eventObject);
    }

}
