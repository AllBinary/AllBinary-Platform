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
package org.allbinary.graphics;

public class ScreenListenerHandler {

    private static final ScreenListenerHandler instance =
        new ScreenListenerHandler();

    /**
     * @return the instance
     */
    public static ScreenListenerHandler getInstance()
    {
        return instance;
    }

    private ScreenListenerInterface screenListenerInterface =
        new ScreenEmptyListener();

    public void setListener(
        ScreenListenerInterface screenListenerInterface)
    {
        this.screenListenerInterface = screenListenerInterface;
    }

    public void fire(boolean isFullScreen)
    {
        this.screenListenerInterface.onFullScreen(isFullScreen);
    }

    public void fire()
    {
        this.screenListenerInterface.onFullScreenDisplay();
    }
}
