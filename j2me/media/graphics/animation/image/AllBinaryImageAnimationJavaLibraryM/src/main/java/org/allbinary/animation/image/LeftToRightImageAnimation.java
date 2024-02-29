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
public class LeftToRightImageAnimation
    extends ImageSegmentAnimation
{
    private int currentWidth;
    
    public LeftToRightImageAnimation(final Image image, final int[] sequenceArray, final AnimationBehavior animationBehavior)
        throws Exception
    {
        super(image, sequenceArray, animationBehavior);

        this.currentWidth = 0;

        if(this.getSequence()[0] == -1)
        {
            this.setFrame(this.getSize() - 1);
        }
    }

    public void nextFrame()
    {
        if(this.getSequence()[0] == -1)
        {
            super.previousFrame();
        }
        else
        {
            super.nextFrame();
        }

        this.update();
    }

    public void previousFrame()
    {
        if(this.getSequence()[0] == -1)
        {
            super.nextFrame();
        }
        else
        {
            super.previousFrame();
        }

        this.update();
    }

    public void setFrame(int index)
    {
        super.setFrame(index);

        this.update();
    }

    private void update()
    {
        this.currentWidth = this.getDrawWidth() * this.getFrame() / this.getSize();
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(final Graphics graphics, final int x, final int y)
    {
        //Image src, int x_src, int y_src, int width, int height, int transform,
        //  int x_dst, int y_dst, int anchor
      graphics.drawRegion(this.getImage(), this.getStartX(), this.getStartY(),
          this.currentWidth, this.getDrawHeight(),
          Sprite.TRANS_NONE, x, y, anchor);
    }
}
