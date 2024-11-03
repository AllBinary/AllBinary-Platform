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

import org.allbinary.util.BasicArrayList;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingInfo;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingNode;

public class RaceTrackStartPointUtil
{
    public static GPoint get(
            final BasicGeographicMap geographicMapInterface, final PathFindingInfo pathFindingInfo, final int index)
    throws Exception
    {
        final BasicArrayList list = pathFindingInfo.getStartPathFindingNodeList();

        final PathFindingNode startPathFindingNode = (PathFindingNode) list.get(index);

        final GeographicMapCellPosition geographicMapCellPosition =
           startPathFindingNode.getGeographicMapCellPosition();

        final GPoint point = geographicMapCellPosition.getPoint();

        return PointFactory.getInstance().getInstance(
                point.getX(), 
                point.getY() + geographicMapInterface.getAllBinaryTiledLayer().getHalfCellHeight());
    }
    
}
