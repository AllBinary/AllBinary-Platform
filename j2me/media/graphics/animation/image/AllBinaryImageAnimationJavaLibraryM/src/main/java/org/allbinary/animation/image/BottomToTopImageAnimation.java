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
import javax.microedition.lcdui.game.Sprite;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.graphics.Anchor;

/**
 *
 * @author user
 */
public class BottomToTopImageAnimation
    extends ImageSegmentAnimation
{
    private int startHeight;
    private int currentHeight;
    
    public BottomToTopImageAnimation(final Image image, final AnimationBehavior animationBehavior)
        throws Exception
    {
        super(image, animationBehavior);

        this.setFrame(this.getSize() - 1);
    }

    public void nextFrame()
    {
        super.previousFrame();

        this.update();
    }

    public void previousFrame()
    {
        super.nextFrame();

        this.update();
    }

    public void setFrame(int index)
    {
        super.setFrame(index);

        this.update();
    }

    private void update()
    {
        this.startHeight = this.getDrawHeight() * this.getFrame() / this.getSize();
        this.currentHeight = this.getDrawHeight() - this.startHeight;
    }

    private int anchor = Anchor.TOP_LEFT;    
    
    public void paint(Graphics graphics, int x, int y)
    {
        //Image src, int x_src, int y_src, int width, int height, int transform,
        //  int x_dst, int y_dst, int anchor
        graphics.drawRegion(this.getImage(), this.getStartX(), this.startHeight,
          this.getDrawWidth(),  this.currentHeight,
          Sprite.TRANS_NONE, x, y + this.startHeight, anchor);
    }
}
