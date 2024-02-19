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
import org.allbinary.math.AngleFactory;

public class CircleFilledAnimation 
extends Animation 
implements ColorCompositeInterface 
{
   private int width;
   private int height;
   
   public CircleFilledAnimation(final int width, final int height, final BasicColor basicColor) {
       super();

      this.width = width;
      this.height = height;
      this.setBasicColor(basicColor);
   }

   public void nextFrame() {
   }
   
   private final int TOTAL_ANGLE = AngleFactory.getInstance().TOTAL_ANGLE;

   public void paint(Graphics graphics, int x, int y) {
       this.basicSetColorUtil.setBasicColor(
               graphics, this.getBasicColor(), this.getColor());
      //graphics.fillArc(x, y, width, height, 0, TOTAL_ANGLE);
      graphics.fillRect(x, y, width, height);
   }
}
