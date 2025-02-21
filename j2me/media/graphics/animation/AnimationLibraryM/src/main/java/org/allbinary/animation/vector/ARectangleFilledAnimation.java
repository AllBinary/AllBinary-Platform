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
package org.allbinary.animation.vector;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.ColorCompositeInterface;

public class ARectangleFilledAnimation
    extends Animation
    implements ColorCompositeInterface {
    
    public int x;
    public int y;

    public int width;
    public int height;

    public ARectangleFilledAnimation() {

        //this.width = width;
        //this.height = height;
        
        this.setBasicColor(BasicColorFactory.getInstance().BLACK);

    }

    public void nextFrame() {
    }

    //@Override
    public void paint(final Graphics graphics, final int unusedX, final int unusedY) {

        graphics.fillRect(x, y, width, height);

    }

}
