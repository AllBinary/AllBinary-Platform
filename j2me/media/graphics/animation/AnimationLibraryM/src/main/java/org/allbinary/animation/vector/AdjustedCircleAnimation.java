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

/**
 *
 * @author user
 */
public class AdjustedCircleAnimation 
    extends CircleAnimation
{
    public static AdjustedCircleAnimation createAnimation(
            final int width, final int height,
            final int xAdjustment, final int yAdjustment,
            final BasicColor basicColor)
    {
        final AdjustedCircleAnimation adjustedCircleAnimation = new AdjustedCircleAnimation(width, height, basicColor);

        adjustedCircleAnimation.initXY(xAdjustment, yAdjustment);

        return adjustedCircleAnimation;
    }

    public static AdjustedCircleAnimation createW(
            final int width, final int height, final int innerWidth, final BasicColor basicColor)
    {
        final AdjustedCircleAnimation adjustedCircleAnimation = new AdjustedCircleAnimation(width, height, basicColor);

        adjustedCircleAnimation.init(innerWidth);

        return adjustedCircleAnimation;
    }

    private int xAdjustment;
    private int yAdjustment;

    public AdjustedCircleAnimation(
            final int width, final int height, final BasicColor basicColor)
    {
        super(width, height, basicColor);
    }

    private void initXY(final int xAdjustment, final int yAdjustment) {
        this.xAdjustment = xAdjustment;
        this.yAdjustment = yAdjustment;
    }

    private void init(final int innerWidth) {
        if (AndroidUtil.isAndroid()) {
            int widthPortion = (((innerWidth / 3) * 10) / 7);

            this.xAdjustment = -((this.width >> 1) - widthPortion);
            this.yAdjustment = this.xAdjustment;
        } else {
            this.xAdjustment = -((this.width >> 1) - innerWidth);
            this.yAdjustment = this.xAdjustment;
        }
    }

    @Override
   public void paintXY(Graphics graphics, int x, int y) {
      
       this.basicSetColorUtil.setBasicColorP3(
               graphics, this.getBasicColorP(), this.getColor());
      
      graphics.drawArc(x + this.xAdjustment, y + this.yAdjustment,
          this.width, this.height, 0, this.TOTAL_ANGLE);
   }
}
