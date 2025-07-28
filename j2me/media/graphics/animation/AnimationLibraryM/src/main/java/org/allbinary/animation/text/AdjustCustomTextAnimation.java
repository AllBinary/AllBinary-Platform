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
package org.allbinary.animation.text;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.AnimationBehavior;

/**
 *
 * @author User
 */
public class AdjustCustomTextAnimation extends CustomTextAnimation {
    
    private int dx;
    private int dy;
    
    public AdjustCustomTextAnimation(final String text, final int fontSize, final int dx, final int dy, final AnimationBehavior animationBehavior) {
        super(text, fontSize, animationBehavior);
        
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public int getDx() {
        return dx;
    }

    @Override
    public void setDx(int dx) {
        this.dx = dx;
    }

    @Override
    public int getDy() {
        return dy;
    }

    @Override
    public void setDy(int dy) {
        this.dy = dy;
    }
    
    @Override
    public void paint(final Graphics graphics, final int x, final int y)
    {        
        super.paint(graphics, x + this.dx, y + this.dy);
    }
    
}
