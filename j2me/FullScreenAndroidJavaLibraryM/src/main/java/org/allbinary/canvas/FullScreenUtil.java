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
package org.allbinary.canvas;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.CommandListener;

/**
 *
 * @author user
 */
public class FullScreenUtil {

    private static final FullScreenUtil instance = new FullScreenUtil();

    /**
     * @return the instance
     */
    public static FullScreenUtil getInstance() {
        return FullScreenUtil.instance;
    }
    
    public final void initOnRun(final Canvas fullScreenInterface, final CommandListener commandListener)
        throws Exception
    {
    }

    public final void init(final Canvas fullScreenInterface)
    {
    }

    public final boolean isScreenChange(final boolean isFullScreen)
    {
            return false;
    }

    public final boolean isScreenChangeCanvas(final Canvas fullScreenInterface)
    {
        return this.isScreenChange(fullScreenInterface.isFullScreenMode());
    }
}
