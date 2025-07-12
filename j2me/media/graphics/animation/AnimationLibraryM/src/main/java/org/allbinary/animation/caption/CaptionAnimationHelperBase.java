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

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.graphics.color.BasicColor;

/**
 *
 * @author User
 */
public class CaptionAnimationHelperBase extends Animation {
    
    public static final CaptionAnimationHelperBase INSTANCE = new CaptionAnimationHelperBase();
    
    public boolean isShowing()
    {
        return false;
    }
    
    public void tick()
    {

    }
    
    public void update(String message, BasicColor basicColor)
    {

    }
    
    public void paint(Graphics graphics, int x, int y)
    {
        
    }
    
}
