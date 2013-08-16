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
package org.allbinary.android.api5;

import org.allbinary.android.ActivityFractureUtil;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.input.motion.button.TouchScreenFactory;
import allbinary.input.motion.button.TouchScreenTypeFactory;
import android.app.Activity;

public class ActivityFractureUtilAPI5 extends ActivityFractureUtil
{
    private static final ActivityFractureUtilAPI5 instance = new ActivityFractureUtilAPI5();

    public static ActivityFractureUtilAPI5 getInstance()
    {
        return instance;
    }

    //PackageManager
    
    //Since: API Level 7
    private final String FEATURE_TOUCHSCREEN_MULTITOUCH = 
        "android.hardware.touchscreen.multitouch";

    //Since: API Level 8
    private final String FEATURE_TOUCHSCREEN = "android.hardware.touchscreen";
        
    //Since: API Level 8
    private final String FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT = 
        "android.hardware.touchscreen.multitouch.distinct"; 
    
    public void process(Activity activity)
    {
        final TouchScreenFactory touchScreenFactory = 
            TouchScreenFactory.getInstance();

        final TouchScreenTypeFactory touchScreenTypeFactory = 
                TouchScreenTypeFactory.getInstance();
        
        touchScreenFactory.setTouch(this.isTouch(activity));

        touchScreenFactory.setMultiTouch(this.isMultiTouch(activity));

        touchScreenFactory.setMultiTouchDistinct(this.isMultiTouchDistinct(activity));

        touchScreenTypeFactory.update(activity);
        
        //LogUtil.put(LogFactory.getInstance(touchScreenFactory.toString(), this, CommonStrings.getInstance().PROCESS));
        PreLogUtil.put(touchScreenFactory.toString(), this, CommonStrings.getInstance().PROCESS);
    }

    private boolean isTouch(Activity activity)
    {
        return this.isFeature(activity, this.FEATURE_TOUCHSCREEN);   
    }
    
    private boolean isMultiTouch(Activity activity)
    {
        return this.isFeature(activity, this.FEATURE_TOUCHSCREEN_MULTITOUCH);   
    }

    private boolean isMultiTouchDistinct(Activity activity)
    {
        return this.isFeature(activity, this.FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT);   
    }
    
    private boolean isFeature(Activity activity, String property)
    {
        return activity.getPackageManager().hasSystemFeature(property);
    }
}
