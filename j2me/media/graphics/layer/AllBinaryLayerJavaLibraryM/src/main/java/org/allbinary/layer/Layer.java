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
package org.allbinary.layer;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class Layer
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    //- These methods are for optimizing to concrete classes
    @JsProperty
    public static final Integer ID = SmallIntegerSingletonFactory.getInstance().getAt(0);

    @JsProperty
    protected int x;

    @JsProperty
    protected int y;

    @JsProperty
    protected int z = 3;
    
    private int width;

    private int height;

    private boolean visible = true;

    @JsConstructor
    public Layer(int width, int height)
    {
        this.setLayerWidth(width);
        this.setLayerHeight(height);
    }

    @JsMethod
    public void setPosition(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        
        /*
        if(this.z != z)
        {
            ForcedLogUtil.log(this.z + " != " + z, this);
        }
        */
        this.z = z;
    }

    //private static final String MOVE = "move";
    
    @JsMethod
    public void moveDXY(final int dx, final int dy)
    {
        //final PositionStrings positionStrings = PositionStrings.getInstance();
        //final StringMaker stringMaker = new StringMaker();
        //this.logUtil.putF(stringMaker.append(
                //positionStrings.DX_LABEL).append(dx)
                //.append(positionStrings.DY_LABEL).append(dy)
                ////.append(positionStrings.DZ_LABEL).append(dz)
                //.toString(), this, MOVE);

        this.x += dx;
        this.y += dy;
        
        //stringMaker.delete(0, stringMaker.length());
        //this.toString(stringMaker);
        //this.logUtil.putF(stringMaker.toString(), this, MOVE);
    }
    
    @JsMethod
    public void moveDXYZ(int dx, int dy, int dz)
    {
        //final PositionStrings positionStrings = PositionStrings.getInstance();
        //final StringMaker stringMaker = new StringMaker();
        //this.logUtil.putF(stringMaker.append(positionStrings.DX_LABEL).append(dx).append(positionStrings.DY_LABEL).append(dy).append(positionStrings.DZ_LABEL).append(dz).toString(), this, "move");
        this.x += dx;
        this.y += dy;
        this.z += dz;
    }
    
    @JsMethod
    public final int getXP()
    {
        return this.x;
    }

    @JsMethod
    public final int getYP()
    {
        return this.y;
    }

    @JsMethod
    public final int getZP()
    {
        return this.z;
    }
    
    @JsMethod
    public final int getWidth()
    {
        return this.width;
    }

    @JsMethod
    public final int getHeight()
    {
        return this.height;
    }

    //3d games need to overide this now for correct screen to world mapping.
    @JsMethod
    public long getDepth()
    {
        return 0;
    }

    @JsMethod
    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    @JsMethod
    public final boolean isVisible()
    {
        return this.visible;
    }
    
    //Should be overridden
    @JsMethod
    public void paint(Graphics graphics)
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        final CanvasStrings canvasStrings = CanvasStrings.getInstance();
        
        this.logUtil.putF(commonStrings.NOT_IMPLEMENTED, this, canvasStrings.PAINT);
        
        //graphics.setColor(BasicColor.RED.intValue());
        //graphics.drawRect(x, y, width, height);
    }

    @JsMethod
    protected void setLayerWidth(int width)
    {
        if (width < 0)
        {
            throw new IllegalArgumentException();
        }
        this.width = width;
    }

    @JsMethod
    protected void setLayerHeight(int height)
    {
        if (height < 0)
        {
            throw new IllegalArgumentException();
        }
        this.height = height;
    }
    
    @JsMethod
    public void toStringAppend(final StringMaker stringBuffer) {
        
    }    
    
}
