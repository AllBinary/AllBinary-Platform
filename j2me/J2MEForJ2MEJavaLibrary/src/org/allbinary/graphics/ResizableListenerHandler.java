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

public class ResizableListenerHandler {

    private static final ResizableListenerHandler instance =
        new ResizableListenerHandler();

    /**
     * @return the instance
     */
    public static ResizableListenerHandler getInstance()
    {
        return instance;
    }

    //private ResizableListenerInterface resizableListenerInterface =
      //  new ResizableEmptyListener();

    public void setListener(
        ResizableListenerInterface resizableListenerInterface)
    {
        //this.resizableListenerInterface = resizableListenerInterface;
    }

    public void fire(boolean isFullScreen)
    {
        //this.resizableListenerInterface.onResizable(isFullScreen);
    }

    public synchronized void fireEvent(boolean value)
            throws Exception
    {
    }
}
