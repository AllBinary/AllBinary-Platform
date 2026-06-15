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
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import org.allbinary.android.activity.ViewCompositeInterface;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class ShowVirtualKeyboardRunnable implements Runnable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final Activity activity;
    
    //private int index = 3;
    
    public ShowVirtualKeyboardRunnable(final Activity activity)
    {
        this.activity = activity;
    }
    
    @Override
    public void run()
    {
        try
        {
            final InputMethodManager inputMethodManager = (InputMethodManager) /*TS as unknown*/ this.activity.getSystemService(Context.INPUT_METHOD_SERVICE);

            final ViewCompositeInterface viewCompositeInterface = (ViewCompositeInterface) /*TS as unknown*/ this.activity;

            if (viewCompositeInterface == null) {
                ForcedLogUtil.log("Activity Null", this);
            }

            final View view = viewCompositeInterface.getView();
        
            /*
            if (index == 0) {
                this.activity.getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                index++;
            } else if (index == 1) {
                inputMethodManager.showSoftInput(
                        view, InputMethodManager.SHOW_FORCED);
                index++;
            } else if (index == 2) {

                inputMethodManager.showSoftInputFromInputMethod(
                        view.getWindowToken(),
                        InputMethodManager.SHOW_FORCED);
                index++;
            } else */
/*
            if (index == 3) {

                inputMethodManager.showSoftInput(
                        view, InputMethodManager.SHOW_IMPLICIT);
                index++;
            } else if (index == 4) {

                inputMethodManager.showSoftInput(
                        this.activity.getWindow().getDecorView(), InputMethodManager.SHOW_IMPLICIT);
                index++;
            } else if (index == 5) {
                index = 0;
            }          
*/
                inputMethodManager.toggleSoftInputFromWindow(
                        view.getWindowToken(),
                        InputMethodManager.SHOW_FORCED, 0);
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this,commonStrings.RUN, e);
        }
    }
}
