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
import android.content.res.Configuration;

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

        touchScreenFactory.setTouch(
                this.isTouch(activity));

        touchScreenFactory.setMultiTouch(
                this.isMultiTouch(activity));

        touchScreenFactory.setMultiTouchDistinct(
                this.isMultiTouchDistinct(activity));

        Configuration configuration = 
            activity.getResources().getConfiguration();

        int touchScreen = configuration.touchscreen;
        
        if(touchScreen == Configuration.TOUCHSCREEN_FINGER)
        {
            touchScreenFactory.setTouchScreenType(
                    TouchScreenTypeFactory.getInstance().FINGER);
            
            if(!touchScreenFactory.isTouch())
            {
                //LogUtil.put(LogFactory.getInstance("Not Really Exception: This indicates that a touch screen does exist but is not API8 so we will try it", this, CommonStrings.getInstance().PROCESS));
                PreLogUtil.put("Not Really Exception: This indicates that a touch screen does exist but is not API8 so we will try it", this, CommonStrings.getInstance().PROCESS);
                
                touchScreenFactory.setTouch(true);
            }
        }
        else
            if(touchScreen == Configuration.TOUCHSCREEN_STYLUS)
            {
                touchScreenFactory.setTouchScreenType(
                        TouchScreenTypeFactory.getInstance().STYLUS);
                
                if(!touchScreenFactory.isTouch())
                {
                    //LogUtil.put(LogFactory.getInstance("Not Really Exception: This indicates that a touch screen does exist but is not API8 so we will try it", this, CommonStrings.getInstance().PROCESS));
                    
                    PreLogUtil.put("Not Really Exception: This indicates that a touch screen does exist but is not API8 so we will try it", this, CommonStrings.getInstance().PROCESS);
                    
                    touchScreenFactory.setTouch(true);
                }
            }
        else
            if(touchScreen == Configuration.TOUCHSCREEN_NOTOUCH)
            {
                touchScreenFactory.setTouchScreenType(
                        TouchScreenTypeFactory.getInstance().NOTOUCH);
            }
                else
                    if(touchScreen == Configuration.TOUCHSCREEN_UNDEFINED)
                    {
                        touchScreenFactory.setTouchScreenType(
                                TouchScreenTypeFactory.getInstance().UNDEFINED);
                    }
        
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
