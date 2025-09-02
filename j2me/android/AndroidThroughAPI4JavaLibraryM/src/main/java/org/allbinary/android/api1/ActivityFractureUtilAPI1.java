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
package org.allbinary.android.api1;

import org.allbinary.android.ActivityFractureUtil;

import android.app.Activity;
import android.view.WindowManager;

public class ActivityFractureUtilAPI1 extends ActivityFractureUtil
{
    private static final ActivityFractureUtilAPI1 instance = new ActivityFractureUtilAPI1();

    public static ActivityFractureUtilAPI1 getInstance()
    {
        return instance;
    }
    
    //private final int MAX_API = 4;

    @Override
    public void process(final Activity activity) throws Exception
    {
        //Only needed it dynamic
        //this.process(activity, false);
    }
    
    /*
    public void process(Activity activity, boolean shouldAllowVirtualKeyboardFracture) throws Exception
    {
        if(GameInputMotionInfoAPI1.getInstance().MAX_API != this.MAX_API)
        {
            throw new Exception("GameInputMotion is Factured");
        }
        
        int SDK_VERSION = AndroidInfoFactory.getInstance().getVersion();

        if(!shouldAllowVirtualKeyboardFracture && SDK_VERSION > VirtualKeyboardAPI1.MAX_API)
        {
            throw new Exception("VirtualKeyboard is probably Factured");
        }
    }
    */
    
    @Override
    public void setFullScreen(final Activity activity)
    {
        // FLAG_FORCE_NOT_FULLSCREEN
        // FLAG_FULLSCREEN
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    
}
