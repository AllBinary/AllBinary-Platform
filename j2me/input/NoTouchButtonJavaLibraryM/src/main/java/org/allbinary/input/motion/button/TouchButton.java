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

import org.allbinary.input.motion.button.TouchButtonInput;
import org.allbinary.logic.string.StringMaker;
import javax.microedition.lcdui.Graphics;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationInterface;
import org.allbinary.graphics.CellPosition;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

public class TouchButton extends Paintable
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    private final TouchButtonInput touchButtonInput;
    private final Animation animationInterface;
    
    protected final Rectangle rawRectangle;

    protected final int xBorder;
    protected final int yBorder;
    
    protected Rectangle rectangle;
    protected final CellPosition cellPosition;
    
    protected int animationX;
    protected int animationY;
    
    public TouchButton(TouchButtonInput touchButtonInput, AnimationInterface animationInterface,
            Rectangle rawRectangle, CellPosition cellPosition, int xBorder, int yBorder)
    {
        this.touchButtonInput = touchButtonInput;
        this.animationInterface = (Animation) animationInterface;
        
        this.rawRectangle = rawRectangle;
        this.cellPosition = cellPosition;
        this.xBorder = xBorder;
        this.yBorder = yBorder;
        
        this.updateRectangle();
        
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("Created: ").append(this.toString()).toString(), this, this.commonStrings.CONSTRUCTOR));
    }

    public void paint(Graphics graphics)
    {
        this.animationInterface.paint(graphics, animationX, animationY);
        //graphics.drawRect(point.getX() + 4, point.getY() + 4, 
          //      rectangle.getMaxX() - 4, rectangle.getMaxY() - 4);
    }

    //This would probably be better as a builder
    protected void updateRectangle()
    {
        try
        {
            int x = this.rawRectangle.getWidth() * cellPosition.getColumn();
            int y = this.rawRectangle.getHeight() * cellPosition.getRow();

            this.rectangle = new Rectangle(PointFactory.getInstance().getInstance(x + xBorder, y + yBorder),
                    this.rawRectangle.getWidth(), this.rawRectangle.getHeight());
            
            GPoint point = rectangle.getPoint();
            this.animationX = point.getX();
            this.animationY = point.getY();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, "updateRectangle", e));
        }
    }

    /*
    public void setRawRectangle(Rectangle rawRectangle)
    {
        this.rawRectangle = rawRectangle;
        this.updateRectangle();
    }
    */

    /*
    public void setCellPosition(CellPosition cellPosition)
    {
        this.cellPosition = cellPosition;
        this.updateRectangle();
    }
    */

    public CellPosition getCellPosition()
    {
        return cellPosition;
    }

    public Rectangle getRectangle()
    {
        return rectangle;
    }

    public TouchButtonInput getTouchButtonInput()
    {
        return touchButtonInput;
    }

    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();
        final StringUtil stringUtil = StringUtil.getInstance();
        
        stringBuffer.append("TouchButton: ");
        stringBuffer.append(stringUtil.toString(this.getRectangle()));
        stringBuffer.append(" CellPosition: ");
        stringBuffer.append(stringUtil.toString(this.cellPosition));
        stringBuffer.append(" xBorder: ");
        stringBuffer.append(this.xBorder);
        stringBuffer.append(" yBorder: ");
        stringBuffer.append(this.yBorder);
        
        return stringBuffer.toString();
    }

}
