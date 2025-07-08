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

public class PathFindingNodeCostInfoFactory
    extends PathFindingNodeCostInfoFactoryBase {
    //protected final LogUtil logUtil = LogUtil.getInstance();


    private final PathFindingNodeCostInfo[][] pathFindingNodeCostInfoAdjacencyList;

    public PathFindingNodeCostInfoFactory(int max) {
        this.pathFindingNodeCostInfoAdjacencyList = new PathFindingNodeCostInfo[max][max];
    }

    public void create(        
        final BasicGeographicMap geographicMapInterface,
        final GeographicMapCellPosition goingToGeographicMapCellPosition,
        //final GeographicMapCellPosition startGeographicMapCellPosition,
        //final GeographicMapCellPosition endGeographicMapCellPosition,
        final GeographicMapCellPosition geographicMapCellPosition,
        final long costFromStart,
        final long costToEnd)
        throws Exception {
        this.getInstance(
            goingToGeographicMapCellPosition,
            geographicMapCellPosition,
            costFromStart, costToEnd);
    }

    public PathFindingNodeCostInfo getInstance(
        final GeographicMapCellPosition goingToGeographicMapCellPosition,
        //final GeographicMapCellPosition startGeographicMapCellPosition,
        //GeographicMapCellPosition endGeographicMapCellPosition,
        final GeographicMapCellPosition geographicMapCellPosition,
        final long costFromStart,
        final long costToEnd)
        throws Exception {
        PathFindingNodeCostInfo pathFindingNodeCostInfo = 
            this.getInstance(goingToGeographicMapCellPosition, geographicMapCellPosition);

        if (pathFindingNodeCostInfo == null) {
            //PathFindingNodeCostInfo pathFindingNodeCostInfo =
            pathFindingNodeCostInfo = new PathFindingNodeCostInfo(costFromStart, costToEnd);

            pathFindingNodeCostInfo.setTotalCost();

            this.pathFindingNodeCostInfoAdjacencyList[geographicMapCellPosition.getId()][goingToGeographicMapCellPosition.getId()] = pathFindingNodeCostInfo;
        } else {
            pathFindingNodeCostInfo.setCostFromStart(costFromStart);
            pathFindingNodeCostInfo.setCostToEnd(costToEnd);
            pathFindingNodeCostInfo.setTotalCost();

            //throw new Exception("This is for creating all PathFindingNodeCostInfo");
        }

        return pathFindingNodeCostInfo;
    }

    public PathFindingNodeCostInfo getInstance(
        final GeographicMapCellPosition goingToFromGeographicMapCellPosition,
        final GeographicMapCellPosition geographicMapCellPosition) {

        //PathFindingNodeCostInfo pathFindingNodeCostInfo =
        //  this.pathFindingNodeCostInfoAdjacencyList
        //   [geographicMapCellPosition.getId().intValue()]
        // [goingToFromGeographicMapCellPosition.getId().intValue()];
        //if(pathFindingNodeCostInfo == null)
        //{
        // logUtil.put("Unable to get PathFindingNodeCostInfo: " + pathFindingNodeCostInfoKey, this, commonStrings.GET_INSTANCE);
        //}
        //return pathFindingNodeCostInfo;
        return this.pathFindingNodeCostInfoAdjacencyList[geographicMapCellPosition.getId()][goingToFromGeographicMapCellPosition.getId()];
    }

    public long getTotalCost(
        final BasicGeographicMap geographicMapInterface,
        final GeographicMapCellPosition comingFromGeographicMapCellPosition,
        final GeographicMapCellPosition geographicMapCellPosition) {
        return this.getInstance(comingFromGeographicMapCellPosition, geographicMapCellPosition).totalCost;
    }
}
