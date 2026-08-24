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
package org.allbinary.graphics.color;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class BasicColorSetUtil
{
    private static final BasicColorSetUtil instance = new BasicColorSetUtil();
    
    @JsMethod
    public static BasicColorSetUtil getInstance()
    {
        return BasicColorSetUtil.instance;
    }
    
    @JsConstructor
    protected BasicColorSetUtil()
    {
        
    }
    
    @JsMethod
    public void setBasicColorP(Graphics graphics, BasicColor basicColor)
    {
        graphics.setColor(basicColor.intValue());
    }

    @JsMethod
    public void setBasicColorP3(Graphics graphics, BasicColor basicColor, int value)
    {
        graphics.setColor(value);
    }
}
