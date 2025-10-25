/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.input.form;

import javax.microedition.lcdui.game.SimpleTiledLayer;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.RTSLayerEvent;
import org.allbinary.game.layer.building.event.BuildingEventListenerInterface;
import org.allbinary.game.layer.building.event.LocalPlayerBuildingEventHandler;
import org.allbinary.util.BasicArrayList;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.graphics.CellPosition;
import org.allbinary.layer.event.ScrollMapEvent;
import org.allbinary.layer.event.ScrollMapEventHandler;
import org.allbinary.layer.event.ScrollMapEventListenerInterface;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;

public class VisibleCellPositionsSingleton
    implements BuildingEventListenerInterface, ScrollMapEventListenerInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final VisibleCellPositionsSingleton instance = 
        new VisibleCellPositionsSingleton();

    public static VisibleCellPositionsSingleton getInstance()
    {
        return instance;
    }

    private short[][] stationaryVisibleCellPositions = NullUtil.getInstance().NULL_SHORT_ARRAY_ARRAY;
    private short[][] visibleCellPositions = NullUtil.getInstance().NULL_SHORT_ARRAY_ARRAY;
    private short[][] currentlyVisibleCellPositions = NullUtil.getInstance().NULL_SHORT_ARRAY_ARRAY;

    public final SimpleTiledLayer ALL_VISIBLE_TILEDLAYER = new SimpleTiledLayer(0, 0, 0, 0);
    private SimpleTiledLayer simpleTiledLayer = ALL_VISIBLE_TILEDLAYER;
    private SimpleTiledLayer paintSimpleTiledLayer = ALL_VISIBLE_TILEDLAYER;

    private int currentIndex;

    private VisibleCellPositionsSingleton()
    {
        LocalPlayerBuildingEventHandler.getInstance().addListener(this);
        ScrollMapEventHandler.getInstance().addListener(this);
    }
    
    public void init(SimpleTiledLayer simpleTiledLayer)
    {
        this.paintSimpleTiledLayer = ALL_VISIBLE_TILEDLAYER;
        
        this.currentIndex = 0;
        
        int rows = simpleTiledLayer.getRows();
        int columns = simpleTiledLayer.getColumns();
        
        this.stationaryVisibleCellPositions = new short[rows][columns];
        this.visibleCellPositions = new short[rows][columns];
        this.currentlyVisibleCellPositions = new short[rows][columns];
        
        this.setSimpleTiledLayer(simpleTiledLayer);
    }

    @Override
    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }
    
    @Override
    public void onBuildingEvent(RTSLayerEvent event)
        throws Exception
    {
        this.paintSimpleTiledLayer = this.simpleTiledLayer;
    }
    
    @Override
    public void onMoveEvent(ScrollMapEvent scrollMapEvent)
        throws Exception
    {
        this.move(scrollMapEvent.getDx(), scrollMapEvent.getDy());
    }
    
    public void addStationaryCellPositions(BasicArrayList list)
    {
        for(int index = list.size() - 1; index >= 0; index--)
        {
            CellPosition cellPosition = (CellPosition) list.get(index);

            this.stationaryVisibleCellPositions[cellPosition.getRow()][cellPosition.getColumn()]++;
        }
    }

    public void removeStationaryCellPositions(BasicArrayList list)
    {
        for(int index = list.size() - 1; index >= 0; index--)
        {
            CellPosition cellPosition = (CellPosition) list.get(index);

            this.stationaryVisibleCellPositions[cellPosition.getRow()][cellPosition.getColumn()]--;
        }
    }
    
    //set currently visible to new data and clear old data
    public void update()
    {
        if(this.currentIndex == 0)
        {
            final short[][] temp = this.currentlyVisibleCellPositions;
            this.currentlyVisibleCellPositions = this.visibleCellPositions;
            this.visibleCellPositions = temp;

            for (int index = this.visibleCellPositions.length - 1; index >= 0; index--)
            {
                for (int index2 = this.visibleCellPositions[0].length - 1; index2 >= 0; index2--)
                {
                    this.visibleCellPositions[index][index2] = 
                        this.stationaryVisibleCellPositions[index][index2];
                }
            }
        }

        this.currentIndex++;
        
        if(this.currentIndex > 10)
        {
            this.currentIndex = 0;
        }        
    }

    public boolean shouldProcess()
    {
        if(this.currentIndex == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void addCellPositions(BasicArrayList list)
    {
        for(int index = list.size() - 1; index >= 0; index--)
        {
            CellPosition cellPosition = (CellPosition) list.get(index);
            
            this.visibleCellPositions[cellPosition.getRow()][cellPosition.getColumn()]++;
        }
    }

    public boolean isVisible(CellPosition cellPosition)
    {
        if(this.visibleCellPositions[cellPosition.getRow()][cellPosition.getColumn()] > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*
    public boolean isVisible(int column, int row)
    {
        if(this.visibleCellPositions[row][column] > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    */

    public void move(int dx, int dy)
    {
        this.getSimpleTiledLayer().move(dx, dy);
    }
    
    public void paint(Graphics graphics)
    {
        this.paintSimpleTiledLayer.paint(graphics, this.currentlyVisibleCellPositions);
    }

    private void setSimpleTiledLayer(SimpleTiledLayer simpleTiledLayer)
    {
        this.simpleTiledLayer = simpleTiledLayer;
    }

    public SimpleTiledLayer getSimpleTiledLayer()
    {
        return simpleTiledLayer;
    }
}
