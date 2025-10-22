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
package org.allbinary.media.graphics.geography.pathfinding;

import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author user
 */
public class PathGenerator
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final PathGenerator SINGLETON = new PathGenerator();

    public static PathGenerator getInstance()
    {
        return SINGLETON;
    }

    private PathGenerator()
    {
    }

    public void init(
        final Object geographicMapInterface,
        final int totalPaths)
        throws Exception
    {
    }
    
    public BasicArrayList getInstanceNoCache(
        final BasicGeographicMap geographicMapInterface,
        //final GeographicMapCellHistory geographicMapCellHistory,
        final PathFindingInfo pathFindingInfo,
        final int totalPaths)
        throws Exception
    {
        //Integer mapIdInteger = geographicMapInterface.getAllBinaryTiledLayer().getDataId();

        final BasicArrayList geographicMapCellPositionBasicArrayList =
            this.create(
            geographicMapInterface,
            pathFindingInfo,
            totalPaths);

        return geographicMapCellPositionBasicArrayList;
    }

    public BasicArrayList getInstance(
        final BasicGeographicMap geographicMapInterface,
        final GeographicMapCellHistory geographicMapCellHistory,
        final PathFindingInfo pathFindingInfo,
        final int totalPaths)
        throws Exception
    {
        throw new RuntimeException();
    }

    private BasicArrayList create(
        final BasicGeographicMap geographicMapInterface,
        final PathFindingInfo pathFindingInfo,
        final int totalPaths)
        throws Exception
    {
        final BasicArrayList startPathFindingNodeList = pathFindingInfo.getStartPathFindingNodeList();
        final BasicArrayList endPathFindingNodeList = pathFindingInfo.getEndPathFindingNodeList();

        final GeographicPathFinderBase geographicPathFinderInterface =
            pathFindingInfo.getPathFinder();

        final BasicArrayList geographicMapCellPositionBasicArrayList =
            geographicPathFinderInterface.search(startPathFindingNodeList, endPathFindingNodeList, totalPaths);

        return geographicMapCellPositionBasicArrayList;
    }

    public BasicArrayList createN(
        final BasicGeographicMap geographicMapInterface,
        final PathFindingInfo pathFindingInfo,
        final int totalPaths, final MultipassState multipassState)
        throws Exception
    {
        //int (((tiledLayer.getRows() + tiledLayer.getColumns()) * 3) >> 2); //maxPathSize

        //PreLogUtil.put(commonStrings.START, this, commonStrings.CREATE);
        //PreLogUtil.put(Memory.getInfo(), this, "create0");
        //timeDelayHelper.setStartTime();

        final BasicArrayList startPathFindingNodeList = pathFindingInfo.getStartPathFindingNodeList();
        final BasicArrayList endPathFindingNodeList = pathFindingInfo.getEndPathFindingNodeList();

        final GeographicPathFinderBase geographicPathFinderInterface =
            pathFindingInfo.getPathFinder();

        final BasicArrayList geographicMapCellPositionBasicArrayList =
            geographicPathFinderInterface.searchN(startPathFindingNodeList, endPathFindingNodeList, totalPaths, multipassState);
        //, totalPaths, minSize);

//        if(geographicMapCellPositionBasicArrayList != null) {
//
//        }

        return geographicMapCellPositionBasicArrayList;
    }
    
    //returns true if - vector contain all of the cells in the path
    private boolean isValid(
        final BasicArrayList pathList,
        final GeographicMapCellHistory geographicMapCellHistory)
        throws Exception
    {
        throw new RuntimeException();
    }
}
