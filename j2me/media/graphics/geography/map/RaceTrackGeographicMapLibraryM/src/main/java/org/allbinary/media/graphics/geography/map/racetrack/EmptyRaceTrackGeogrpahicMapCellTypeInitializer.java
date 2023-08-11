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
package org.allbinary.media.graphics.geography.map.racetrack;

import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingNodeCostInfoData;

public class EmptyRaceTrackGeogrpahicMapCellTypeInitializer
{
    public static void init()
    {
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        final RaceTrackGeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = 
            RaceTrackGeographicMapCellTypeFactory.getInstance();
        
        final GeographicMapCellTypeFactory geographicMapCellTypeFactory = 
            GeographicMapCellTypeFactory.getInstance();
        
        raceTrackGeographicMapCellTypeFactory.EMPTY_CELL_TYPE = 
          new RaceTrackGeographicMapCellType(
          smallIntegerSingletonFactory.getInstance(0),
          PathFindingNodeCostInfoData.MAX_NODE_COST);

        raceTrackGeographicMapCellTypeFactory.EASY_CELL_TYPE =
          new RaceTrackGeographicMapCellType(
          smallIntegerSingletonFactory.getInstance(19),
          1);

        raceTrackGeographicMapCellTypeFactory.FINISH_LINE_ROAD_CELL_TYPE = 
            raceTrackGeographicMapCellTypeFactory.DEFAULT_FINISH_LINE_ROAD_CELL_TYPE;
    }
}
