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

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.graphics.CellPosition;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.RectangleFactory;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

public class TouchButton extends Paintable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final TouchButtonInput touchButtonInput;
    private final Animation animationInterface;
    private final Animation hintAnimationInterface;
    
    protected final Rectangle rawRectangle;

    protected final int xBorder;
    protected final int yBorder;
    
    protected Rectangle rectangleP = RectangleFactory.SINGLETON;
    protected final CellPosition cellPositionP;
    
    protected int animationX;
    protected int animationY;
    protected int hintAnimationY;
    
    public TouchButton(TouchButtonInput touchButtonInput, 
            TouchButtonResource touchButtonResource,            
            Rectangle rawRectangle, CellPosition cellPosition, int xBorder, int yBorder)
            throws Exception
    {            
            this(touchButtonInput, 
            FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().get(
                             touchButtonResource.RESOURCE).getInstance(0),
            FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().get(
                             touchButtonResource.HINT).getInstance(0),
            rawRectangle, cellPosition, xBorder, yBorder);
    }

    public TouchButton(TouchButtonInput touchButtonInput, 
            Animation animationInterface, 
            Animation hintAnimationInterface,
            Rectangle rawRectangle, CellPosition cellPosition, int xBorder, int yBorder)
            throws Exception
    {
        this.touchButtonInput = touchButtonInput;
            
        this.animationInterface = animationInterface;
        this.hintAnimationInterface = hintAnimationInterface;
                
        this.rawRectangle = rawRectangle;
        this.cellPositionP = cellPosition;
        this.xBorder = xBorder;
        this.yBorder = yBorder;
        
        this.updateRectangle();
        
        logUtil.put(new StringMaker().append("Created: ").append(this.toString()).toString(), this, commonStrings.CONSTRUCTOR);
    }
    
    public void paintHint(Graphics graphics)
    {
        this.hintAnimationInterface.paint(graphics, animationX, this.hintAnimationY);
    }
    
    @Override
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
            int x = this.rawRectangle.getWidth() * cellPositionP.getColumn();
            int y = this.rawRectangle.getHeight() * cellPositionP.getRow();

            this.rectangleP = new Rectangle(
                    PointFactory.getInstance().getInstance(x + xBorder, y + yBorder),
                    this.rawRectangle.getWidth(), this.rawRectangle.getHeight());
            
            GPoint point = rectangleP.getPoint();
            this.animationX = point.getX();
            this.animationY = point.getY();
            this.hintAnimationY = animationY - 32;
            //CommonButtons.getInstance().STANDARD_BUTTON_SIZE >> 1
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "updateRectangle", e);
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
        return cellPositionP;
    }

    public Rectangle getRectangle()
    {
        return rectangleP;
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
        stringBuffer.append(stringUtil.toString(this.cellPositionP));
        stringBuffer.append(" xBorder: ");
        stringBuffer.append(this.xBorder);
        stringBuffer.append(" yBorder: ");
        stringBuffer.append(this.yBorder);
        
        return stringBuffer.toString();
    }

}
