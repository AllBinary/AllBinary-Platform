/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.animation.caption;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.graphics.color.BasicColor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class CaptionAnimationHelperBase extends Animation {
    
    @JsProperty
    public static final CaptionAnimationHelperBase INSTANCE = new CaptionAnimationHelperBase();
    
    @JsMethod
    public boolean isShowing()
    {
        return false;
    }
    
    @JsMethod
    public void tick()
    {

    }
    
    @JsMethod
    public void update(String message, BasicColor basicColor)
    {

    }
    
    @Override
    @JsMethod
    public void paintXY(Graphics graphics, int x, int y)
    {
        
    }
    
}
