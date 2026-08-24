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

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationInterface;
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
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class TouchButton extends Paintable
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    private final TouchButtonInput touchButtonInput;
    private final Animation animationInterface;
    
    @JsProperty
    protected final Rectangle rawRectangle;

    @JsProperty
    protected final int xBorder;
    @JsProperty
    protected final int yBorder;
    
    @JsProperty
    protected Rectangle rectangle = RectangleFactory.SINGLETON;
    @JsProperty
    protected final CellPosition cellPosition;
    
    @JsProperty
    protected int animationX;
    @JsProperty
    protected int animationY;
    
    @JsConstructor
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
        
        this.logUtil.putF(new StringMaker().append("Created: ").append(this.toString()).toString(), this, this.commonStrings.CONSTRUCTOR);
    }

    @Override
    @JsMethod
    public void paint(Graphics graphics)
    {
        this.animationInterface.paintXY(graphics, animationX, this.animationY);
        //graphics.drawRect(point.getX() + 4, point.getY() + 4, 
          //      rectangle.getMaxX() - 4, rectangle.getMaxY() - 4);
    }

    //This would probably be better as a builder
    @JsMethod
    protected void updateRectangle()
    {
        try
        {
            final int x = this.rawRectangle.getWidth() * this.cellPosition.getColumn();
            final int y = this.rawRectangle.getHeight() * this.cellPosition.getRow();

            final PointFactory pointFactory = PointFactory.getInstance();
            
            this.rectangle = new Rectangle(pointFactory.createXY(x + this.xBorder, y + this.yBorder),
                    this.rawRectangle.getWidth(), this.rawRectangle.getHeight());
            
            final GPoint point = this.rectangle.getPoint();
            this.animationX = point.getX();
            this.animationY = point.getY();
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "updateRectangle", e);
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

    @JsMethod
    public CellPosition getCellPositionP()
    {
        return this.cellPosition;
    }

    @JsMethod
    public Rectangle getRectangleP()
    {
        return this.rectangle;
    }

    @JsMethod
    public TouchButtonInput getTouchButtonInput()
    {
        return this.touchButtonInput;
    }

    @JsMethod
    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();
        final StringUtil stringUtil = StringUtil.getInstance();
        
        stringBuffer.append("TouchButton: ");
        stringBuffer.append(stringUtil.toString(this.rectangle));
        stringBuffer.append(" CellPosition: ");
        stringBuffer.append(stringUtil.toString(this.cellPosition));
        stringBuffer.append(" xBorder: ");
        stringBuffer.appendint(this.xBorder);
        stringBuffer.append(" yBorder: ");
        stringBuffer.appendint(this.yBorder);
        
        return stringBuffer.toString();
    }

}
