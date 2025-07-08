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

import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.racetrack.BasePathFindingInfoFactory;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackRoadsGeographicMapCellHistoryFactory;
import org.allbinary.media.graphics.geography.pathfinding.PathFinder;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingInfo;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingNodeCostInfoFactory;

/**
 *
 * @author user
 */
//1.4.2
public class PathFindingInfoFactory extends BasePathFindingInfoFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final PathFindingInfoFactory instance = new PathFindingInfoFactory();
    
    /**
     * @return the instance
     */
    public static PathFindingInfoFactory getInstance()
    {
        return instance;
    }

    private static int MAX = 32768;
    public static void init(int max)
    {
        PathFindingInfoFactory.MAX = max;
    }
    
    //private final int MAX_DIRECTIONS = 4;

    private final PathFindingInfo pathFindingInfo;
    private final PathFinder pathFinder;
    
    private PathFindingInfoFactory()
    {
        //PreLogUtil.put("Using Dynamic Path Finding", this, commonStrings.GET_INSTANCE);
        
        this.pathFindingInfo = new PathFindingInfo(new PathFindingNodeCostInfoFactory(MAX));
        
        this.pathFinder = new PathFinder();
    }

    public PathFindingInfo getInstance(
        final BasicGeographicMap geographicMapInterface,
        final int[][] mapArray)
        throws Exception
    {
        //TWB - find a way to cache this PathFindingInfo.
        final PathFindingInfo pathFindingInfo = new PathFindingInfo(this.pathFindingInfo.getPathFindingNodeCostInfoFactoryInterface());

        RaceTrackRoadsGeographicMapCellHistoryFactory.getInstance().init();
        
        this.init(geographicMapInterface, pathFindingInfo, mapArray);

        this.pathFinder.init(geographicMapInterface);

        pathFindingInfo.setPathFinder(pathFinder);

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

        //logUtil.put(SpacialStrings + allBinaryTiledLayer.getHeight() + commonStrings.SPACE + SpacialStrings + width, this, commonStrings.INIT);

        this.buildPathFindingNodes(
            geographicMapInterface,
            pathFindingInfo,
            mapArray);

        // String keys =
        // this.getPathFinder().getPathFindingNodeCostInfoFactoryInterface().getHashTable().toString();
        // logUtil.put("All PathFindingNodeCostInfos in Hashtable: " +
        // keys, this, commonStrings.INIT);
    }

    private void buildPathFindingNodes(
        final BasicGeographicMap geographicMapInterface,
        final PathFindingInfo pathFindingInfo,
        final int[][] mapArray,
        final GeographicMapCellPosition cellPosition)
       throws Exception
    {
//       final BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
//            geographicMapInterface.getGeographicMapCellPositionFactory();
//
//        final int cellTypeId = mapArray[cellPosition.getRow()][cellPosition.getColumn()];
//
//        final GeographicMapCellType geographicMapCellType =
//            GeographicMapCellTypeFactory.getInstance().getInstance(
//           geographicMapInterface.getCellTypeFromMapCellTypeInt(cellTypeId));
//
//        final RaceTrackGeographicMapCellType raceTrackGeographicMapCellType =
//           (RaceTrackGeographicMapCellType) geographicMapCellType;
//
//        final AllBinaryTiledLayer allBinaryTiledLayer =
//            geographicMapInterface.getAllBinaryTiledLayer();
//
//        final PathFindingNodeCostInfoFactoryBase pathFindingNodeCostInfoFactoryInterface =
//           pathFindingInfo.getPathFindingNodeCostInfoFactoryInterface();
//        //if (raceGameGeographicMapCellType != RaceTrackGeographicMapCellType.FINISH_LINE_ROAD_CELL_TYPE)
//        //{
//        
//        final BasicGeographicMapUtil basicGeographicMapUtil = BasicGeographicMapUtil.getInstance();
//        
//        int row;
//        int column;
//        GeographicMapCellPosition goingToGeographicMapCellPosition;
//        int costFromStart;
//        int costToEnd;
//        int discoveryCalculation;
//        for (int index = 0; index < MAX_DIRECTIONS; index++)
//        {
//            row = basicGeographicMapUtil.getBorderingRow(index, cellPosition);
//            column = basicGeographicMapUtil.getBorderingColumn(index, cellPosition);
//
//            // If beyond border
//            if (row < allBinaryTiledLayer.getRows() && 
//                column < allBinaryTiledLayer.getColumns() && 
//                row >= 0 && column >= 0)
//            {
//                goingToGeographicMapCellPosition =
//                   geographicMapCellPositionFactory.getInstance(column, row);
//
//                 //RaceTrackGeographicMapCellType
//                 //nextBorderingRaceGameGeographicMapCellType =
//                 //RaceTrackGeographicMapCellType.getInstance(
//                 //this.mapTwoDArray[goingToGeographicMapCellPosition.getRow()]
//                 //[goingToGeographicMapCellPosition.getColumn()]);
//
//                costFromStart = raceTrackGeographicMapCellType.getTravelCost();
//                
//                PathFindingNode targetPathFindingNode = ((PathFindingNode) pathFindingInfo.getEndPathFindingNodeList().get(0));
//                discoveryCalculation = Math.abs(column - targetPathFindingNode.geographicMapCellPosition.getColumn()) + Math.abs(row - targetPathFindingNode.geographicMapCellPosition.getRow());
//                
//     //+
//                // RaceGameGeographicMapCellType.getTravelCostBetween(index,
//                // raceGameGeographicMapCellType,
//                // nextBorderingRaceGameGeographicMapCellType);
//                
//                costToEnd = costFromStart;
//
//
//                 // int toEndManhattanDistance =
//                 // PathFinder.manhattanDistance( geographicMapCellPosition,
//                 // endGeographicMapCellPosition);
//
//                 // int toStartManhattanDistance =
//                 // PathFinder.manhattanDistance( geographicMapCellPosition,
//                 // startGeographicMapCellPosition);
//
//                 // pathFindingNodeCostInfo.setCostFromStart(toEndManhattanDistance +
//                 // extraCost);
//                 // pathFindingNodeCostInfo.setCostToEnd(toStartManhattanDistance +
//                 // extraCost);
//
//                pathFindingNodeCostInfoFactoryInterface.create(
//                    geographicMapInterface,
//                   goingToGeographicMapCellPosition,
//                   //this.pathFindingInfo.getStartPathFindingNode()
//                   //      .getGeographicMapCellPosition(),
//                   //this.pathFindingInfo.getEndPathFindingNode()
//                   //      .getGeographicMapCellPosition(),
//                   cellPosition, costFromStart, costToEnd, costFromStart, discoveryCalculation);
//            }
//        }
        //}
    }

    private void buildPathFindingNodes(
        final BasicGeographicMap geographicMapInterface,
        final PathFindingInfo pathFindingInfo,
        final int[][] mapArray)
        throws Exception
    {
       //logUtil.put(CommonSeps.getInstance().START, this, "buildPathFindingNode");

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
                this.buildPathFindingNodes(
                    geographicMapInterface, pathFindingInfo, mapArray, 
                    geographicMapCellPositionFactory.getInstance(column, row));
            }
        }
    }
}
