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
package allbinary.input.motion.button;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.animation.Animation;
import allbinary.graphics.CellPosition;
import allbinary.graphics.GPoint;
import allbinary.graphics.PointFactory;
import allbinary.graphics.Rectangle;

public class FullTouchButton extends TouchButton
{
    public FullTouchButton(TouchButtonInput touchButtonInput, Animation animationInterface,
            Rectangle rawRectangle, CellPosition cellPosition, int xBorder, int yBorder)
    {
        super(touchButtonInput, animationInterface,
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
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "updateRectangle", e));
        }
    }

}
