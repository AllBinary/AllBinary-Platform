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


import org.allbinary.util.BasicArrayList;

import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.util.BasicArrayListS;

/**
 *
 * @author user
 */
public class CellPositionsUtil
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final CellPositionsUtil instance = new CellPositionsUtil();
    
    public static CellPositionsUtil getInstance()
    {
        return CellPositionsUtil.instance;
    }
    
    public final BasicArrayList getAll(
            final BasicGeographicMap geographicMapInterface,
            final GeographicMapCellPosition topRightGeographicMapCellPosition,
            final int columns, final int rows, final BasicArrayList reusableList)
        throws Exception
    {
        reusableList.clear();

        final BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
            geographicMapInterface.getGeographicMapCellPositionFactory();

        final int lastColumn = topRightGeographicMapCellPosition.getColumn() + columns;
        final int lastRow = topRightGeographicMapCellPosition.getRow() + rows;

//        this.logUtil.putF("Columns: " + columns + " Rows: " + rows + " LastColumn: " + lastColumn + " lastRow: " + lastRow, this, "addAll");

        if ((columns > 1 && lastColumn > geographicMapInterface.getAllBinaryTiledLayer().getColumns()) ||
            (rows > 1 && lastRow > geographicMapInterface.getAllBinaryTiledLayer().getRows()))
        {
            return reusableList;
        }

//        this.logUtil.putF("layerInterface.getWidth(): " + layerInterface.getWidth() + " geographicMapInterface.getAllBinaryTiledLayer().getCellWidth(): " + geographicMapInterface.getAllBinaryTiledLayer().getCellWidth(), this, "addAll");


        for (int rowIndex = 0; rowIndex < rows; rowIndex++)
        {
            for (int columnIndex = 0; columnIndex < columns; columnIndex++)
            {
                GeographicMapCellPosition geographicMapCellPosition =
                    geographicMapCellPositionFactory.getAt(
                    topRightGeographicMapCellPosition.getColumn() + columnIndex,
                    topRightGeographicMapCellPosition.getRow() + rowIndex);

//                this.logUtil.putF("GeographicMapCellPosition: " + geographicMapCellPosition, this, "addAll");
//                this.logUtil.putF("columnIndex: " + columnIndex + " rowIndex: " + rowIndex, this, "addAll");
                reusableList.add(geographicMapCellPosition);
            }
        }
        
//        this.logUtil.putF(new StringMaker().append("reusableList: ").append(reusableList).append(" topRightGeographicMapCellPosition: ").append(topRightGeographicMapCellPosition).toString(), this, "visit");
        return reusableList;
    }

    private final BasicArrayList reusableSingleThreadedSurroundingList = 
        new BasicArrayListS(8);

    public final BasicArrayList getAllSurrounding(
            final BasicGeographicMap geographicMapInterface,
            final BasicArrayList occupyList,
            final BasicArrayList reusableList)
        throws Exception
    {
        reusableList.clear();

        for (int index = occupyList.size() - 1; index >= 0; index--)
        {
            final GeographicMapCellPosition layerGeographicMapCellPosition =
                (GeographicMapCellPosition) occupyList.get(index);

            final BasicArrayList surroundingGeographicMapCellPositionList = getAllSurroundingAt(
                geographicMapInterface, 
                layerGeographicMapCellPosition,
                reusableSingleThreadedSurroundingList);

            for (int index2 = surroundingGeographicMapCellPositionList.size() - 1; index2 >= 0; index2--)
            {
                final GeographicMapCellPosition geographicMapCellPosition =
                    (GeographicMapCellPosition) surroundingGeographicMapCellPositionList.get(index2);

                //Add if not already in list or not occupied
                if (!reusableList.contains(geographicMapCellPosition) &&
                    !occupyList.contains(geographicMapCellPosition))
                {
                    reusableList.add(geographicMapCellPosition);
                }
            }
        }

        //this.logUtil.putF("list: " + list.size(), this, "getAllSurrounding");

        return reusableList;
    }
    private final int[][] surroundArray =
    {
        {
            -1, -1
        },
        {
            0, -1
        },
        {
            1, -1
        },
        {
            -1, 1
        },
        {
            0, 1
        },
        {
            1, 1
        },
        {
            1, 0
        },
        {
            -1, 0
        },
    };
    //new int[4][2];

    public final BasicArrayList getAllSurroundingAt(
            final BasicGeographicMap geographicMapInterface,
            final GeographicMapCellPosition layerGeographicMapCellPosition,
            final BasicArrayList reusableSurroundingList)
        throws Exception
    {
        reusableSurroundingList.clear();

        final BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
            geographicMapInterface.getGeographicMapCellPositionFactory();

        AllBinaryTiledLayer tiledLayer =
            geographicMapInterface.getAllBinaryTiledLayer();

       int row;
       int column;

        for (int index = 0; index < 8; index++)
        {
            column = layerGeographicMapCellPosition.getColumn() + surroundArray[index][0];
            row = layerGeographicMapCellPosition.getRow() + surroundArray[index][1];

            if (tiledLayer.isOnTileLayer(column, row))
            {
                final GeographicMapCellPosition geographicMapCellPosition =
                    geographicMapCellPositionFactory.getAt(
                    column, row);

                reusableSurroundingList.add(geographicMapCellPosition);
            }
        }
        return reusableSurroundingList;
    }
}
