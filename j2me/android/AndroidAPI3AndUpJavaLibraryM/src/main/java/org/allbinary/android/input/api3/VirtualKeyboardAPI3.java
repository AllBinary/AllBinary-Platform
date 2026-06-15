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


import android.app.Activity;
import org.allbinary.android.input.VirtualKeyboard;
import org.allbinary.input.event.VirtualKeyboardEvent;
import org.allbinary.input.event.VirtualKeyboardEventHandler;
import org.allbinary.input.event.VirtualKeyboardEventListenerInterface;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;

public class VirtualKeyboardAPI3 extends VirtualKeyboard
implements VirtualKeyboardEventListenerInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public static int MAX_API = Integer.MAX_VALUE;
    public static int MIN_API = 3;

    private boolean virtualKeyboard;
        
    private Activity activity;
    
    private final Runnable showVirtualKeyboardRunnable;
    private final Runnable hideVirtualKeyboardRunnable;
    
    public VirtualKeyboardAPI3(final Activity activity)
    {
        super();
        
        this.activity = activity;

        this.showVirtualKeyboardRunnable = new ShowVirtualKeyboardRunnable(this.activity);
        this.hideVirtualKeyboardRunnable = new HideVirtualKeyboardRunnable(this.activity);
        
        final VirtualKeyboardEventHandler virtualKeyboardEventHandler = 
                VirtualKeyboardEventHandler.getInstance();
        
        virtualKeyboardEventHandler.removeAllListeners();
        virtualKeyboardEventHandler.addListenerInterface(this);
    }
    
    @Override
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    @Override
    public void onVirtualKeyboardEvent(final VirtualKeyboardEvent virtualKeyboardEvent)
    {
        final Boolean isShow = (Boolean) virtualKeyboardEvent.getSource();

        if (isShow.booleanValue()) {
            this.showVirtualKeyboard();
        } else {
            this.hideVirtualKeyboard();
        }         
    }
    
    @Override
    public void forceHide()
    {
        this.hideVirtualKeyboard();
    }
    
    @Override
    public void hide()
    {
        if (this.virtualKeyboard)
        {
            this.forceHide();
        }
    }

    private void showVirtualKeyboard()
    {
        this.activity.runOnUiThread(this.showVirtualKeyboardRunnable);
        this.virtualKeyboard = true;
    }

    private void hideVirtualKeyboard()
    {   
        this.activity.runOnUiThread(this.hideVirtualKeyboardRunnable);
        this.virtualKeyboard = false;
    }
   
}