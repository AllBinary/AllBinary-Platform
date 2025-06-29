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
package org.allbinary.input.motion.button;

import android.app.Activity;
import android.content.res.Configuration;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;

public class TouchScreenTypeFactory
{
    private static final TouchScreenTypeFactory instance = new TouchScreenTypeFactory();

    public static TouchScreenTypeFactory getInstance()
    {
        return instance;
    }
    
    public final TouchScreenType FINGER = new TouchScreenType("Finger");
    
    public final TouchScreenType NOTOUCH = new TouchScreenType("No Touch");
    
    public final TouchScreenType STYLUS = new TouchScreenType("Stylus");

    public final TouchScreenType UNDEFINED = new TouchScreenType("Undefined");
    
    public void update(Activity activity)
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        
        final TouchScreenFactory touchScreenFactory = 
                TouchScreenFactory.getInstance();

        final TouchScreenTypeFactory touchScreenTypeFactory = 
                TouchScreenTypeFactory.getInstance();
        
        Configuration configuration = activity.getResources().getConfiguration();

        int touchScreen = configuration.touchscreen;
        
        if(touchScreen == Configuration.TOUCHSCREEN_FINGER)
        {
            touchScreenFactory.setTouchScreenType(touchScreenTypeFactory.FINGER);
            
            if(!touchScreenFactory.isTouch())
            {
                //LogUtil.put(LogFactory.getInstance("Not Really Exception: This indicates that a touch screen does exist but is not API8 so we will try it", this, commonStrings.PROCESS));
                PreLogUtil.put("Not Really Exception: This indicates that a touch screen does exist but is not API8 so we will try it", this, commonStrings.PROCESS);
                
                touchScreenFactory.setTouch(true);
            }
        }
        else
            if(touchScreen == Configuration.TOUCHSCREEN_STYLUS)
            {
                touchScreenFactory.setTouchScreenType(touchScreenTypeFactory.STYLUS);
                
                if(!touchScreenFactory.isTouch())
                {
                    //LogUtil.put(LogFactory.getInstance("Not Really Exception: This indicates that a touch screen does exist but is not API8 so we will try it", this, commonStrings.PROCESS));
                    
                    PreLogUtil.put("Not Really Exception: This indicates that a touch screen does exist but is not API8 so we will try it", this, commonStrings.PROCESS);
                    
                    touchScreenFactory.setTouch(true);
                }
            }
        else
            if(touchScreen == Configuration.TOUCHSCREEN_NOTOUCH)
            {
                touchScreenFactory.setTouchScreenType(touchScreenTypeFactory.NOTOUCH);
            }
                else
                    if(touchScreen == Configuration.TOUCHSCREEN_UNDEFINED)
                    {
                        touchScreenFactory.setTouchScreenType(touchScreenTypeFactory.UNDEFINED);
                    }
    }
}
