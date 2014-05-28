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
package org.allbinary.android.input.api3;


import org.allbinary.android.input.VirtualKeyboard;

import abcs.logic.communication.log.ForcedLogUtil;
import allbinary.input.event.VirtualKeyboardEvent;
import allbinary.input.event.VirtualKeyboardEventHandler;
import allbinary.input.event.VirtualKeyboardEventListenerInterface;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;
import android.app.Activity;

public class VirtualKeyboardAPI3 extends VirtualKeyboard
implements VirtualKeyboardEventListenerInterface
{
    public static int MAX_API = Integer.MAX_VALUE;
    public static int MIN_API = 3;

    private boolean virtualKeyboard;
        
    private Activity activity;
    
    private final Runnable showVirtualKeyboardRunnable;
    private final Runnable hideVirtualKeyboardRunnable;
    
    public VirtualKeyboardAPI3(Activity activity)
    {
        super();
        
        this.activity = activity;

        this.showVirtualKeyboardRunnable = new ShowVirtualKeyboardRunnable(this.activity);
        this.hideVirtualKeyboardRunnable = new HideVirtualKeyboardRunnable(this.activity);
        
        VirtualKeyboardEventHandler virtualKeyboardEventHandler = 
                VirtualKeyboardEventHandler.getInstance();
        
        virtualKeyboardEventHandler.removeAllListeners();
        virtualKeyboardEventHandler.addListener(this);
    }
    
    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }

    public void onVirtualKeyboardEvent(VirtualKeyboardEvent virtualKeyboardEvent)
    {
        Boolean isShow = (Boolean) virtualKeyboardEvent.getSource();

        if (isShow.booleanValue()) {
            this.showVirtualKeyboard();
        } else {
            this.hideVirtualKeyboard();
        }         
    }
    
    public void forceHide()
    {
        this.hideVirtualKeyboard();
    }
    
    public void hide()
    {
        if (virtualKeyboard)
        {
            this.forceHide();
        }
    }

    private void showVirtualKeyboard()
    {
        this.activity.runOnUiThread(showVirtualKeyboardRunnable);
        virtualKeyboard = true;
    }

    private void hideVirtualKeyboard()
    {   
        this.activity.runOnUiThread(hideVirtualKeyboardRunnable);
        virtualKeyboard = false;
    }
   
}