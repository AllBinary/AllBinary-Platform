package org.allbinary.game.input;

import org.allbinary.android.AndroidInfoFactory;

public class ActivityFractureControllerUtilFactory
{
    public static final AndroidKeyFactory getInstance()
    {
        int SDK_VERSION = AndroidInfoFactory.getInstance().getVersion();
        
        //VERSION_CODES.DONUT
        if(SDK_VERSION <= 8)
        {
            return AndroidAPI1KeyFactory.getInstance();
        }
        else
            if(SDK_VERSION <= 11)
            {
                return AndroidAPI9KeyFactory.getInstance();
            }
            else
            {
            	return AndroidAPI12KeyFactory.getAPI12Instance();
            }
    }
}
