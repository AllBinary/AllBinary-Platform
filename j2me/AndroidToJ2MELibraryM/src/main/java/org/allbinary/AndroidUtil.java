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
package org.allbinary;

import android.app.Activity;
import android.os.Build;

public class AndroidUtil
{
    public static final Activity NULL_ACTIVITY = new Activity();
    
    private static final int version = Integer.parseInt(Build.VERSION.SDK);
    public static boolean isMemoryRestrictive() {
        if(AndroidUtil.version < 30) {
            return true;
        }
        return false;
    }
    
    public static boolean isAndroid()
    {
        return true;
    }
}
