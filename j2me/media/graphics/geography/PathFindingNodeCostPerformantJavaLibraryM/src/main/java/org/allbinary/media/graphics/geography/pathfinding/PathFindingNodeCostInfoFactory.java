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
package org.allbinary.media.graphics.geography.pathfinding;

import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapCellType;

public class PathFindingNodeCostInfoFactory
    extends PathFindingNodeCostInfoFactoryBase {

    public PathFindingNodeCostInfoFactory(int max) {
    }

    @Override
    public void create(
        final BasicGeographicMap geographicMapInterface,
        final GeographicMapCellPosition goingToGeographicMapCellPosition,
        //GeographicMapCellPosition startGeographicMapCellPosition,
        //GeographicMapCellPosition endGeographicMapCellPosition,
        final GeographicMapCellPosition geographicMapCellPosition,
        final long costFromStart,
        final long costToEnd)
        throws Exception {

//        final GeographicMapCellType geographicMapCellType = geographicMapInterface.getCellTypeAt(goingToGeographicMapCellPosition);
//        final GeographicMapCellType geographicMapCellType2 = geographicMapInterface.getCellTypeAt(geographicMapCellPosition);
//
//        final RaceTrackGeographicMapCellType raceTrackGeographicMapCellType =
//           (RaceTrackGeographicMapCellType) geographicMapCellType;
//        final RaceTrackGeographicMapCellType raceTrackGeographicMapCellType2 =
//           (RaceTrackGeographicMapCellType) geographicMapCellType2;
//        
//        System.out.println(goingToGeographicMapCellPosition + " ctc: " + raceTrackGeographicMapCellType.getTravelCost());
//        System.out.println(goingToGeographicMapCellPosition + " cc: " + raceTrackGeographicMapCellType2.getTravelCost());
    }

    @Override
    public long getTotalCost(
        final BasicGeographicMap geographicMapInterface,
        final GeographicMapCellPosition comingFromGeographicMapCellPosition,
        final GeographicMapCellPosition geographicMapCellPosition) throws Exception {
        
        final GeographicMapCellType geographicMapCellType = geographicMapInterface.getCellTypeAt(comingFromGeographicMapCellPosition);
        final GeographicMapCellType geographicMapCellType2 = geographicMapInterface.getCellTypeAt(geographicMapCellPosition);

        final RaceTrackGeographicMapCellType raceTrackGeographicMapCellType =
           (RaceTrackGeographicMapCellType) geographicMapCellType;
        final RaceTrackGeographicMapCellType raceTrackGeographicMapCellType2 =
           (RaceTrackGeographicMapCellType) geographicMapCellType2;
        
//        System.out.println(comingFromGeographicMapCellPosition + " fc: " + raceTrackGeographicMapCellType.getTravelCost());
//        System.out.println(geographicMapCellPosition + " tc: " + raceTrackGeographicMapCellType2.getTravelCost());
        return raceTrackGeographicMapCellType.getTravelCost() + raceTrackGeographicMapCellType2.getTravelCost();
    }

}
