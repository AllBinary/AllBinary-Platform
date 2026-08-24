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
package org.allbinary.game.displayable.canvas;

import jsinterop.annotations.JsType;

import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class GameRunnable implements Runnable
{

    @JsProperty
    public final int WAIT = 240;
    @JsProperty
    public final int FAST = 60;
    @JsProperty
    public long waitInMillis = (long) this.WAIT;
    
    @JsConstructor
    public GameRunnable() {
        
    }
    
    @Override
    @JsMethod
    public void run()
    {
        DisplayInfoSingleton.getInstance().process();
    }
    
    @JsMethod
    public void processLoopSleep()
    throws Exception
    {
        Thread.sleep(this.waitInMillis);
    }
}
