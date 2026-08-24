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
package org.allbinary.game.input;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Canvas;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class InputProcessor
{
    @JsProperty
    protected final GameInputStrings gameInputStrings = GameInputStrings.getInstance();
    
    @JsMethod
    public void keyPressedByDevice(final int keyCode, final int deviceId)
    {
        
    }

    @JsMethod
    public void keyReleasedByDevice(final Canvas canvas, final int keyCode, final int deviceId)
    {
        
    }

}
