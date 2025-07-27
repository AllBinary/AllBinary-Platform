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

import org.allbinary.game.identification.BasicGroupFactory;
import org.allbinary.game.identification.Group;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.RectangleFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.math.PositionStrings;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.view.ViewPosition;
import org.allbinary.view.event.ViewPositionEvent;

public class AllBinaryLayer 
extends Layer 
implements LayerInterface
{
    public static final AllBinaryLayer NULL_ALLBINARY_LAYER = new AllBinaryLayer(RectangleFactory.SINGLETON, ViewPosition.NULL_VIEW_POSITION);

    protected final ViewPositionEvent viewPositionEvent = new ViewPositionEvent(this);

    private int halfWidth;
    private int halfHeight;
    private final String name;

    private ViewPosition viewPosition = ViewPosition.NULL_VIEW_POSITION;

    public AllBinaryLayer(final Rectangle rectangle, final ViewPosition viewPosition)
    {
        this(StringUtil.getInstance().EMPTY_STRING, rectangle, viewPosition);
    }
    
    public AllBinaryLayer(final String name, final Rectangle rectangle, final ViewPosition viewPosition)
    {
        super(rectangle.getWidth(), rectangle.getHeight());

        final String localName;
        if(name == null) {
            localName = this.getClass().getName();
        } else {
            localName = new StringMaker().append(name).append(CommonSeps.getInstance().COLON).append(Integer.toHexString(this.hashCode())).toString();
        }
        this.name = localName;

        final GPoint point = rectangle.getPoint();

        this.setPosition(point.getX(), point.getY(), point.getZ());

        this.setHalfWidth((this.getWidth() >> 1));
        this.setHalfHeight((this.getHeight() >> 1));

        this.viewPosition = viewPosition;

        this.viewPosition.setAllbinaryLayer(this);
    }

    public void onChangeEvent(final ViewPositionEvent layerManagerEvent)
            throws Exception
    {
    }

    @Override
    public void paintThreed(final Graphics graphics)
    {

    }

    @Override
    public int getX2()
    {
        return this.getX() + this.getWidth();
    }

    @Override
    public int getY2()
    {
        return this.getY() + this.getHeight();
    }

    @Override
    public int getZ2()
    {
        return (int) (this.getZ() + this.getDepth());
    }

    @Override
    public String getName()
    {
        return name;
    }

    protected void setHalfWidth(final int halfWidth)
    {
        this.halfWidth = halfWidth;
    }

    @Override
    public int getHalfWidth()
    {
        return halfWidth;
    }

    protected void setHalfHeight(final int halfHeight)
    {
        this.halfHeight = halfHeight;
    }

    @Override
    public int getHalfHeight()
    {
        return halfHeight;
    }

    public int getHalfDepth()
    {
        return (int) this.getDepth() / 2;
    }

    public ViewPosition getViewPosition()
    {
        return viewPosition;
    }

    public void setViewPosition(final ViewPosition viewPosition)
    {
        this.viewPosition = viewPosition;
    }

    @Override
    public Group[] getGroupInterface()
    {
        return BasicGroupFactory.getInstance().NONE_ARRAY;
    }

    @Override
    public boolean implmentsTickableInterface()
    {
        return false;
    }

    @Override
    public boolean implmentsGameInputInterface()
    {
        return false;
    }

    @Override
    public boolean implmentsArtificialIntelligenceCompositeInterface()
    {
        return false;
    }

    @Override
    public boolean implmentsCollidableInterface()
    {
        return false;
    }

    public boolean implementsThreedInterface()
    {
        return OpenGLFeatureUtil.getInstance().isAnyThreed();
    }

    @Override
    public int getType()
    {
        return -1;
    }

    public int getMultiPlayerType()
    {
        return -1;
    }

    private static final String TYPE = "Type: ";

    @Override
    public void toString(final StringMaker stringBuffer)
    {
        final CommonSeps commonSeps = CommonSeps.getInstance();

        final PositionStrings positionStrings = PositionStrings.getInstance();
        final CommonLabels commonLabels = CommonLabels.getInstance();

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
        stringBuffer.append(commonLabels.WIDTH_LABEL);
        stringBuffer.append(this.getWidth());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(commonLabels.HEIGHT_LABEL);
        stringBuffer.append(this.getHeight());

    }
    
    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();

        this.toString(stringBuffer);

        return stringBuffer.toString();
    }

}