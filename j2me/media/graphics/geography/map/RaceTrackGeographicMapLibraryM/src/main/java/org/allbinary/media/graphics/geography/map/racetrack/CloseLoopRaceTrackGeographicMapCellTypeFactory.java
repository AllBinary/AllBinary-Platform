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
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingNodeCostInfoData;

public class CloseLoopRaceTrackGeographicMapCellTypeFactory extends RaceTrackGeographicMapCellTypeFactory
{
    private static final CloseLoopRaceTrackGeographicMapCellTypeFactory instance = new CloseLoopRaceTrackGeographicMapCellTypeFactory();

    /**
     * @return the instance
     */
    public static CloseLoopRaceTrackGeographicMapCellTypeFactory getInstance() {
        return instance;
    }
    
    private CloseLoopRaceTrackGeographicMapCellTypeFactory()
    {                
        this.EMPTY_CELL_TYPE = EmptyRaceRaceTrackGeographicMapCellTypeFactory.getInstance().EMPTY_CELL_TYPE;

        this.EASY_CELL_TYPE = this.EMPTY_CELL_TYPE;
       
        this.FINISH_LINE_ROAD_CELL_TYPE = this.START_LINE_ROAD_CELL_TYPE;
    }
}
