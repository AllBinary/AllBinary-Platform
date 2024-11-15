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

    public GeographicMapCellType EMPTY_CELL_TYPE;
    public GeographicMapCellType EASY_CELL_TYPE;
    //new GeographicMapCellType(SmallIntegerSingletonFactory.getInstance(0));
    
    public final RaceTrackGeographicMapCellType BOTTOM_LEFT_TURN_ROAD_CELL_TYPE;
    public final RaceTrackGeographicMapCellType BOTTOM_RIGHT_TURN_ROAD_CELL_TYPE;
    public final RaceTrackGeographicMapCellType TOP_LEFT_TURN_ROAD_CELL_TYPE;
    public final RaceTrackGeographicMapCellType TOP_RIGHT_TURN_ROAD_CELL_TYPE;
    public final RaceTrackGeographicMapCellType HORIZONTAL_STRAIGHT_ROAD_CELL_TYPE;
    public final RaceTrackGeographicMapCellType VERTICAL_STRAIGHT_ROAD_CELL_TYPE;

    public final RaceTrackGeographicMapCellType START_LINE_ROAD_CELL_TYPE;

    public final RaceTrackGeographicMapCellType DEFAULT_FINISH_LINE_ROAD_CELL_TYPE;

    public RaceTrackGeographicMapCellType FINISH_LINE_ROAD_CELL_TYPE;

    public RaceTrackGeographicMapCellTypeFactory()
    {
        BOTTOM_LEFT_TURN_ROAD_CELL_TYPE = new RaceTrackGeographicMapCellType("Bottom Left Turn",
                1, 1);
        BOTTOM_RIGHT_TURN_ROAD_CELL_TYPE = new RaceTrackGeographicMapCellType("Bottom Right Turn",
                2, 1);
        TOP_LEFT_TURN_ROAD_CELL_TYPE = new RaceTrackGeographicMapCellType("Top Left Turn",
                3, 1);
        TOP_RIGHT_TURN_ROAD_CELL_TYPE = new RaceTrackGeographicMapCellType("Top Right Turne",
                4, 1);
        HORIZONTAL_STRAIGHT_ROAD_CELL_TYPE = new RaceTrackGeographicMapCellType("Horizontal Straight",
                5, 1);
        VERTICAL_STRAIGHT_ROAD_CELL_TYPE = new RaceTrackGeographicMapCellType("Vertical Straight",
                6, 1);

        START_LINE_ROAD_CELL_TYPE = new RaceTrackGeographicMapCellType("Start Line",
                7, 1);

        DEFAULT_FINISH_LINE_ROAD_CELL_TYPE = new RaceTrackGeographicMapCellType("Finish Line",
                8, 1);
    }

    public int getStartType() {
        return this.START_LINE_ROAD_CELL_TYPE.getType();
    }

    public int getEndType() {
        return this.FINISH_LINE_ROAD_CELL_TYPE.getType();
    }
        
    public int getEmptyType() {
        return this.EMPTY_CELL_TYPE.getType();
    }

    public boolean isPath(final GeographicMapCellType cellType)
    {
        // if(i_TileType == ROAD_CELL_TYPE_1 ||
        // i_TileType == FINISH_LINE_ROAD_CELL_TYPE)
        if (cellType != this.EMPTY_CELL_TYPE
                && cellType != this.EASY_CELL_TYPE)
        {
            return true;
        }
        return false;
    }
}
