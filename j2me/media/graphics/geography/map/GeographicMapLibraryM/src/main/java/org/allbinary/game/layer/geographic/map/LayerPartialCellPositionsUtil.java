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
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.SimpleGeographicMapCellPositionFactory;

/**
 *
 * @author user
 *
 * This class finds all GeographicMapCellPositions that a layer covers
 * including those that are partially covered.
 */
public class LayerPartialCellPositionsUtil {

    private static final LayerPartialCellPositionsUtil instance = new LayerPartialCellPositionsUtil();
    
    //Assumes that entire layer is on the map //not
    public final BasicArrayList getAll(
            BasicGeographicMap geographicMapInterface,
            AllBinaryLayer layerInterface,
        BasicArrayList reusableList)
        throws Exception
    {
        reusableList.clear();

        GeographicMapCellPosition topLeftGeographicMapCellPosition =
            geographicMapInterface.getCellPositionAtNoThrow(
            layerInterface.getXP(), layerInterface.getYP());

        GeographicMapCellPosition topRightGeographicMapCellPosition =
            geographicMapInterface.getCellPositionAtNoThrow(
            layerInterface.getXP() + layerInterface.getWidth(),
            layerInterface.getYP());

        GeographicMapCellPosition bottomLeftGeographicMapCellPosition =
            geographicMapInterface.getCellPositionAtNoThrow(
            layerInterface.getXP(),
            layerInterface.getYP() + layerInterface.getHeight());

        GeographicMapCellPosition bottomRightGeographicMapCellPosition =
            geographicMapInterface.getCellPositionAtNoThrow(
            layerInterface.getXP() + layerInterface.getWidth(),
            layerInterface.getYP() + layerInterface.getHeight());

        if(topLeftGeographicMapCellPosition != SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION)
        {
            reusableList.add(topLeftGeographicMapCellPosition);
        }

        if(topRightGeographicMapCellPosition != SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION &&
            topLeftGeographicMapCellPosition != topRightGeographicMapCellPosition)
        {
            reusableList.add(topRightGeographicMapCellPosition);
        }

        if(bottomLeftGeographicMapCellPosition != SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION &&
            !reusableList.contains(bottomLeftGeographicMapCellPosition))
        {
            reusableList.add(bottomLeftGeographicMapCellPosition);
        }

        if(bottomRightGeographicMapCellPosition != SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION &&
            !reusableList.contains(bottomRightGeographicMapCellPosition))
        {
            reusableList.add(bottomRightGeographicMapCellPosition);
        }

        return reusableList;

        //Finish implementation when layers become bigger than cells
        //return LayerPartialCellPositionsUtil.getAll(
            //geographicMapInterface,
            //topRightGeographicMapCellPosition,
            //layerInterface, reusableList);
    }

    public final BasicArrayList getAll(
            BasicGeographicMap geographicMapInterface,
            AllBinaryLayer layerInterface,
        int dx, int dy,
        BasicArrayList reusableList)
        throws Exception
    {
        reusableList.clear();

        GeographicMapCellPosition topLeftGeographicMapCellPosition =
            geographicMapInterface.getCellPositionAtNoThrow(
            layerInterface.getXP() + dx , layerInterface.getYP() + dy);

        GeographicMapCellPosition topRightGeographicMapCellPosition =
            geographicMapInterface.getCellPositionAtNoThrow(
            layerInterface.getXP() + layerInterface.getWidth() + dx,
            layerInterface.getYP() + dy);

        GeographicMapCellPosition bottomLeftGeographicMapCellPosition =
            geographicMapInterface.getCellPositionAtNoThrow(
            layerInterface.getXP() + dx,
            layerInterface.getYP() + layerInterface.getHeight() + dy);

        GeographicMapCellPosition bottomRightGeographicMapCellPosition =
            geographicMapInterface.getCellPositionAtNoThrow(
            layerInterface.getXP() + layerInterface.getWidth() + dx,
            layerInterface.getYP() + layerInterface.getHeight() + dy);

        //reusableList.add(topLeftGeographicMapCellPosition);

        if(topLeftGeographicMapCellPosition != SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION)
        {
            reusableList.add(topLeftGeographicMapCellPosition);
        }

        if(topRightGeographicMapCellPosition != SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION &&
            topLeftGeographicMapCellPosition != topRightGeographicMapCellPosition)
        {
            reusableList.add(topRightGeographicMapCellPosition);
        }

        if(bottomLeftGeographicMapCellPosition != SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION &&
            !reusableList.contains(bottomLeftGeographicMapCellPosition))
        {
            reusableList.add(bottomLeftGeographicMapCellPosition);
        }

        if(bottomRightGeographicMapCellPosition != SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION &&
            !reusableList.contains(bottomRightGeographicMapCellPosition))
        {
            reusableList.add(bottomRightGeographicMapCellPosition);
        }

        return reusableList;

        /*
        //Finish implementation when layers become bigger than cells
        return LayerPartialCellPositionsUtil.getAll(
            geographicMapInterface,
            topRightGeographicMapCellPosition,
            layerInterface, reusableList);
        */
    }

    public static LayerPartialCellPositionsUtil getInstance()
    {
        return instance;
    }

    /*
    public static final BasicArrayList getAll(
        BasicGeographicMap geographicMapInterface,
        GeographicMapCellPosition topRightGeographicMapCellPosition,
        LayerInterface layerInterface,
        BasicArrayList reusableList)
        throws Exception
    {
        reusableList.clear();

        GeographicMapCellPositionFactory geographicMapCellPositionFactory =
            geographicMapInterface.getGeographicMapCellPositionFactory();

        int columns = layerInterface.getWidth() /
            geographicMapInterface.getAllBinaryTiledLayer().getCellWidth();

        int rows = layerInterface.getHeight() /
            geographicMapInterface.getAllBinaryTiledLayer().getCellHeight();

        if(columns == 0)
            columns = 1;
        if(rows == 0)
            rows = 1;

        return CellPositionsUtil.getAll(
            geographicMapInterface,
            topRightGeographicMapCellPosition,
            columns, rows, reusableList);
    }
     */
}
