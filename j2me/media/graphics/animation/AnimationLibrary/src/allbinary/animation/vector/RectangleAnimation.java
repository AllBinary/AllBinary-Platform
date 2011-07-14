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
package allbinary.animation.vector;

import javax.microedition.lcdui.Graphics;

import allbinary.animation.Animation;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.ColorCompositeInterface;

public class RectangleAnimation
extends Animation 
implements ColorCompositeInterface
{
   private int width;
   private int height;
   
   public RectangleAnimation(int width, int height, BasicColor basicColor) {
      this.width = width;
      this.height = height;
      this.setBasicColor(basicColor);
   }

   public void nextFrame() {
   }

   public void paint(Graphics graphics, int x, int y) {
       this.basicColorUtil.setBasicColor(
               graphics, this.getBasicColor(), this.getColor());

      graphics.drawRect(x, y, width, height);
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
}
