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
package allbinary.media.graphics.geography.map.racetrack;

import allbinary.logic.math.SmallIntegerSingletonFactory;
import allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;
import allbinary.media.graphics.geography.pathfinding.PathFindingNodeCostInfoData;

public class RaceTrackGeogrpahicMapCellTypeInitializer
{
    public static void init()
    {
        final RaceTrackGeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = 
            RaceTrackGeographicMapCellTypeFactory.getInstance();
        
        final GeographicMapCellTypeFactory geographicMapCellTypeFactory = 
            GeographicMapCellTypeFactory.getInstance();                
        
        geographicMapCellTypeFactory.EMPTY_CELL_TYPE = 
          new RaceTrackGeographicMapCellType(
          SmallIntegerSingletonFactory.getInstance().getInstance(0), 
          PathFindingNodeCostInfoData.MAX_NODE_COST);

        geographicMapCellTypeFactory.EASY_CELL_TYPE = geographicMapCellTypeFactory.EMPTY_CELL_TYPE;

        raceTrackGeographicMapCellTypeFactory.FINISH_LINE_ROAD_CELL_TYPE = 
            raceTrackGeographicMapCellTypeFactory.DEFAULT_FINISH_LINE_ROAD_CELL_TYPE;
    }
}
