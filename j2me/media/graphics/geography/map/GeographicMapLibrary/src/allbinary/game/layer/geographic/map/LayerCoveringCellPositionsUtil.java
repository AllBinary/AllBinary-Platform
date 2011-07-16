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
package allbinary.game.layer.geographic.map;

import org.allbinary.util.BasicArrayList;

import allbinary.layer.AllBinaryLayer;
import allbinary.media.graphics.geography.map.BasicGeographicMap;
import allbinary.media.graphics.geography.map.CellPositionsUtil;
import allbinary.media.graphics.geography.map.GeographicMapCellPosition;

/**
 *
 * @author user
 */
public class LayerCoveringCellPositionsUtil {

    public static final BasicArrayList getAll(
            BasicGeographicMap geographicMapInterface,
            AllBinaryLayer layerInterface,
        BasicArrayList reusableList)
        throws Exception
    {
        GeographicMapCellPosition topLeftGeographicMapCellPosition =
            geographicMapInterface.getCellPositionAt(
            layerInterface.getX(), layerInterface.getY());

        return LayerCoveringCellPositionsUtil.getAll(
            geographicMapInterface,
            topLeftGeographicMapCellPosition,
            layerInterface, reusableList);
    }

    public static final BasicArrayList getAll(
            BasicGeographicMap geographicMapInterface,
        GeographicMapCellPosition topLeftGeographicMapCellPosition,
        AllBinaryLayer layerInterface,
        BasicArrayList reusableList)
        throws Exception
    {
        reusableList.clear();

        int columns = layerInterface.getWidth() /
            geographicMapInterface.getAllBinaryTiledLayer().getCellWidth();

        int rows = layerInterface.getHeight() /
            geographicMapInterface.getAllBinaryTiledLayer().getCellHeight();

        if(columns == 0)
            columns = 1;
        if(rows == 0)
            rows = 1;

        return CellPositionsUtil.getInstance().getAll(
            geographicMapInterface,
            topLeftGeographicMapCellPosition,
            columns, rows, reusableList);
    }

}
