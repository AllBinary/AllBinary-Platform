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


import org.allbinary.android.activity.ViewCompositeInterface;
import org.allbinary.android.input.VirtualKeyboard;

import abcs.logic.communication.log.ForcedLogUtil;
import allbinary.input.event.VirtualKeyboardEvent;
import allbinary.input.event.VirtualKeyboardEventHandler;
import allbinary.input.event.VirtualKeyboardEventListenerInterface;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;
import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class VirtualKeyboardAPI3 extends VirtualKeyboard
implements VirtualKeyboardEventListenerInterface
{
    public static int MAX_API = Integer.MAX_VALUE;
    public static int MIN_API = 3;
    
    private Activity activity;
    
    public VirtualKeyboardAPI3(Activity activity)
    {
        super();
        
        this.activity = activity;

        VirtualKeyboardEventHandler.getInstance().removeAllListeners();
        VirtualKeyboardEventHandler.getInstance().addListener(this);
    }
    
    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }

    public void onVirtualKeyboardEvent(VirtualKeyboardEvent virtualKeyboardEvent)
    {
        InputMethodManager inputMethodManager = (InputMethodManager) 
        this.activity.getSystemService(Context.INPUT_METHOD_SERVICE); 
     
        Boolean isShow = (Boolean) virtualKeyboardEvent.getSource();
        
        if(isShow.booleanValue())
        {
            this.showVirtualKeyboard(inputMethodManager);
        }
        else
        {
            this.hideVirtualKeyboard(inputMethodManager);
        }    
    }

    public void forceHide()
    {
        InputMethodManager inputMethodManager = 
            (InputMethodManager) this.activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        this.hideVirtualKeyboard(inputMethodManager);
    }
    
    public void hide()
    {
        if (virtualKeyboard)
        {
            this.forceHide();
        }
    }

    private boolean virtualKeyboard;
    private void showVirtualKeyboard(
            InputMethodManager inputMethodManager)
    {
        //this.activity.getWindow().setSoftInputMode(
          //      WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        
        //inputMethodManager.showSoftInput(
          //      this.getView(), InputMethodManager.SHOW_FORCED); 

        //inputMethodManager.showSoftInputFromInputMethod (
          //      this.getView().getWindowToken(), 
            //    InputMethodManager.SHOW_FORCED);

        View view = ((ViewCompositeInterface) this.activity).getView();

        inputMethodManager.toggleSoftInputFromWindow(
                view.getWindowToken(), 
                InputMethodManager.SHOW_FORCED, 0);
        
        virtualKeyboard = true;
    }

    private void hideVirtualKeyboard(
            InputMethodManager inputMethodManager)
    {   
        IBinder token = 
            ((ViewCompositeInterface) this.activity).getView().getWindowToken();
        
        //this.activity.getWindow().setSoftInputMode(
          //      WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        //inputMethodManager.hideStatusIcon(token);
        
        //inputMethodManager.toggleSoftInput(
          //      0, InputMethodManager.HIDE_NOT_ALWAYS);
        
        //inputMethodManager.hideSoftInputFromInputMethod(
          //      token, InputMethodManager.HIDE_NOT_ALWAYS);
        
        //inputMethodManager.toggleSoftInputFromWindow(
          //      token, 
            //    0, InputMethodManager.HIDE_NOT_ALWAYS);

        //inputMethodManager.hideSoftInputFromWindow(
        //      token, 
          //    InputMethodManager.HIDE_NOT_ALWAYS);
        
        //InputMethodManager.HIDE_NOT_ALWAYS does not hide when SHOW_FORCED is used
        
        inputMethodManager.hideSoftInputFromWindow(token, 0);
        
        virtualKeyboard = false;
    }
    
}
