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
package org.allbinary.game.layer.geographic.map;

import org.allbinary.util.BasicArrayList;

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.CellPositionsUtil;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

/**
 *
 * @author user
 */
public class LayerCoveringCellPositionsUtil {
    //protected final LogUtil logUtil = LogUtil.getInstance();


    private static final LayerCoveringCellPositionsUtil instance = new LayerCoveringCellPositionsUtil();

    /**
     * @return the instance
     */
    public static LayerCoveringCellPositionsUtil getInstance() {
        return instance;
    }
    
    private final CellPositionsUtil cellPositionsUtil = CellPositionsUtil.getInstance();
    
    public final BasicArrayList getAll(
            final BasicGeographicMap geographicMapInterface,
            final AllBinaryLayer layerInterface,
            final int x, final int y,
        final BasicArrayList reusableList)
        throws Exception
    {
        final GeographicMapCellPosition topLeftGeographicMapCellPosition =
            geographicMapInterface.getCellPositionAt(x, y);
        
        return this.getAll(
            geographicMapInterface,
            topLeftGeographicMapCellPosition,
            layerInterface, reusableList);
    }

    public final BasicArrayList getAll(
            final BasicGeographicMap geographicMapInterface,
        final GeographicMapCellPosition topLeftGeographicMapCellPosition,
        final AllBinaryLayer layerInterface,
        final BasicArrayList reusableList)
        throws Exception
    {
        reusableList.clear();

        int columns = layerInterface.getWidth() /
            geographicMapInterface.getAllBinaryTiledLayer().getCellWidth();
        
        int rows = layerInterface.getHeight() /
            geographicMapInterface.getAllBinaryTiledLayer().getCellHeight();

        //logUtil.put(new StringMaker().append("c/r: ").append(columns).append('/').append(rows).toString(), this, "visit");
        
        if(columns == 0)
            columns = 1;
        if(rows == 0)
            rows = 1;

        return cellPositionsUtil.getAll(
            geographicMapInterface,
            topLeftGeographicMapCellPosition,
            columns, rows, reusableList);
    }

}
