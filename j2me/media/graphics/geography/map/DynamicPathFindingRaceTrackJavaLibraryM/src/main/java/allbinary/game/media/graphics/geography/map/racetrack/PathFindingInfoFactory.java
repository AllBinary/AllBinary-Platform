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
package allbinary.game.media.graphics.geography.map.racetrack;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.game.layer.AllBinaryTiledLayer;
import allbinary.media.graphics.geography.map.BasicGeographicMap;
import allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import allbinary.media.graphics.geography.map.BasicGeographicMapUtil;
import allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import allbinary.media.graphics.geography.map.GeographicMapCellType;
import allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;
import allbinary.media.graphics.geography.map.racetrack.BasePathFindingInfoFactory;
import allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapCellType;
import allbinary.media.graphics.geography.map.racetrack.RaceTrackRoadsGeographicMapCellHistoryFactory;
import allbinary.media.graphics.geography.pathfinding.BasePathFindingNodeCostInfoFactoryInterface;
import allbinary.media.graphics.geography.pathfinding.BasicGeographicMapGraph;
import allbinary.media.graphics.geography.pathfinding.BasicGeographicMapPathFinder;
import allbinary.media.graphics.geography.pathfinding.PathFinderGraphVisitorFactoryInterface;
import allbinary.media.graphics.geography.pathfinding.PathFindingInfo;
import allbinary.media.graphics.geography.pathfinding.PathFindingNodeCostInfoFactory;

/**
 *
 * @author user
 */
public class PathFindingInfoFactory extends BasePathFindingInfoFactory
{
    private static PathFindingInfoFactory instance;
    
    /**
     * @return the instance
     */
    public static PathFindingInfoFactory getInstance()
    {
        return instance;
    }

    public static void init(int max)
    {
        instance =
        new PathFindingInfoFactory(
            new SimplePathFinderGraphVisitorFactory(1, 1, Integer.MAX_VALUE), max);
    }
    
    private final int MAX_DIRECTIONS = 4;

    private final PathFindingInfo pathFindingInfo;
    private final BasicGeographicMapGraph basicGeographicMapGraph;
    private final BasicGeographicMapPathFinder basicGeographicMapPathFinder;

    private final PathFinderGraphVisitorFactoryInterface
        pathFinderGraphVisitorFactoryInterface;

    private PathFindingInfoFactory(PathFinderGraphVisitorFactoryInterface
        pathFinderGraphVisitorFactoryInterface, int max)
    {
        PreLogUtil.put("Using Dynamic Path Finding", this, CommonStrings.getInstance().GET_INSTANCE);

        this.basicGeographicMapPathFinder =
            new BasicGeographicMapPathFinder(max);

        this.pathFindingInfo = 
            new PathFindingInfo(
                    new PathFindingNodeCostInfoFactory(max));
        
        this.basicGeographicMapGraph =
            new BasicGeographicMapGraph(
            (PathFindingNodeCostInfoFactory)
            pathFindingInfo.getPathFindingNodeCostInfoFactoryInterface());
        
        this.pathFinderGraphVisitorFactoryInterface = pathFinderGraphVisitorFactoryInterface;
    }

    public PathFindingInfo getInstance(
        final BasicGeographicMap geographicMapInterface,
        final int[][] mapArray)
        throws Exception
    {
        pathFindingInfo.init();

        RaceTrackRoadsGeographicMapCellHistoryFactory.getInstance().init();
        
        this.init(geographicMapInterface, pathFindingInfo, mapArray);

        this.basicGeographicMapPathFinder.init(
            geographicMapInterface, basicGeographicMapGraph, 
            pathFinderGraphVisitorFactoryInterface.getInstance(
                    geographicMapInterface));

        pathFindingInfo.setPathFinder(
            basicGeographicMapPathFinder);

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

        //LogUtil.put(LogFactory.getInstance(SpacialStrings + allBinaryTiledLayer.getHeight() + CommonStrings.getInstance().SPACE + SpacialStrings + width, this, CommonStrings.getInstance().INIT));

        this.buildPathFindingNodes(
            geographicMapInterface,
            pathFindingInfo,
            mapArray);

        // String keys =
        // this.getPathFinder().getPathFindingNodeCostInfoFactoryInterface().getHashTable().toString();
        // LogUtil.put(LogFactory.getInstance("All PathFindingNodeCostInfos in Hashtable: " +
        // keys, this, CommonStrings.getInstance().INIT));
    }

    private void buildPathFindingNodes(
        final BasicGeographicMap geographicMapInterface,
        final PathFindingInfo pathFindingInfo,
        final int[][] mapArray,
        final GeographicMapCellPosition cellPosition)
       throws Exception
    {
        BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
            geographicMapInterface.getGeographicMapCellPositionFactory();

        int cellTypeId = mapArray[cellPosition.getRow()][cellPosition.getColumn()];

        GeographicMapCellType geographicMapCellType =
            GeographicMapCellTypeFactory.getInstance().getInstance(
           geographicMapInterface.getCellTypeFromMapCellTypeInt(cellTypeId));

        RaceTrackGeographicMapCellType raceTrackGeographicMapCellType =
           (RaceTrackGeographicMapCellType) geographicMapCellType;

        AllBinaryTiledLayer allBinaryTiledLayer =
            geographicMapInterface.getAllBinaryTiledLayer();

        BasePathFindingNodeCostInfoFactoryInterface pathFindingNodeCostInfoFactoryInterface =
           pathFindingInfo.getPathFindingNodeCostInfoFactoryInterface();
        //if (raceGameGeographicMapCellType != RaceTrackGeographicMapCellType.FINISH_LINE_ROAD_CELL_TYPE)
        //{
        
        final BasicGeographicMapUtil basicGeographicMapUtil = BasicGeographicMapUtil.getInstance();
        
        for (int index = 0; index < MAX_DIRECTIONS; index++)
        {
            int row = basicGeographicMapUtil.getBorderingRow(index, cellPosition);
            int column = basicGeographicMapUtil.getBorderingColumn(index, cellPosition);

            // If beyond border
            if (row < allBinaryTiledLayer.getRows() && 
                column < allBinaryTiledLayer.getColumns() && 
                row >= 0 && column >= 0)
            {
                GeographicMapCellPosition goingToGeographicMapCellPosition =
                   geographicMapCellPositionFactory.getInstance(column, row);

                 //RaceTrackGeographicMapCellType
                 //nextBorderingRaceGameGeographicMapCellType =
                 //RaceTrackGeographicMapCellType.getInstance(
                 //this.i_Map2DArray[goingToGeographicMapCellPosition.getRow()]
                 //[goingToGeographicMapCellPosition.getColumn()]);

                int costFromStart = raceTrackGeographicMapCellType.getTravelCost();

     //+
                // RaceGameGeographicMapCellType.getTravelCostBetween(index,
                // raceGameGeographicMapCellType,
                // nextBorderingRaceGameGeographicMapCellType);
                
                int costToEnd = costFromStart;


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
        //LogUtil.put(LogFactory.getInstance(CommonSeps.getInstance().START, this, "buildPathFindingNode"));

        BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
            geographicMapInterface.getGeographicMapCellPositionFactory();

        AllBinaryTiledLayer allBinaryTiledLayer =
            geographicMapInterface.getAllBinaryTiledLayer();

        int totalColumns = allBinaryTiledLayer.getColumns();
        int totalRows = allBinaryTiledLayer.getRows();

        for (int column = 0; column < totalColumns; column++)
        {
            for (int row = 0; row < totalRows; row++)
            {
                this.buildPathFindingNodes(
                    geographicMapInterface, pathFindingInfo, mapArray, 
                    geographicMapCellPositionFactory.getInstance(column, row));
            }
        }
    }
}
