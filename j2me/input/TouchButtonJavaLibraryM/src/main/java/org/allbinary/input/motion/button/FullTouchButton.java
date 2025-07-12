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
import org.allbinary.graphics.CellPosition;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.logic.communication.log.LogUtil;

public class FullTouchButton extends TouchButton
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public FullTouchButton(TouchButtonInput touchButtonInput, TouchButtonResource touchButtonResource,
            Rectangle rawRectangle, CellPosition cellPosition, int xBorder, int yBorder)
            throws Exception
    {
        super(touchButtonInput, touchButtonResource,
                rawRectangle, cellPosition, xBorder, yBorder);
    }

    public FullTouchButton(TouchButtonInput touchButtonInput, 
            Animation animationInterface, 
            Animation hintAnimationInterface,
            Rectangle rawRectangle, CellPosition cellPosition, int xBorder, int yBorder)
            throws Exception
    {
        super(touchButtonInput, 
                animationInterface, hintAnimationInterface,
                rawRectangle, cellPosition, xBorder, yBorder);
    }

    protected void updateRectangle()
    {
        try
        {
            int x = this.rawRectangle.getWidth() * cellPosition.getColumn();
            int y = this.rawRectangle.getHeight() * cellPosition.getRow();

            this.rectangle = new Rectangle(PointFactory.getInstance().getInstance(x, y),
                    this.rawRectangle.getWidth() + (2 * xBorder), this.rawRectangle.getHeight() + (2 * yBorder));
            
            GPoint point = rectangle.getPoint();
            this.animationX = point.getX() + xBorder;
            this.animationY = point.getY() + yBorder;
            this.hintAnimationY = animationY - this.rectangle.getHeight() >> 1;
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "updateRectangle", e);
        }
    }

}
