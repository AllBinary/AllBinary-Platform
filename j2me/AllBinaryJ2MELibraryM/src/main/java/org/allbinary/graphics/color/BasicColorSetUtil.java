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

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;

public class BasicColorSetUtil
{
    private static final BasicColorSetUtil instance = new BasicColorSetUtil();
    
    public static BasicColorSetUtil getInstance()
    {
        return instance;
    }
    
    protected BasicColorSetUtil()
    {
        
    }
    
    public void setBasicColor(Graphics graphics, BasicColor basicColor)
    {
        graphics.setColor(basicColor.intValue());
    }

    public void setBasicColor(Graphics graphics, BasicColor basicColor, int value)
    {
        graphics.setColor(value);
    }
}
