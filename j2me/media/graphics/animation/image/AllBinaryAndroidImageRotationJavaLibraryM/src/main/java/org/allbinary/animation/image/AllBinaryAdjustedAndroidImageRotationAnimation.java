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
package org.allbinary.animation.image;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.math.AngleInfo;

public class AllBinaryAdjustedAndroidImageRotationAnimation
extends AllBinaryAndroidImageRotationAnimation
{
    private final int dx;
    private final int dy;

    protected AllBinaryAdjustedAndroidImageRotationAnimation(
            Image originalImage, Image image, AngleInfo angleInfo, short totalAngle, 
            int dx, int dy) 
    throws Exception
    {
        super(originalImage, image, angleInfo, totalAngle);
        
        this.dx = dx;
        this.dy = dy;
    }
    
    public void paint(Graphics graphics, int x, int y)
    {
        super.paint(graphics, x + this.dx, y + this.dy);
    }    
}
