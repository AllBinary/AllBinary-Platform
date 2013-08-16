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
package allbinary.canvas;

import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.MainFeatureFactory;

import javax.microedition.lcdui.CommandListener;

/**
 *
 * @author user
 */
public class FullScreenUtil
{

    public static final void init(
        FullScreenInterface fullScreenInterface,
        CommandListener commandListener)
        throws Exception
    {
        if (commandListener != null)
        {
            FullScreenUtil.init(fullScreenInterface);
        }
    }

    public static final void init(FullScreenInterface fullScreenInterface)
    {
    }

    public static final boolean isScreenChange(
        boolean isFullScreen)
    {
        MainFeatureFactory mainFeatureFactory =
            MainFeatureFactory.getInstance();

        Features features = Features.getInstance();

        if (features.isFeature(mainFeatureFactory.FULL_SCREEN) != isFullScreen)
        {
            return true;
        }

        return false;
    }

    public static final boolean isScreenChange(
        FullScreenInterface fullScreenInterface)
    {
        return isScreenChange(false);
    }
}
