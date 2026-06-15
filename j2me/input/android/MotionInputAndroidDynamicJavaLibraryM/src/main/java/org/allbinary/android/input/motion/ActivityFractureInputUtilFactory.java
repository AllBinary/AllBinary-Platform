/*
* AllBinary Open License Version 1
* Copyright (c) 2009 AllBinary
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
package org.allbinary.android.input.motion;

import org.allbinary.android.ActivityFractureUtil;
import org.allbinary.android.AndroidInfoFactory;
import org.allbinary.android.api1.ActivityFractureUtilAPI1;
import org.allbinary.android.api16.ActivityFractureUtilAPI16;
import org.allbinary.android.api19.ActivityFractureUtilAPI19;
import org.allbinary.android.api5.ActivityFractureUtilAPI5;

public class ActivityFractureInputUtilFactory
{
    public static final ActivityFractureUtil getInstance()
    {
        final int SDK_VERSION = AndroidInfoFactory.getInstance().getVersion();
        
        //VERSION_CODES.DONUT
        //1-4
        if(SDK_VERSION <= 4)
        {
            return ActivityFractureUtilAPI1.getInstance();
        }
        //5-15
        else if(SDK_VERSION <= 15)
        {
            return ActivityFractureUtilAPI5.getInstance();
        }
        //16-18
        else if(SDK_VERSION <= 16)
        {
            return ActivityFractureUtilAPI16.getInstance();
        }
        //19-29
        else //if(SDK_VERSION <= 19)
        {
            return ActivityFractureUtilAPI19.getInstance();
        //30-up Androidx
//        } else 
//        {
//            return ActivityFractureUtilAPI30.getInstance();
        }
    }
    
}
