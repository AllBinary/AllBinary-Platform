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
package org.allbinary.media.graphics.geography.map.racetrack;

import org.allbinary.game.layer.AdvancedRTSGameLayer;
import org.allbinary.game.layer.waypoint.WaypointLayer;
import org.allbinary.graphics.CellPosition;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;
import org.allbinary.media.graphics.geography.map.drop.DropCellPositionHistory;

public class CustomMapGenerator extends CustomMapGeneratorBase
{

    private final int[][] customMapArray;
    private final RaceTrackGeographicMap raceTrackGeographicMap;
    private final GeographicMapCellTypeFactory geographicMapCellTypeFactory;
    
    public CustomMapGenerator(final RaceTrackGeographicMap raceTrackGeographicMap)
            throws Exception
    {
        this.raceTrackGeographicMap = raceTrackGeographicMap;
        this.geographicMapCellTypeFactory = this.raceTrackGeographicMap.getGeographicMapCellTypeFactory();
        
        final int[][] mapArray = raceTrackGeographicMap.getRaceTrackData().getMapArray();

        this.customMapArray = new int[mapArray.length][mapArray[0].length];

        if (mapArray.length != this.customMapArray.length
                || mapArray[0].length != this.customMapArray[0].length)
        {
            throw new Exception("Array Incorrect");
        }
    }

    @Override
    public void copyMapIntoCustomMap()
            throws Exception
    {
        // RaceTrackGeographicMap raceTrackGeographicMap =
        // (RaceTrackGeographicMap)
        // this.getOwnerLayer().geographicMapInterface;

        int[][] mapArray = raceTrackGeographicMap.getRaceTrackData().getMapArray();

        int startIndex2 = mapArray[0].length - 1;
        for (int index = mapArray.length - 1; index >= 0; index--)
        {
            for (int index2 = startIndex2; index2 >= 0; index2--)
            {
                this.customMapArray[index][index2] = this.getCustomType(
                        index2, index, mapArray[index][index2]);
            }
        }
    }

    private final DropCellPositionHistory dropCellPositionHistory = 
        DropCellPositionHistory.getInstance();
    
    @Override
    public int getCustomType(final int column, final int row, final int currentType)
        throws Exception
    {
        final int emptyType = geographicMapCellTypeFactory.getEmptyType();

        final BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
            this.raceTrackGeographicMap.getGeographicMapCellPositionFactory();
          //  this.getOwnerLayer().geographicMapInterface.getGeographicMapCellPositionFactory();

        final CellPosition cellPosition = geographicMapCellPositionFactory.getInstance(column, row);

        if (dropCellPositionHistory.isCellPositionWithDrop(cellPosition))
        {
            final AdvancedRTSGameLayer rtsLayer = (AdvancedRTSGameLayer) 
                dropCellPositionHistory.getLayerInterface(cellPosition);

            if (!(rtsLayer.getType() == WaypointLayer.getStaticType()))
            {
                return emptyType;
            }
        }

        return currentType;
    }

    @Override
    public int[][] getCustomMapArray()
    {
        return customMapArray;
    }
    
}
