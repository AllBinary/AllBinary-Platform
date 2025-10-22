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

import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;

public class RaceTrackGeographicMapCellTypeFactory extends GeographicMapCellTypeFactory
{
    private static final RaceTrackGeographicMapCellTypeFactory instance = new RaceTrackGeographicMapCellTypeFactory();

    public static RaceTrackGeographicMapCellTypeFactory getInstance()
    {
        return instance;
    }

    public GeographicMapCellType EMPTY_CELL_TYPE = GeographicMapCellType.NULL_GEOGRAPHIC_MAP_CELL_TYPE;
    public GeographicMapCellType EASY_CELL_TYPE = GeographicMapCellType.NULL_GEOGRAPHIC_MAP_CELL_TYPE;
    //new GeographicMapCellType(SmallIntegerSingletonFactory.getInstance(0));
    
    public final RaceTrackGeographicMapCellType BOTTOM_LEFT_TURN_ROAD_CELL_TYPE;
    public final RaceTrackGeographicMapCellType BOTTOM_RIGHT_TURN_ROAD_CELL_TYPE;
    public final RaceTrackGeographicMapCellType TOP_LEFT_TURN_ROAD_CELL_TYPE;
    public final RaceTrackGeographicMapCellType TOP_RIGHT_TURN_ROAD_CELL_TYPE;
    public final RaceTrackGeographicMapCellType HORIZONTAL_STRAIGHT_ROAD_CELL_TYPE;
    public final RaceTrackGeographicMapCellType VERTICAL_STRAIGHT_ROAD_CELL_TYPE;

    public final RaceTrackGeographicMapCellType START_LINE_ROAD_CELL_TYPE;

    public final RaceTrackGeographicMapCellType DEFAULT_FINISH_LINE_ROAD_CELL_TYPE;

    public GeographicMapCellType FINISH_LINE_ROAD_CELL_TYPE = GeographicMapCellType.NULL_GEOGRAPHIC_MAP_CELL_TYPE;

    public RaceTrackGeographicMapCellTypeFactory()
    {
        final RaceTrackGeographicMapCellTypes raceTrackGeographicMapCellTypes = RaceTrackGeographicMapCellTypes.getInstance();
        BOTTOM_LEFT_TURN_ROAD_CELL_TYPE = raceTrackGeographicMapCellTypes.BOTTOM_LEFT_TURN_ROAD_CELL_TYPE;
        BOTTOM_RIGHT_TURN_ROAD_CELL_TYPE = raceTrackGeographicMapCellTypes.BOTTOM_RIGHT_TURN_ROAD_CELL_TYPE;
        TOP_LEFT_TURN_ROAD_CELL_TYPE = raceTrackGeographicMapCellTypes.TOP_LEFT_TURN_ROAD_CELL_TYPE;
        TOP_RIGHT_TURN_ROAD_CELL_TYPE = raceTrackGeographicMapCellTypes.TOP_RIGHT_TURN_ROAD_CELL_TYPE;
        HORIZONTAL_STRAIGHT_ROAD_CELL_TYPE = raceTrackGeographicMapCellTypes.HORIZONTAL_STRAIGHT_ROAD_CELL_TYPE;
        VERTICAL_STRAIGHT_ROAD_CELL_TYPE = raceTrackGeographicMapCellTypes.VERTICAL_STRAIGHT_ROAD_CELL_TYPE;

        START_LINE_ROAD_CELL_TYPE = raceTrackGeographicMapCellTypes.START_LINE_ROAD_CELL_TYPE;

        DEFAULT_FINISH_LINE_ROAD_CELL_TYPE = raceTrackGeographicMapCellTypes.DEFAULT_FINISH_LINE_ROAD_CELL_TYPE;
    }

    @Override
    public int getStartType() {
        return this.START_LINE_ROAD_CELL_TYPE.getType();
    }

    @Override
    public int getEndType() {
        return this.FINISH_LINE_ROAD_CELL_TYPE.getType();
    }

    @Override
    public int getEmptyType() {
        return this.EMPTY_CELL_TYPE.getType();
    }

    @Override
    public boolean isPath(final GeographicMapCellType cellType)
    {
        // if(i_TileType == ROAD_CELL_TYPE_1 ||
        // i_TileType == FINISH_LINE_ROAD_CELL_TYPE)
        if (cellType != this.EMPTY_CELL_TYPE && cellType != this.EASY_CELL_TYPE)
        {
            return true;
        }
        return false;
    }
}
