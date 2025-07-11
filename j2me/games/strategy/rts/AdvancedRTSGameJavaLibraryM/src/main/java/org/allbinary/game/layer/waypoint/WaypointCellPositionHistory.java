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
package org.allbinary.game.layer.waypoint;

import org.allbinary.util.BasicArrayList;

import org.allbinary.graphics.CellPosition;
import org.allbinary.layer.AllBinaryLayer;

/**
 *
 * @author user
 */
public class WaypointCellPositionHistory
{
    private static final WaypointCellPositionHistory SINGLETON = new WaypointCellPositionHistory();

    public static WaypointCellPositionHistory getInstance()
    {
        return SINGLETON;
    }
    private BasicArrayList positionList = new BasicArrayList();
    private BasicArrayList layerList = new BasicArrayList();

    public void add(CellPosition cellPosition, AllBinaryLayer layerInterface)
    {
        this.positionList.add(cellPosition);
        this.layerList.add(layerInterface);
    }

    public void add(BasicArrayList list, AllBinaryLayer layerInterface)
    {
        for (int index = list.size() - 1; index >= 0; index--)
        {
            this.add((CellPosition) list.get(index), layerInterface);
        }
    }

    public void remove(CellPosition cellPosition)
    {
        int index = this.positionList.indexOf(cellPosition);
        if (index >= 0)
        {
            this.positionList.remove(index);
            this.layerList.remove(index);
        }
    }

    public void remove(AllBinaryLayer layerInterface)
    {
        int index = 0;

        while (index != -1)
        {
            index = this.layerList.indexOf(layerInterface);
            if (index >= 0)
            {
                this.positionList.remove(index);
                this.layerList.remove(index);
            }
        }
    }

    public void clear()
    {
        this.positionList.clear();
        this.layerList.clear();
    }

    public boolean isCellPositionWithDrop(CellPosition cellPosition)
    {
        return positionList.contains(cellPosition);
    }

    public boolean anyCellPositionWithDrop(BasicArrayList list)
    {
        for (int index = list.size() - 1; index >= 0; index--)
        {
            if (this.isCellPositionWithDrop((CellPosition) list.get(index)))
            {
                return true;
            }
        }
        return false;
    }

    public AllBinaryLayer getLayerInterface(CellPosition cellPosition)
    {
        int index = this.positionList.indexOf(cellPosition);
        if (index >= 0)
        {
            return (AllBinaryLayer) this.layerList.get(index);
        }
        else
        {
            return null;
        }
    }
}
