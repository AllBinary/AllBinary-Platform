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

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.view.ViewPosition;
import org.allbinary.view.event.ViewPositionEvent;
import org.allbinary.view.event.ViewPositionEventListenerInterface;

public class AllBinaryGameLayer extends AllBinaryLayer 
   implements ViewPositionEventListenerInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
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

    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);

        //this.onGeographicMapCellPositionEvent((GeographicMapCellPositionEvent) eventObject);
    }

    public void onViewPositionChangeEvent() throws Exception
    {
        this.onChangeEvent(this.viewPositionEvent);
    }

    public void onChangeEvent(final ViewPositionEvent layerManagerEvent)
       throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "onChangeEvent"));
        if (GameLayerUtil.isOnScreen(this))
        {
            this.setVisible(true);
        }
        else
        {
            this.setVisible(false);
        }
    }
    
    protected final BasicColorSetUtil basicColorUtil = 
        BasicColorSetUtil.getInstance();
    
    private final BasicColor RED = BasicColorFactory.getInstance().RED;
    
    //Should be overridden
    public void paint(final Graphics graphics)
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.NOT_IMPLEMENTED, this, "paint"));

        final ViewPosition viewPosition = this.getViewPosition();
        final int viewX = viewPosition.getX();
        final int viewY = viewPosition.getY();

        //LogUtil.put(LogFactory.getInstance("viewX: ").append(viewX).append(" viewY: ").append(viewY, this, "paint"));

        this.basicColorUtil.setBasicColor(graphics, RED);

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