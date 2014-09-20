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
import javax.microedition.lcdui.game.Sprite;

public class AllBinaryAdjustedSpriteRotationAnimation
extends AllBinarySpriteRotationAnimation
{
    private int dx;
    private int dy;
    
    public AllBinaryAdjustedSpriteRotationAnimation(
            Sprite sprite, int dx, int dy)
    {
        super(sprite);
        this.dx = dx;
        this.dy = dy;
    }    

    public void paint(Graphics g, int x, int y)
    {
        this.sprite.setPosition(x + this.dx, y + this.dy);
        this.paint(g);
    }
    
}
