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

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;

public class Layer
{
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    //- These methods are for optimizing to concrete classes
    public static final Integer ID = SmallIntegerSingletonFactory.getInstance().getInstance(0);

    protected int x;

    protected int y;

    protected int z = 3;
    
    protected int width;

    protected int height;

    protected boolean visible = true;

    public Layer(int width, int height)
    {
        setLayerWidth(width);
        setLayerHeight(height);
    }

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
    
    public void move(final int dx, final int dy)
    {
        //final PositionStrings positionStrings = PositionStrings.getInstance();
        //final StringMaker stringMaker = new StringMaker();
        //logUtil.put(stringMaker.append(
                //positionStrings.DX_LABEL).append(dx)
                //.append(positionStrings.DY_LABEL).append(dy)
                ////.append(positionStrings.DZ_LABEL).append(dz)
                //.toString(), this, MOVE);

        this.x += dx;
        this.y += dy;
        
        //stringMaker.delete(0, stringMaker.length());
        //this.toString(stringMaker);
        //logUtil.put(stringMaker.toString(), this, MOVE);
    }
    
    public void move(int dx, int dy, int dz)
    {
        //final PositionStrings positionStrings = PositionStrings.getInstance();
        //final StringMaker stringMaker = new StringMaker();
        //logUtil.put(stringMaker.append(positionStrings.DX_LABEL).append(dx).append(positionStrings.DY_LABEL).append(dy).append(positionStrings.DZ_LABEL).append(dz).toString(), this, "move");
        this.x += dx;
        this.y += dy;
        this.z += dz;
    }
    
    public final int getX()
    {
        return x;
    }

    public final int getY()
    {
        return y;
    }

    public final int getZ()
    {
        return z;
    }
    
    public final int getWidth()
    {
        return width;
    }

    public final int getHeight()
    {
        return height;
    }

    //3d games need to overide this now for correct screen to world mapping.
    public long getDepth()
    {
        return 0;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    public final boolean isVisible()
    {
        return visible;
    }
    
    //Should be overridden
    public void paint(Graphics graphics)
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        final CanvasStrings canvasStrings = CanvasStrings.getInstance();
        
        logUtil.put(commonStrings.NOT_IMPLEMENTED, this, canvasStrings.PAINT);
        
        //graphics.setColor(BasicColor.RED.intValue());
        //graphics.drawRect(x, y, width, height);
    }

    protected void setLayerWidth(int width)
    {
        if (width < 0)
        {
            throw new IllegalArgumentException();
        }
        this.width = width;
    }

    protected void setLayerHeight(int height)
    {
        if (height < 0)
        {
            throw new IllegalArgumentException();
        }
        this.height = height;
    }
    
    public void toString(final StringMaker stringBuffer) {
        
    }    
    
}
