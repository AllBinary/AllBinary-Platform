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

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.ColorCompositeInterface;

public class RectangleFilledAdjustedAnimation
extends RectangleFilledAnimation 
implements ColorCompositeInterface
{

   private int offsetX;
   private int offsetY;

   public RectangleFilledAdjustedAnimation(final int width, final int height, final int offsetX, final int offsetY, final BasicColor basicColor) {
       super(width, height, basicColor);

      this.offsetX = offsetX;
      this.offsetY = offsetY;

      this.setBasicColorP(basicColor);
   }

   @Override
   public void nextFrame() {
   }

   @Override
   public void paintXY(final Graphics graphics, final int x, final int y) {
       this.basicSetColorUtil.setBasicColorP3(
               graphics, this.getBasicColorP(), this.getColor());

       super.paintXY(graphics, x + this.offsetX, y + this.offsetY);
   }

    /**
     * @param offsetX the offsetX to set
     */
    public void setOffsetX(int offsetX)
    {
        this.offsetX = offsetX;
    }

    /**
     * @param offsetY the offsetY to set
     */
    public void setOffsetY(int offsetY)
    {
        this.offsetY = offsetY;
    }
}
