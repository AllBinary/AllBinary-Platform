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
package org.allbinary.graphics.paint;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author user
 */

@JsType
public class Paintable 
implements PaintableInterface
{

    @JsMethod
    public void setBasicColorP(final BasicColor basicColor)
    {
    }
    
    @Override
    @JsMethod
    public void paint(final Graphics graphics)
    {
    }

    @Override
    @JsMethod
    public void paintThreed(final Graphics graphics)
    {
    }
}
