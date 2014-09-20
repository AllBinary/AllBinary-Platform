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
            BasicGeographicMap geographicMapInterface, PathFindingInfo pathFindingInfo, int index)
    throws Exception
    {
        BasicArrayList list = pathFindingInfo.getStartPathFindingNodeList();

        PathFindingNode startPathFindingNode = (PathFindingNode) list.get(index);

        GeographicMapCellPosition geographicMapCellPosition =
           startPathFindingNode.getGeographicMapCellPosition();

        GPoint point = geographicMapCellPosition.getPoint();

        return PointFactory.getInstance().getInstance(
                point.getX(), 
                point.getY() + geographicMapInterface.getAllBinaryTiledLayer().getHalfCellHeight());
    }
    
}
