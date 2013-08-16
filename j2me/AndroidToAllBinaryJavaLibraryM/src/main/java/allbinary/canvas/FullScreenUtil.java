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

import javax.microedition.lcdui.CommandListener;

/**
 *
 * @author user
 */
public class FullScreenUtil {

    public static final void init(
        FullScreenInterface fullScreenInterface, CommandListener commandListener)
        throws Exception
    {
    }

    public static final void init(FullScreenInterface fullScreenInterface)
    {
    }

    public static final boolean isScreenChange(
        boolean isFullScreen)
    {
            return false;
    }

    public static final boolean isScreenChange(
        FullScreenInterface fullScreenInterface)
    {
        return isScreenChange(fullScreenInterface.isFullScreenMode());
    }
}
