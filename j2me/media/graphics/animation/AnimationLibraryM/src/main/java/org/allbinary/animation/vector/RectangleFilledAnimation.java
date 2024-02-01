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

public class RectangleFilledAnimation 
extends Animation 
implements ColorCompositeInterface
{
   private int width;
   private int height;
   
   public RectangleFilledAnimation(final int width, final int height, final BasicColor basicColor) {
      this.width = width;
      this.height = height;
      this.setBasicColor(basicColor);
   }

   public void nextFrame() {
   }

   public void paint(final Graphics graphics, final int x, final int y) {
       this.basicColorUtil.setBasicColor(
               graphics, this.getBasicColor(), this.getColor());

      graphics.fillRect(x, y, width, height);
   }

    /**
     * @param width the width to set
     */
    public void setWidth(final int width)
    {
        this.width = width;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(final int height)
    {
        this.height = height;
    }
}
