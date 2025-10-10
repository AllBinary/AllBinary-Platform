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
package org.allbinary.game.layer;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.image.opengles.OpenGLSurfaceChangedInterface;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.view.ViewPosition;
import org.allbinary.view.event.ViewPositionEvent;
import org.allbinary.view.event.ViewPositionEventListenerInterface;

public class AllBinaryGameLayer extends AllBinaryLayer 
   implements ViewPositionEventListenerInterface, OpenGLSurfaceChangedInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    protected final BasicColor RED = BasicColorFactory.getInstance().RED;

    protected final BasicColorSetUtil basicSetColorUtil = BasicColorSetUtil.getInstance();
    
    private final Paintable paintable = SWTUtil.isSWT ? new Paintable() {
        
        private final BasicColor BLACK = BasicColorFactory.getInstance().BLACK;
        
        public void paint(Graphics graphics) {
            basicSetColorUtil.setBasicColorP(graphics, BLACK);
        }
    } : NullPaintable.getInstance();

    private final BasicArrayList gameKeyEventList = new BasicArrayList();

    public AllBinaryGameLayer(final Rectangle layerInfo)
    {
        this(layerInfo, new ViewPosition());
    }

    public AllBinaryGameLayer(final Rectangle layerInfo, final ViewPosition viewPosition)
    {
        super(layerInfo, viewPosition);
    }

    public AllBinaryGameLayer(final String name, final Rectangle layerInfo, final ViewPosition viewPosition)
    {
        super(name, layerInfo, viewPosition);
    }

    @Override
    public void set(final GL gl) throws Exception
    {
        //OpenGLSurfaceChangedInterface
    	throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }
    
    public void move() {

    }

    public BasicArrayList getGameKeyEventList()
    {
        return gameKeyEventList;
    }

    public void processInput(final AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }

    public void processTick(final AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }

    @Override
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);

        //this.onGeographicMapCellPositionEvent((GeographicMapCellPositionEvent) eventObject);
    }

    public void onViewPositionChangeEvent() throws Exception
    {
        this.onChangeEvent(this.viewPositionEvent);
    }

    @Override
    public void onChangeEvent(final ViewPositionEvent layerManagerEvent)
       throws Exception
    {
        //logUtil.put(commonStrings.START, this, "onChangeEvent");
        if (GameLayerUtil.isOnScreen(this))
        {
            this.setVisible(true);
        }
        else
        {
            this.setVisible(false);
        }
    }

    public void paintFirst(final Graphics graphics)
    {
        this.paintable.paint(graphics);
    }
    
    //Should be overridden
    public void paintDebug(final Graphics graphics)
    {
        //logUtil.put(commonStrings.NOT_IMPLEMENTED, this, canvasStrings.PAINT);

        final ViewPosition viewPosition = this.getViewPosition();
        final int viewX = viewPosition.getX();
        final int viewY = viewPosition.getY();

        //logUtil.put("viewX: ").append(viewX).append(" viewY: ").append(viewY, this, canvasStrings.PAINT);

        this.basicSetColorUtil.setBasicColorP(graphics, RED);

        graphics.drawRect(viewX, viewY, this.getWidth(), this.getHeight());
        //this.getViewPosition().getX2() - viewX,
        //this.getViewPosition().getY2() - viewY);

        //graphics.drawRect(viewX, viewY, width, height);

        //super.paint(graphics);
    }
    
    public void setWidth(final int width)
    {
    	super.setLayerWidth(width);
    	this.setHalfWidth(width >> 1);
    }

    public void setHeight(final int height)
    {
    	super.setLayerHeight(height);
    	this.setHalfHeight(height >> 1);
    }    
}