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
package org.allbinary.animation.image.sprite;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import org.allbinary.animation.AnimationBehavior;

public class AllBinaryAdjustedSpriteRotationAnimation
extends AllBinarySpriteRotationAnimation
{
    private int dx;
    private int dy;

    public AllBinaryAdjustedSpriteRotationAnimation(
            final Sprite sprite, final Image image, final int dx, final int dy, final AnimationBehavior animationBehavior)
    {
        super(sprite, image, animationBehavior);
        
        this.dx = dx;
        this.dy = dy;
    }    

    @Override
    public void paint(final Graphics g, final int x, final int y)
    {
        this.sprite.setPosition(x + this.dx, y + this.dy);
        this.paint(g);
    }
    
}
