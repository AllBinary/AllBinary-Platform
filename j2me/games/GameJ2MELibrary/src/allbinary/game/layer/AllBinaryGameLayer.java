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
package allbinary.game.layer;

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.NotImplemented;
import abcs.logic.communication.log.ForcedLogUtil;
import allbinary.graphics.Rectangle;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.color.BasicColorSetUtil;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;
import allbinary.view.ViewPosition;
import allbinary.view.event.ViewPositionEvent;
import allbinary.view.event.ViewPositionEventListenerInterface;

public class AllBinaryGameLayer extends AllBinaryLayer 
   implements ViewPositionEventListenerInterface
{
    private final BasicArrayList gameKeyEventList = new BasicArrayList();

    /*
     * public AllBinaryGameLayer(int x, int y, int width, int height) { super(x,
     * y, width, height); }
     */
    public AllBinaryGameLayer(Rectangle layerInfo)
    {
        this(layerInfo, new ViewPosition());
    }

    public AllBinaryGameLayer(Rectangle layerInfo, ViewPosition viewPosition)
    {
        super(layerInfo, viewPosition);
    }

    public BasicArrayList getGameKeyEventList()
    {
        return gameKeyEventList;
    }

    public void processInput(AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    public void processTick(AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);

        //this.onGeographicMapCellPositionEvent((GeographicMapCellPositionEvent) eventObject);
    }

    public void onViewPositionChangeEvent() throws Exception
    {
        this.onChangeEvent(this.viewPositionEvent);
    }

    public void onChangeEvent(ViewPositionEvent layerManagerEvent)
       throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onChangeEvent"));
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
    public void paint(Graphics graphics)
    {
        //LogUtil.put(LogFactory.getInstance(NotImplemented.NAME, this, "paint"));

        ViewPosition viewPosition = this.getViewPosition();
        int viewX = viewPosition.getX();
        int viewY = viewPosition.getY();

        //LogUtil.put(LogFactory.getInstance("viewX: " + viewX + " viewY: " + viewY, this, "paint"));

        this.basicColorUtil.setBasicColor(graphics, RED);

        graphics.drawRect(viewX, viewY, this.getWidth(), this.getHeight());
        //this.getViewPosition().getX2() - viewX,
        //this.getViewPosition().getY2() - viewY);

        //graphics.drawRect(viewX, viewY, width, height);

        //super.paint(graphics);
    }
    
    public void setWidth(int width)
    {
    	super.setWidthImpl(width);
    	this.setHalfWidth(width >> 1);
    }

    public void setHeight(int height)
    {
    	super.setHeightImpl(height);
    	this.setHalfHeight(height >> 1);
    }    
}