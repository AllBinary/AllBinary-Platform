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

import org.allbinary.AndroidUtil;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.math.AngleFactory;

/**
 *
 * @author user
 */
public class AdjustedCircleAnimation 
    extends CircleAnimation
{
    private int xAdjustment;
    private int yAdjustment;

    public AdjustedCircleAnimation(
            int width, int height,
            int xAdjustment, int yAdjustment,
            BasicColor basicColor)
    {
        super(width, height, basicColor);

        this.xAdjustment = xAdjustment;
        this.yAdjustment = yAdjustment;
    }

    public AdjustedCircleAnimation(
            int width, int height, int innerWidth,
            BasicColor basicColor)
    {
        super(width, height, basicColor);

        //this.xAdjustment = xAdjustment;
        //this.yAdjustment = yAdjustment;

        if (AndroidUtil.isAndroid())
        {
            int widthPortion = (((innerWidth / 3) * 10) / 7);
            
            this.xAdjustment = -((width >> 1) - widthPortion);
            this.yAdjustment = this.xAdjustment;
        }
        else
        {
            this.xAdjustment = -((width >> 1) - innerWidth);
            this.yAdjustment = this.xAdjustment;
        }
    }
    
    @Override
   public void paint(Graphics graphics, int x, int y) {
      
       this.basicSetColorUtil.setBasicColorP(
               graphics, this.getBasicColorP(), this.getColor());
      
      graphics.drawArc(x + this.xAdjustment, y + this.yAdjustment,
          width, height, 0, TOTAL_ANGLE);
   }
}
