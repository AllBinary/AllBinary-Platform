package org.allbinary.animation.vector;
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


import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColorFactory;

public class ARectangleFilledAnimation
    extends RectangleFilledAnimation {
    
    public int x;
    public int y;

    public ARectangleFilledAnimation() {
        super(0,0, BasicColorFactory.getInstance().BLACK);

    }

    @Override
    public void nextFrame() {
    }

    @Override
    public void paintXY(final Graphics graphics, final int unusedX, final int unusedY) {

        super.paintXY(graphics, this.x, this.y);

    }

}
