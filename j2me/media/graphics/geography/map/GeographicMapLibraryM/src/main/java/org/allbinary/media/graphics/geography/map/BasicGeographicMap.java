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
package org.allbinary.media.graphics.geography.map;

import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.layer.Layer;

public class BasicGeographicMap
    extends SimpleGeographicMap
    implements GeographicMapInterface
{
    private final BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory;
    private final GeographicMapCellPositionFactoryInterface geographicMapCellPositionFactoryInterface;

    public BasicGeographicMap(Integer id, String name, int[] cellTypeIdToGeographicMapCellType,
            AllBinaryTiledLayer tiledLayer, BasicColor foregroundBasicColor,
            BasicColor backgroundBasicColor,
            GeographicMapCellPositionFactoryInterface geographicMapCellPositionFactoryInterface,
            GeographicMapCellPositionBaseFactory geographicMapCellPositionBaseFactory) throws Exception
    {
        super(id, name, cellTypeIdToGeographicMapCellType,
            tiledLayer, foregroundBasicColor, backgroundBasicColor);

        this.geographicMapCellPositionFactoryInterface = geographicMapCellPositionFactoryInterface;
        
        this.geographicMapCellPositionFactory =
                geographicMapCellPositionBaseFactory.getInstance(this);
    }

    //Use getPoint in GeographicMapCellPosition
    /*
    public GPoint getPointAt(
       GeographicMapCellPosition geographicMapCellPosition)
       throws Exception
    {
        AllBinaryTiledLayer allBinaryTiledLayer = this.getAllBinaryTiledLayer();
        int x = allBinaryTiledLayer.getCellHeight() * geographicMapCellPosition.getColumn();
        int y = allBinaryTiledLayer.getCellWidth() * geographicMapCellPosition.getRow();
        return PointFactory.getInstance(x, y);
    }
    */

    public GeographicMapCellPosition getCellPositionAt(int x, int y) throws Exception
    {
        AllBinaryTiledLayer allBinaryTiledLayer = this.getAllBinaryTiledLayer();
        int i_column = Math.abs(x / allBinaryTiledLayer.getCellHeight());
        int i_row = Math.abs(y / allBinaryTiledLayer.getCellWidth());

        return geographicMapCellPositionFactory.getInstance(i_column, i_row);
    }

    public GeographicMapCellPosition getCellPositionAtNoThrow(int x, int y) throws Exception
    {
        AllBinaryTiledLayer allBinaryTiledLayer = this.getAllBinaryTiledLayer();
        int i_column = Math.abs(x / allBinaryTiledLayer.getCellHeight());
        int i_row = Math.abs(y / allBinaryTiledLayer.getCellWidth());

        if(allBinaryTiledLayer.getColumns() > i_column &&
                allBinaryTiledLayer.getRows() > i_row)
        {
            return geographicMapCellPositionFactory.getInstance(i_column, i_row);
        }
        else
        {
            return null;
        }
    }

    // Assumes array is tied to the ratio of tile cell width and height
    public boolean getCellPositionsAt(Layer layer,
            GeographicMapCellPosition[][] currentCellPositionArray,
            GeographicMapCellPosition[][] cellPositionArray) throws Exception
    {
        boolean hasChanged = false;

        int size = cellPositionArray.length;
        int size2 = cellPositionArray[0].length;

        int xPortion = layer.getX() / (size - 1);
        int yPortion = layer.getY() / (size - 1);
        for (int index = 0; index < size; index++)
        {
            for (int index2 = 0; index2 < size2; index2++)
            {
                int x = xPortion * index;
                int y = yPortion * index;
                cellPositionArray[index][index2] = this.getCellPositionAt(x, y);

                if (currentCellPositionArray[index][index2] != cellPositionArray[index][index2])
                {
                    hasChanged = true;
                }
            }
        }

        return hasChanged;
    }

    public GeographicMapCellType getCellTypeAt(int x, int y) throws Exception
    {
        GeographicMapCellPosition cellPosition = this.getCellPositionAt(x, y);
        return this.getCellTypeAt(cellPosition);
    }

    public BasicGeographicMapCellPositionFactory getGeographicMapCellPositionFactory()
    {
        return geographicMapCellPositionFactory;
    }

    /**
     * @return the geographicMapCellPositionFactoryInterface
     */
    public GeographicMapCellPositionFactoryInterface getGeographicMapCellPositionFactoryInterface()
    {
        return geographicMapCellPositionFactoryInterface;
    }
}