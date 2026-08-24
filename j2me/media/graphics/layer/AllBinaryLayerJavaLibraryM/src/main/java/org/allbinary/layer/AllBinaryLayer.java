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

import org.allbinary.game.identification.BasicGroupFactory;
import org.allbinary.game.identification.Group;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.RectangleFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureUtil;
import org.allbinary.TsUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.math.PositionStrings;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.view.ViewPosition;
import org.allbinary.view.ViewPositionBase;
import org.allbinary.view.event.ViewPositionEvent;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class AllBinaryLayer 
extends Layer 
implements LayerInterface
{
    @JsProperty
    public static final AllBinaryLayer NULL_ALLBINARY_LAYER = new AllBinaryLayer(StringUtil.getInstance().EMPTY_STRING, RectangleFactory.SINGLETON, ViewPositionBase.NULL_VIEW_POSITION);

    @JsProperty
    protected final ViewPositionEvent viewPositionEvent = new ViewPositionEvent(this);

    private int halfWidth;
    private int halfHeight;
    private final String name;

    private ViewPositionBase viewPosition = ViewPositionBase.NULL_VIEW_POSITION;

    @JsConstructor
    public AllBinaryLayer(final String name, final Rectangle rectangle, final ViewPositionBase viewPosition)
    {
        super(rectangle.getWidth(), rectangle.getHeight());

        final String localName;
        if(name == null) {
            localName = this.getClass().getName();
        } else {
            localName = new StringMaker().append(name).append(CommonSeps.getInstance().COLON).append(Integer.toHexString(TsUtil.getInstance().hashCode(this))).toString();
        }
        this.name = localName;

        final GPoint point = rectangle.getPoint();

        this.setPosition(point.getX(), point.getY(), point.getZ());

        this.setHalfWidth((this.getWidth() >> 1));
        this.setHalfHeight((this.getHeight() >> 1));

        this.viewPosition = viewPosition;

        this.viewPosition.setAllbinaryLayer(this);
    }

    @JsMethod
    public void onChangeEvent(final ViewPositionEvent layerManagerEvent)
            throws Exception
    {
    }

    @Override
    @JsMethod
    public void paintThreed(final Graphics graphics)
    {

    }

    @Override
    @JsMethod
    public int getX2()
    {
        return this.getXP() + this.getWidth();
    }

    @Override
    @JsMethod
    public int getY2()
    {
        return this.getYP() + this.getHeight();
    }

    @Override
    @JsMethod
    public int getZ2()
    {
        return (int) (this.getZP() + this.getDepth());
    }

    @Override
    @JsMethod
    public String getName()
    {
        return this.name;
    }

    @JsMethod
    protected void setHalfWidth(final int halfWidth)
    {
        this.halfWidth = halfWidth;
    }

    @Override
    @JsMethod
    public int getHalfWidth()
    {
        return this.halfWidth;
    }

    @JsMethod
    protected void setHalfHeight(final int halfHeight)
    {
        this.halfHeight = halfHeight;
    }

    @Override
    @JsMethod
    public int getHalfHeight()
    {
        return this.halfHeight;
    }

    @JsMethod
    public int getHalfDepth()
    {
        return (int) this.getDepth() / 2;
    }

    @JsMethod
    public ViewPositionBase getViewPosition()
    {
        return this.viewPosition;
    }

    @JsMethod
    public void setViewPosition(final ViewPositionBase viewPosition)
    {
        this.viewPosition = viewPosition;
    }

    @Override
    @JsMethod
    public Group[] getGroupInterface()
    {
        return BasicGroupFactory.getInstance().NONE_ARRAY;
    }

    @Override
    @JsMethod
    public boolean implmentsTickableInterface()
    {
        return false;
    }

    @Override
    @JsMethod
    public boolean implmentsGameInputInterface()
    {
        return false;
    }

    @Override
    @JsMethod
    public boolean implmentsArtificialIntelligenceCompositeInterface()
    {
        return false;
    }

    @Override
    @JsMethod
    public boolean implmentsCollidableInterface()
    {
        return false;
    }

    @JsMethod
    public boolean implementsThreedInterface()
    {
        return OpenGLFeatureUtil.getInstance().isAnyThreed();
    }

    @Override
    @JsMethod
    public int getType()
    {
        return -1;
    }

    @JsMethod
    public int getMultiPlayerType()
    {
        return -1;
    }

    private static final String TYPE = "Type: ";

    @Override
    @JsMethod
    public void toStringAppend(final StringMaker stringBuffer)
    {
        final CommonSeps commonSeps = CommonSeps.getInstance();

        final PositionStrings positionStrings = PositionStrings.getInstance();
        final CommonLabels commonLabels = CommonLabels.getInstance();

        stringBuffer.append(this.getName());
        stringBuffer.append(commonSeps.COLON);
        stringBuffer.append(AllBinaryLayer.TYPE);
        stringBuffer.appendint(this.getType());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.X_LABEL);
        stringBuffer.appendint(this.getXP());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.Y_LABEL);
        stringBuffer.appendint(this.getYP());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.Z_LABEL);
        stringBuffer.appendint(this.getZP());

        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(commonLabels.WIDTH_LABEL);
        stringBuffer.appendint(this.getWidth());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(commonLabels.HEIGHT_LABEL);
        stringBuffer.appendint(this.getHeight());

    }
    
    @JsMethod
    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();

        this.toStringAppend(stringBuffer);

        return stringBuffer.toString();
    }

}