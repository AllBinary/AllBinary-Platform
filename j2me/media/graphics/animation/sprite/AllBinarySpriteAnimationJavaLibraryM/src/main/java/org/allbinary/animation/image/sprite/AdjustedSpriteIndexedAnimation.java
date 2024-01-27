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
import org.allbinary.animation.AnimationBehavior;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorUtil;

public class AdjustedSpriteIndexedAnimation
extends SpriteIndexedAnimation
{
    private int dx;
    private int dy;

    public AdjustedSpriteIndexedAnimation(final Sprite sprite, final int dx, final int dy, final AnimationBehavior animationBehavior)
        throws Exception
    {
        this(sprite, BasicColorUtil.getInstance().ZERO_ARRAY, dx, dy, animationBehavior);
    }    
        
    public AdjustedSpriteIndexedAnimation(final Sprite sprite, final BasicColor[] basicColorArray, final int dx, final int dy, final AnimationBehavior animationBehavior)
        throws Exception
    {
        super(sprite, basicColorArray, animationBehavior);
        this.dx = dx;
        this.dy = dy;
        //this.getSprite().setPosition(this.dx, this.dy);
    }

    public void paint(final Graphics g, final int x, final int y)
    {
        this.getSprite().setPosition(x + this.dx, y + this.dy);
        super.paint(g);
    }

    public void paint(final Graphics g)
    {
        this.getSprite().setPosition(this.dx, this.dy);
        super.paint(g);
    }
}
