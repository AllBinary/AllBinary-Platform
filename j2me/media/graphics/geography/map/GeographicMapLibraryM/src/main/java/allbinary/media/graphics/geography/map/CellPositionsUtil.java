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
package allbinary.media.graphics.geography.map;

import org.allbinary.util.BasicArrayList;

import org.allbinary.game.layer.AllBinaryTiledLayer;

/**
 *
 * @author user
 */
public class CellPositionsUtil
{
    private static final CellPositionsUtil instance = new CellPositionsUtil();
    
    public static CellPositionsUtil getInstance()
    {
        return instance;
    }
    
    public final BasicArrayList getAll(
            BasicGeographicMap geographicMapInterface,
        GeographicMapCellPosition topRightGeographicMapCellPosition,
        int columns, int rows,
        BasicArrayList reusableList)
        throws Exception
    {
        reusableList.clear();

        BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
            geographicMapInterface.getGeographicMapCellPositionFactory();

        int lastColumn = topRightGeographicMapCellPosition.getColumn() + columns;
        int lastRow = topRightGeographicMapCellPosition.getRow() + rows;

        /*
        LogUtil.put(LogFactory.getInstance(
            "Columns: " + columns + " Rows: " + rows + " LastColumn: " + lastColumn + " lastRow: " + lastRow,
            "CellPositionUtil", "addAll"));
         */

        if ((columns > 1 && lastColumn > geographicMapInterface.getAllBinaryTiledLayer().getColumns()) ||
            (rows > 1 && lastRow > geographicMapInterface.getAllBinaryTiledLayer().getRows()))
        {
            return reusableList;
        }

        /*
        LogUtil.put(LogFactory.getInstance(
            "layerInterface.getWidth(): " + layerInterface.getWidth() +
            " geographicMapInterface.getAllBinaryTiledLayer().getCellWidth(): " +
            geographicMapInterface.getAllBinaryTiledLayer().getCellWidth(),
            "CellPositionUtil", "addAll"));
         */

        for (int rowIndex = 0; rowIndex < rows; rowIndex++)
        {
            for (int columnIndex = 0; columnIndex < columns; columnIndex++)
            {
                GeographicMapCellPosition geographicMapCellPosition =
                    geographicMapCellPositionFactory.getInstance(
                    topRightGeographicMapCellPosition.getColumn() + columnIndex,
                    topRightGeographicMapCellPosition.getRow() + rowIndex);

//            LogUtil.put(LogFactory.getInstance(
                //              "GeographicMapCellPosition: " + geographicMapCellPosition,
                //            "CellPositionUtil", "addAll"));
                reusableList.add(geographicMapCellPosition);
            }
        }
        return reusableList;
    }

    private final BasicArrayList reusableSingleThreadedSurroundingList = 
        new BasicArrayList(8);

    public final BasicArrayList getAllSurrounding(
            BasicGeographicMap geographicMapInterface,
        BasicArrayList occupyList,
        BasicArrayList reusableList)
        throws Exception
    {
        reusableList.clear();

        for (int index = occupyList.size() - 1; index >= 0; index--)
        {
            GeographicMapCellPosition layerGeographicMapCellPosition =
                (GeographicMapCellPosition) occupyList.get(index);

            BasicArrayList surroundingGeographicMapCellPositionList = getAllSurrounding(
                geographicMapInterface, 
                layerGeographicMapCellPosition,
                reusableSingleThreadedSurroundingList);

            for (int index2 = surroundingGeographicMapCellPositionList.size() - 1; index2 >= 0; index2--)
            {
                GeographicMapCellPosition geographicMapCellPosition =
                    (GeographicMapCellPosition) surroundingGeographicMapCellPositionList.get(index2);

                //Add if not already in list or not occupied
                if (!reusableList.contains(geographicMapCellPosition) &&
                    !occupyList.contains(geographicMapCellPosition))
                {
                    reusableList.add(geographicMapCellPosition);
                }
            }
        }

        //LogUtil.put(LogFactory.getInstance(
          //  "list: " + list.size(), "CellPositionUtil", "getAllSurrounding"));

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

    public final BasicArrayList getAllSurrounding(
            BasicGeographicMap geographicMapInterface,
        GeographicMapCellPosition layerGeographicMapCellPosition,
        BasicArrayList reusableSurroundingList)
        throws Exception
    {
        reusableSurroundingList.clear();

        BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
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
                GeographicMapCellPosition geographicMapCellPosition =
                    geographicMapCellPositionFactory.getInstance(
                    column, row);

                reusableSurroundingList.add(geographicMapCellPosition);
            }
        }
        return reusableSurroundingList;
    }
}
