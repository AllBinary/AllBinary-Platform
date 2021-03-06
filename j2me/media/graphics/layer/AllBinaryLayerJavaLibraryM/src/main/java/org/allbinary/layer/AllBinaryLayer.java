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

import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.game.identification.BasicGroupFactory;
import org.allbinary.game.identification.Group;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.SpacialStrings;
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
    private final String name = this.getClass().getName();

    private ViewPosition viewPosition;

    public AllBinaryLayer(Rectangle rectangle, ViewPosition viewPosition)
    {
        super(rectangle.getWidth(), rectangle.getHeight());

        GPoint point = rectangle.getPoint();

        this.setPosition(point.getX(), point.getY(), point.getZ());

        this.setHalfWidth((this.width >> 1));
        this.setHalfHeight((this.height >> 1));

        this.viewPosition = viewPosition;

        this.viewPosition.setAllbinaryLayer(this);
    }

    public void onChangeEvent(ViewPositionEvent layerManagerEvent)
            throws Exception
    {
    }

    public void paintThreed(Graphics graphics)
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
        return this.z + this.getDepth();
    }

    public String getName()
    {
        return name;
    }

    protected void setHalfWidth(int halfWidth)
    {
        this.halfWidth = halfWidth;
    }

    public int getHalfWidth()
    {
        return halfWidth;
    }

    protected void setHalfHeight(int halfHeight)
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

    public void setViewPosition(ViewPosition viewPosition)
    {
        this.viewPosition = viewPosition;
    }

    public Group getGroupInterface()
    {
        return BasicGroupFactory.getInstance().NONE;
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

    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();

        CommonSeps commonSeps = CommonSeps.getInstance();

        stringBuffer.append(commonSeps.NEW_LINE);
        stringBuffer.append(this.getName());
        stringBuffer.append(commonSeps.COLON_SEP);
        stringBuffer.append(TYPE);
        stringBuffer.append(this.getType());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(PositionStrings.getInstance().X_LABEL);
        stringBuffer.append(this.getX());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(PositionStrings.getInstance().Y_LABEL);
        stringBuffer.append(this.getY());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(PositionStrings.getInstance().Z_LABEL);
        stringBuffer.append(this.getZ());

        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(SpacialStrings.getInstance().WIDTH_LABEL);
        stringBuffer.append(this.getWidth());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(SpacialStrings.getInstance().HEIGHT_LABEL);
        stringBuffer.append(this.getHeight());

        return stringBuffer.toString();
    }
}