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
package org.allbinary.media.graphics.geography.map.drop;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import org.allbinary.graphics.CellPosition;
import org.allbinary.graphics.CellPositionFactory;
import org.allbinary.layer.AllBinaryLayer;

/**
 *
 * @author user
 */
public class DropCellPositionHistory
{
    private static final DropCellPositionHistory SINGLETON = new DropCellPositionHistory();

    public static DropCellPositionHistory getInstance()
    {
        return SINGLETON;
    }
    
    private final CellPositionFactory cellPositionFactory = CellPositionFactory.getInstance();
    
    private BasicArrayList positionList = new BasicArrayListD();
    private BasicArrayList layerList = new BasicArrayListD();

    public void add(final CellPosition cellPosition, final AllBinaryLayer layerInterface)
    {
        this.positionList.add(cellPosition);
        this.layerList.add(layerInterface);
    }

    public void addAll(final BasicArrayList list, final AllBinaryLayer layerInterface)
    {
        for (int index = list.size() - 1; index >= 0; index--)
        {
            this.add((CellPosition) list.get(index), layerInterface);
        }
    }

    public void remove(final CellPosition cellPosition)
    {
        int index = this.positionList.indexOf(cellPosition);
        if (index >= 0)
        {
            this.positionList.removeAt(index);
            this.layerList.removeAt(index);
        }
    }

    public void removeAll(final AllBinaryLayer layerInterface)
    {
        int index = 0;

        while (index != -1)
        {
            index = this.layerList.indexOf(layerInterface);
            if (index >= 0)
            {
                this.positionList.removeAt(index);
                this.layerList.removeAt(index);
            }
        }
    }

    public void clear()
    {
        this.positionList.clear();
        this.layerList.clear();
    }

    public boolean isCellPositionWithDrop(final CellPosition cellPosition)
    {
        return this.positionList.contains(cellPosition);
    }

    public boolean anyCellPositionWithDrop(final BasicArrayList list)
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

    public CellPosition getCellPositionWithDrop(final BasicArrayList list)
    {
        CellPosition cellPosition;
        for (int index = list.size() - 1; index >= 0; index--)
        {
            cellPosition = (CellPosition) list.get(index);
            
            if (this.isCellPositionWithDrop(cellPosition))
            {
                return cellPosition;
            }
        }
        return this.cellPositionFactory.NONE;
    }
    
    public AllBinaryLayer getLayerInterface(final CellPosition cellPosition)
    {
        int index = this.positionList.indexOf(cellPosition);
        if (index >= 0)
        {
            return (AllBinaryLayer) this.layerList.get(index);
        }
        else
        {
            return AllBinaryLayer.NULL_ALLBINARY_LAYER;
        }
    }
}
