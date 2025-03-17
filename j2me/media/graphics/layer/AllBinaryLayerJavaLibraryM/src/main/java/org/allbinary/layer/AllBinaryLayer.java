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

import org.allbinary.graphics.opengles.OpenGLFeatureUtil;

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.game.identification.BasicGroupFactory;
import org.allbinary.game.identification.Group;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.SpacialStrings;
import org.allbinary.logic.string.CommonLabels;
import org.allbinary.math.PositionStrings;
import org.allbinary.view.ViewPosition;
import org.allbinary.view.event.ViewPositionEvent;

public class AllBinaryLayer 
extends Layer 
implements LayerInterface
{
    protected final ViewPositionEvent viewPositionEvent = new ViewPositionEvent(this);

    private int halfWidth;
    private int halfHeight;
    private final String name;

    private ViewPosition viewPosition;

    public AllBinaryLayer(final Rectangle rectangle, final ViewPosition viewPosition)
    {
        this(null, rectangle, viewPosition);
    }
    
    public AllBinaryLayer(final String name, final Rectangle rectangle, final ViewPosition viewPosition)
    {
        super(rectangle.getWidth(), rectangle.getHeight());

        if(name == null) {
            this.name = this.getClass().getName();
        } else {
            this.name = new StringMaker().append(name).append(CommonSeps.getInstance().COLON).append(Integer.toHexString(this.hashCode())).toString();
        }
        
        final GPoint point = rectangle.getPoint();

        this.setPosition(point.getX(), point.getY(), point.getZ());

        this.setHalfWidth((this.width >> 1));
        this.setHalfHeight((this.height >> 1));

        this.viewPosition = viewPosition;

        this.viewPosition.setAllbinaryLayer(this);
    }

    public void onChangeEvent(final ViewPositionEvent layerManagerEvent)
            throws Exception
    {
    }

    public void paintThreed(final Graphics graphics)
    {

    }

    public int getX2()
    {
        return this.x + this.getWidth();
    }

    public int getY2()
    {
        return this.y + this.getHeight();
    }

    public int getZ2()
    {
        return (int) (this.z + this.getDepth());
    }

    public String getName()
    {
        return name;
    }

    protected void setHalfWidth(final int halfWidth)
    {
        this.halfWidth = halfWidth;
    }

    public int getHalfWidth()
    {
        return halfWidth;
    }

    protected void setHalfHeight(final int halfHeight)
    {
        this.halfHeight = halfHeight;
    }

    public int getHalfHeight()
    {
        return halfHeight;
    }

    public int getHalfDepth()
    {
        return 0;
    }

    public ViewPosition getViewPosition()
    {
        return viewPosition;
    }

    public void setViewPosition(final ViewPosition viewPosition)
    {
        this.viewPosition = viewPosition;
    }

    public Group[] getGroupInterface()
    {
        return BasicGroupFactory.getInstance().NONE_ARRAY;
    }

    public boolean implmentsTickableInterface()
    {
        return false;
    }

    public boolean implmentsGameInputInterface()
    {
        return false;
    }

    public boolean implmentsArtificialIntelligenceCompositeInterface()
    {
        return false;
    }

    public boolean implmentsCollidableInterface()
    {
        return false;
    }

    public boolean implementsThreedInterface()
    {
        return OpenGLFeatureUtil.getInstance().isAnyThreed();
    }

    public int getType()
    {
        return -1;
    }

    public int getMultiPlayerType()
    {
        return -1;
    }

    private static final String TYPE = "Type: ";

    public void toString(final StringMaker stringBuffer)
    {
        final CommonSeps commonSeps = CommonSeps.getInstance();

        final PositionStrings positionStrings = PositionStrings.getInstance();
        final SpacialStrings spacialStrings = SpacialStrings.getInstance();

        stringBuffer.append(this.getName());
        stringBuffer.append(commonSeps.COLON);
        stringBuffer.append(TYPE);
        stringBuffer.append(this.getType());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.X_LABEL);
        stringBuffer.append(this.getX());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.Y_LABEL);
        stringBuffer.append(this.getY());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.Z_LABEL);
        stringBuffer.append(this.getZ());

        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(spacialStrings.WIDTH_LABEL);
        stringBuffer.append(this.getWidth());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(spacialStrings.HEIGHT_LABEL);
        stringBuffer.append(this.getHeight());

    }
    
    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();

        this.toString(stringBuffer);

        return stringBuffer.toString();
    }

}