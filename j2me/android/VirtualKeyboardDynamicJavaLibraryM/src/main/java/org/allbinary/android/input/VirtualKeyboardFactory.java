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

import org.allbinary.android.AndroidInfoFactory;
import org.allbinary.android.input.api1.VirtualKeyboardAPI1;
import org.allbinary.android.input.api3.VirtualKeyboardAPI3;

import android.app.Activity;

public class VirtualKeyboardFactory
{
    private static VirtualKeyboard virtualKeyboard = VirtualKeyboard.NULL_VIRTUAL_KEYBOARD;
    
    public static final void init(Activity activity)
    {
        int SDK_VERSION = AndroidInfoFactory.getInstance().getVersion();
        
        //VERSION_CODES.BASE_1_1
        if(SDK_VERSION <= 2)
        {
            virtualKeyboard = new VirtualKeyboardAPI1(activity);
        }
        else
        {
            virtualKeyboard = new VirtualKeyboardAPI3(activity);
        }
    }

    public static final VirtualKeyboard getInstance()
    {
        return virtualKeyboard;
    }
}
