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
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.ColorCompositeInterface;

public class RectangleFilledAdjustedAnimation
extends Animation 
implements ColorCompositeInterface
{
   private int width;
   private int height;

   private int offsetX;
   private int offsetY;

   public RectangleFilledAdjustedAnimation(int width, int height, int offsetX, int offsetY, BasicColor basicColor) {
      this.width = width;
      this.height = height;

      this.offsetX = offsetX;
      this.offsetY = offsetY;

      this.setBasicColorP(basicColor);
   }

   @Override
   public void nextFrame() {
   }

   @Override
   public void paint(Graphics graphics, int x, int y) {
       this.basicSetColorUtil.setBasicColorP(
               graphics, this.getBasicColorP(), this.getColor());

      graphics.fillRect(x + this.offsetX, y + this.offsetY, width, height);
   }

    /**
     * @param width the width to set
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height)
    {
        this.height = height;
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
