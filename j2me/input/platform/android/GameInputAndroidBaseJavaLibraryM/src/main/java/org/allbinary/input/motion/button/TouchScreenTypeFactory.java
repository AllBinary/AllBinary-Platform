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
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final TouchScreenTypeFactory instance = new TouchScreenTypeFactory();

    public static TouchScreenTypeFactory getInstance()
    {
        return instance;
    }
        
    public void update(Activity activity)
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        
        final TouchScreenFactory touchScreenFactory = 
                TouchScreenFactory.getInstance();

        final TouchScreenTypesFactory touchScreenTypesFactory = 
                TouchScreenTypesFactory.getInstance();
        
        Configuration configuration = activity.getResources().getConfiguration();

        int touchScreen = configuration.touchscreen;
        
        if(touchScreen == Configuration.TOUCHSCREEN_FINGER)
        {
            touchScreenFactory.setTouchScreenType(touchScreenTypesFactory.FINGER);
            
            if(!touchScreenFactory.isTouch())
            {
                //logUtil.put("Not Really Exception: This indicates that a touch screen does exist but is not API8 so we will try it", this, commonStrings.PROCESS);
                PreLogUtil.put("Not Really Exception: This indicates that a touch screen does exist but is not API8 so we will try it", this, commonStrings.PROCESS);
                
                touchScreenFactory.setTouch(true);
            }
        }
        else
            if(touchScreen == Configuration.TOUCHSCREEN_STYLUS)
            {
                touchScreenFactory.setTouchScreenType(touchScreenTypesFactory.STYLUS);
                
                if(!touchScreenFactory.isTouch())
                {
                    //logUtil.put("Not Really Exception: This indicates that a touch screen does exist but is not API8 so we will try it", this, commonStrings.PROCESS);
                    
                    PreLogUtil.put("Not Really Exception: This indicates that a touch screen does exist but is not API8 so we will try it", this, commonStrings.PROCESS);
                    
                    touchScreenFactory.setTouch(true);
                }
            }
        else
            if(touchScreen == Configuration.TOUCHSCREEN_NOTOUCH)
            {
                touchScreenFactory.setTouchScreenType(touchScreenTypesFactory.NOTOUCH);
            }
                else
                    if(touchScreen == Configuration.TOUCHSCREEN_UNDEFINED)
                    {
                        touchScreenFactory.setTouchScreenType(touchScreenTypesFactory.UNDEFINED);
                    }
    }
}
