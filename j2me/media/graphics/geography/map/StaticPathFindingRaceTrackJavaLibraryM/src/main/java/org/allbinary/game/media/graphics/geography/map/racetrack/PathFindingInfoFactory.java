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
import org.allbinary.media.graphics.geography.pathfinding.PathFindingNodeCostInfoFactoryBase;
import org.allbinary.util.BasicArrayListS;

/**
 *
 * @author user
 */
public class PathFindingInfoFactory extends BasePathFindingInfoFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final PathFindingInfoFactory instance =
        new PathFindingInfoFactory();

    /**
     * @return the instance
     */
    public static PathFindingInfoFactory getInstance()
    {
        return PathFindingInfoFactory.instance;
    }

    private PathFindingInfoFactory()
    {

    }

    @Override
    public PathFindingInfo getInstancePathFindingInfo(
            final BasicGeographicMap geographicMapInterface,
            final int[][] graphArray)
        throws Exception
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        PreLogUtil.put(commonStrings.START, this, commonStrings.GET_INSTANCE);

        final PathFindingInfo pathFindingInfo = new PathFindingInfo(
            new PathFindingNodeCostInfoFactoryBase(), 
            new BasicArrayListS(1), new BasicArrayListS(1));

        RaceTrackRoadsGeographicMapCellHistoryFactory.getInstance().init();
        
        this.init(geographicMapInterface, pathFindingInfo, graphArray);

        return pathFindingInfo;
    }
}
