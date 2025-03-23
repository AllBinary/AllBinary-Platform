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
package org.allbinary.game.media.graphics.geography.map.racetrack;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.racetrack.BasePathFindingInfoFactory;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackRoadsGeographicMapCellHistoryFactory;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingInfo;

/**
 *
 * @author user
 */
public class PathFindingInfoFactory extends BasePathFindingInfoFactory
{
    private static final PathFindingInfoFactory instance =
        new PathFindingInfoFactory();

    /**
     * @return the instance
     */
    public static PathFindingInfoFactory getInstance()
    {
        return instance;
    }

    private PathFindingInfoFactory()
    {

    }

    public PathFindingInfo getInstance(
            final BasicGeographicMap geographicMapInterface,
            final int[][] graphArray)
        throws Exception
    {
        PreLogUtil.put(CommonStrings.getInstance().START, this, CommonStrings.getInstance().GET_INSTANCE);

        final PathFindingInfo pathFindingInfo = new PathFindingInfo(null);

        RaceTrackRoadsGeographicMapCellHistoryFactory.getInstance().init();
        
        this.init(geographicMapInterface, pathFindingInfo, graphArray);

        return pathFindingInfo;
    }
}
