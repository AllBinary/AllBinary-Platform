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
package org.allbinary.input.motion.button;

import org.allbinary.animation.Animation;
import org.allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.graphics.CellPosition;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;

public class FullTouchButton extends TouchButton
{
    public static FullTouchButton createButton(final TouchButtonInput touchButtonInput, final TouchButtonResource touchButtonResource,
                                               final Rectangle rawRectangle, final CellPosition cellPosition, final int xBorder, final int yBorder)
                                         throws Exception
    {
        return new FullTouchButton(touchButtonInput,
                FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().get(
                        touchButtonResource.RESOURCE).getInstance(0),
                FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().get(
                        touchButtonResource.HINT).getInstance(0),
                rawRectangle, cellPosition, xBorder, yBorder);
    }

    public FullTouchButton(final TouchButtonInput touchButtonInput,
                           final Animation animationInterface,
                           final Animation hintAnimationInterface,
                           final Rectangle rawRectangle, final CellPosition cellPosition, final int xBorder, final int yBorder)
            throws Exception
    {
        super(touchButtonInput, 
                animationInterface, hintAnimationInterface,
                rawRectangle, cellPosition, xBorder, yBorder);
    }

    @Override
    protected void updateRectangle()
    {
        try
        {
            final PointFactory pointFactory = PointFactory.getInstance();
            final int x = this.rawRectangle.getWidth() * this.cellPositionP.getColumn();
            final int y = this.rawRectangle.getHeight() * this.cellPositionP.getRow();

            this.rectangleP = new Rectangle(pointFactory.createXY(x, y),
                    this.rawRectangle.getWidth() + (2 * this.xBorder), this.rawRectangle.getHeight() + (2 * this.yBorder));
            
            final GPoint point = this.rectangleP.getPoint();
            this.animationX = point.getX() + this.xBorder;
            this.animationY = point.getY() + this.yBorder;
            this.hintAnimationY = this.animationY - this.rectangleP.getHeight() >> 1;
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "updateRectangle", e);
        }
    }

}
