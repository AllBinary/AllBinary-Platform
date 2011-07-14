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
package allbinary.animation.image;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import allbinary.graphics.Anchor;

/**
 *
 * @author user
 */
public class TopToBottomImageAnimation
    extends AllBinaryImageSegmentAnimation
{
    private int currentHeight;

    public TopToBottomImageAnimation(Image image, int[] sequenceArray)
        throws Exception
    {
        super(image, sequenceArray);

        this.currentHeight = 0;

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
        this.currentHeight = this.getDrawHeight() * this.getFrame() / this.getSize();
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics, int x, int y)
    {
        //Image src, int x_src, int y_src, int width, int height, int transform,
          //  int x_dst, int y_dst, int anchor
        graphics.drawRegion(this.getImage(), this.getStartX(), this.getStartY(),
            this.getDrawWidth(), this.currentHeight,
            Sprite.TRANS_NONE, x, y, anchor);
    }
}
