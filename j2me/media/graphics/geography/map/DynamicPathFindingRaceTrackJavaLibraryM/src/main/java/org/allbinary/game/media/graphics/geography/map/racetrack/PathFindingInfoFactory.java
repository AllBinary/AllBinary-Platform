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
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapUtil;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;
import org.allbinary.media.graphics.geography.map.racetrack.BasePathFindingInfoFactory;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapCellType;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackRoadsGeographicMapCellHistoryFactory;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingNodeCostInfoFactoryBase;
import org.allbinary.media.graphics.geography.pathfinding.BasicGeographicMapGraph;
import org.allbinary.media.graphics.geography.pathfinding.BasicGeographicMapPathFinder;
import org.allbinary.media.graphics.geography.pathfinding.PathFinderGraphVisitorFactoryBase;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingInfo;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingNodeCostInfoFactory;
import org.allbinary.util.BasicArrayListS;

/**
 *
 * @author user
 */
public class PathFindingInfoFactory extends BasePathFindingInfoFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static PathFindingInfoFactory instance;
    
    /**
     * @return the instance
     */
    public static PathFindingInfoFactory getInstance()
    {
        return PathFindingInfoFactory.instance;
    }

    public static void initMax(int max)
    {
        PathFindingInfoFactory.instance = new PathFindingInfoFactory(
            new SimplePathFinderGraphVisitorFactory(1, 1, Integer.MAX_VALUE), max);
    }
    
    private final int MAX_DIRECTIONS = 4;

    private final PathFindingInfo pathFindingInfo;
    private final BasicGeographicMapGraph basicGeographicMapGraph;
    private final BasicGeographicMapPathFinder basicGeographicMapPathFinder;

    private final PathFinderGraphVisitorFactoryBase
        pathFinderGraphVisitorFactoryInterface;

    private PathFindingInfoFactory(
        final PathFinderGraphVisitorFactoryBase pathFinderGraphVisitorFactoryInterface, final int max)
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        PreLogUtil.put("Using Dynamic Path Finding", this, commonStrings.GET_INSTANCE);

        this.basicGeographicMapPathFinder =
            new BasicGeographicMapPathFinder(max);

        this.pathFindingInfo = new PathFindingInfo(new PathFindingNodeCostInfoFactory(max), new BasicArrayListS(1), new BasicArrayListS(1));
        
        this.basicGeographicMapGraph =
            new BasicGeographicMapGraph(
            (PathFindingNodeCostInfoFactory)
            this.pathFindingInfo.getPathFindingNodeCostInfoFactoryInterface());
        
        this.pathFinderGraphVisitorFactoryInterface = pathFinderGraphVisitorFactoryInterface;
    }

    @Override
    public PathFindingInfo getInstancePathFindingInfo(
        final BasicGeographicMap geographicMapInterface,
        final int[][] mapArray)
        throws Exception
    {
        //TWB - find a way to cache this PathFindingInfo.
        final PathFindingInfo pathFindingInfo = new PathFindingInfo(this.pathFindingInfo.getPathFindingNodeCostInfoFactoryInterface(), new BasicArrayListS(1), new BasicArrayListS(1));

        RaceTrackRoadsGeographicMapCellHistoryFactory.getInstance().init();
        
        this.init(geographicMapInterface, pathFindingInfo, mapArray);

        this.basicGeographicMapPathFinder.init(
            geographicMapInterface, this.basicGeographicMapGraph, 
            this.pathFinderGraphVisitorFactoryInterface.getInstance(
                    geographicMapInterface));

        pathFindingInfo.setPathFinder(this.basicGeographicMapPathFinder);

        return pathFindingInfo;
    }

    // Set Appropriate Terrain and Map
    public void init(
        final BasicGeographicMap geographicMapInterface,
        final PathFindingInfo pathFindingInfo,
        final int[][] mapArray)
        throws Exception
    {

        super.init(geographicMapInterface, pathFindingInfo, mapArray);

        //AllBinaryTiledLayer allBinaryTiledLayer =
          //  geographicMapInterface.getAllBinaryTiledLayer();

        //int width = allBinaryTiledLayer.getWidth();

        //this.logUtil.putF(CommonLabels + allBinaryTiledLayer.getHeight() + commonStrings.SPACE + CommonLabels + width, this, this.commonStrings.INIT);

        this.buildPathFindingNodes(
            geographicMapInterface,
            pathFindingInfo,
            mapArray);

        // String keys =
        // this.getPathFinder().getPathFindingNodeCostInfoFactoryInterface().getHashTable().toString();
        // this.logUtil.putF("All PathFindingNodeCostInfos in Hashtable: " +
        // keys, this, this.commonStrings.INIT);
    }

    private void buildPathFindingNodesForCellPosition(
        final BasicGeographicMap geographicMapInterface,
        final PathFindingInfo pathFindingInfo,
        final int[][] mapArray,
        final GeographicMapCellPosition cellPosition)
       throws Exception
    {
        final BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
            geographicMapInterface.getGeographicMapCellPositionFactory();

        final int cellTypeId = mapArray[cellPosition.getRow()][cellPosition.getColumn()];

        final GeographicMapCellType geographicMapCellType =
            GeographicMapCellTypeFactory.getInstance().get(
           geographicMapInterface.getCellTypeFromMapCellTypeInt(cellTypeId));

        final RaceTrackGeographicMapCellType raceTrackGeographicMapCellType =
           (RaceTrackGeographicMapCellType) geographicMapCellType;

        final AllBinaryTiledLayer allBinaryTiledLayer =
            geographicMapInterface.getAllBinaryTiledLayer();

        final PathFindingNodeCostInfoFactoryBase pathFindingNodeCostInfoFactoryInterface =
           pathFindingInfo.getPathFindingNodeCostInfoFactoryInterface();
        //if (raceGameGeographicMapCellType != RaceTrackGeographicMapCellType.FINISH_LINE_ROAD_CELL_TYPE)
        //{
        
        final BasicGeographicMapUtil basicGeographicMapUtil = BasicGeographicMapUtil.getInstance();
        
        int row;
        int column;
        GeographicMapCellPosition goingToGeographicMapCellPosition;
        int costFromStart;
        int costToEnd;
        for (int index = 0; index < this.MAX_DIRECTIONS; index++)
        {
            row = basicGeographicMapUtil.getBorderingRow(index, cellPosition);
            column = basicGeographicMapUtil.getBorderingColumn(index, cellPosition);

            // If beyond border
            if (row < allBinaryTiledLayer.getRows() && 
                column < allBinaryTiledLayer.getColumns() && 
                row >= 0 && column >= 0)
            {
                goingToGeographicMapCellPosition =
                   geographicMapCellPositionFactory.getAt(column, row);

                 //RaceTrackGeographicMapCellType
                 //nextBorderingRaceGameGeographicMapCellType =
                 //RaceTrackGeographicMapCellType.getInstance(
                 //this.mapTwoDArray[goingToGeographicMapCellPosition.getRow()]
                 //[goingToGeographicMapCellPosition.getColumn()]);

                costFromStart = raceTrackGeographicMapCellType.getTravelCost();
                
     //+
                // RaceGameGeographicMapCellType.getTravelCostBetween(index,
                // raceGameGeographicMapCellType,
                // nextBorderingRaceGameGeographicMapCellType);
                
                costToEnd = costFromStart;


                 // int toEndManhattanDistance =
                 // PathFinder.manhattanDistance( geographicMapCellPosition,
                 // endGeographicMapCellPosition);

                 // int toStartManhattanDistance =
                 // PathFinder.manhattanDistance( geographicMapCellPosition,
                 // startGeographicMapCellPosition);

                 // pathFindingNodeCostInfo.setCostFromStart(toEndManhattanDistance +
                 // extraCost);
                 // pathFindingNodeCostInfo.setCostToEnd(toStartManhattanDistance +
                 // extraCost);

                pathFindingNodeCostInfoFactoryInterface.create(
                    geographicMapInterface,
                   goingToGeographicMapCellPosition,
                   //this.pathFindingInfo.getStartPathFindingNode()
                   //      .getGeographicMapCellPosition(),
                   //this.pathFindingInfo.getEndPathFindingNode()
                   //      .getGeographicMapCellPosition(),
                   cellPosition, costFromStart, costToEnd);
            }
        }
        //}

    }

    private void buildPathFindingNodes(
        final BasicGeographicMap geographicMapInterface,
        final PathFindingInfo pathFindingInfo,
        final int[][] mapArray)
        throws Exception
    {
        //this.logUtil.putF(CommonSeps.getInstance().START, this, "buildPathFindingNode");

        final BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
            geographicMapInterface.getGeographicMapCellPositionFactory();

        final AllBinaryTiledLayer allBinaryTiledLayer =
            geographicMapInterface.getAllBinaryTiledLayer();

        final int totalColumns = allBinaryTiledLayer.getColumns();
        final int totalRows = allBinaryTiledLayer.getRows();

        for (int column = 0; column < totalColumns; column++)
        {
            for (int row = 0; row < totalRows; row++)
            {
                this.buildPathFindingNodesForCellPosition(
                    geographicMapInterface, pathFindingInfo, mapArray, 
                    geographicMapCellPositionFactory.getAt(column, row));
            }
        }
    }
}
