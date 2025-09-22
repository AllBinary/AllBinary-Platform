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


import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingInfo;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingNode;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPositionFactoryInitVisitorInterface;

/**
 *
 * @author user
 */
public class BasePathFindingInfoFactory {
    protected final LogUtil logUtil = LogUtil.getInstance();


    public void init(
        final BasicGeographicMap geographicMapInterface,
        final PathFindingInfo pathFindingInfo,
        final int[][] mapArray)
        throws Exception
    {
        //TWB - PathFinding
        //logUtil.put("Map Info: \n").append(ArrayUtil.toString(mapArray), this, commonStrings.INIT);

        class RaceTrackGeographicMapCellPositionFactoryInitVisitor implements
           GeographicMapCellPositionFactoryInitVisitorInterface
        {

            private int startLineId;
            private int finishLineId;

            public RaceTrackGeographicMapCellPositionFactoryInitVisitor()
            {
                final GeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = 
                    geographicMapInterface.getGeographicMapCellTypeFactory();
                
                this.startLineId = raceTrackGeographicMapCellTypeFactory.getStartType();
                this.finishLineId = raceTrackGeographicMapCellTypeFactory.getEndType();

                //logUtil.put(
                  // "Race Track Map Array: ").append(PathFindingInfoFactory.this.getName() +
                   //" columns: ").append(this.mapTwoDArray.length).append(" rows: ").append(this.mapTwoDArray[0].length, this, commonStrings.CONSTRUCTOR);
            }

            public void visit(final AllBinaryTiledLayer tiledLayer, final GeographicMapCellPosition cellPosition) throws Exception
            {
                final int row = cellPosition.getRow();
                final int column = cellPosition.getColumn();

                try
                {

                //AllBinaryTiledLayer2 allBinaryTiledLayer = RaceTrackGeographicMap.this.getAllBinaryTiledLayer();
                //this.cellTypeIdToGeographicMapCellType[allBinaryTiledLayer.getCell(col, row)];

                final int cellTypeId = mapArray[row][column];

                final int geographicCellType = geographicMapInterface.getCellTypeFromMapCellTypeInt(cellTypeId);

                if (geographicCellType == startLineId)
                {
                    BasePathFindingInfoFactory.this.addStartPathFindingNode(
                       pathFindingInfo,
                       cellPosition);
                }


                if (geographicCellType == finishLineId)
                {
                    if (geographicCellType == startLineId)
                    {
                        BasePathFindingInfoFactory.this.addEndPathFindingNode(
                           pathFindingInfo,
                           geographicMapInterface.getGeographicMapCellPositionFactoryInterface().getInstance(
                           geographicMapInterface,
                           cellPosition.getColumn(), cellPosition.getRow(),
                           tiledLayer.getColumns(), tiledLayer.getRows(),
                           tiledLayer.getCellWidth(), tiledLayer.getCellHeight()));
                    }
                    else
                    {
                        BasePathFindingInfoFactory.this.addEndPathFindingNode(
                           pathFindingInfo,
                           cellPosition);
                    }
                }

                final GeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = 
                    geographicMapInterface.getGeographicMapCellTypeFactory();
                
                final GeographicMapCellTypeFactory geographicMapCellTypeFactory = 
                    GeographicMapCellTypeFactory.getInstance();
                
                // Use the singleton history to verifiy the
                // PathFinder/PathFactory results
                if (raceTrackGeographicMapCellTypeFactory.isPath(geographicMapCellTypeFactory.getInstance(geographicCellType)))
                {
                    RaceTrackRoadsGeographicMapCellHistoryFactory.getInstance().track(cellPosition);
                }
                else
                {
                    // logUtil.put("Not Added For Tracking: " +
                    // cellPosition).append(" Type: ").append(this.mapTwoDArray[row][col],
                    // this, "setMap");
                }
                
                }
                catch(Exception e)
                {
                    logUtil.put(
                            new StringMaker().append("[").append(row).append("][").append(column).append("] in [").append(
                            mapArray.length).append("][").append(mapArray[0].length).append("]").toString(), 
                            this, "visit", e);
                    throw e;
                }
            }
        }

        geographicMapInterface.getGeographicMapCellPositionFactory().visit(
           new RaceTrackGeographicMapCellPositionFactoryInitVisitor());
    }
    
    private void addStartPathFindingNode(final PathFindingInfo pathFindingInfo,
            final GeographicMapCellPosition startGeographicMapCellPosition)
       throws Exception
    {
        //logUtil.put(commonStrings.START, this, "addStartPathFindingNode");

        //int extraTravelCost = RaceTrackGeographicMapCellType.FINISH_LINE_ROAD_CELL_TYPE.getTravelCost();
        // Setup Start Node
        //PathFindingNodeCostInfo startPathFindingNodeCostInfo =
          //  new PathFindingNodeCostInfo(0, extraTravelCost);

        //startPathFindingNodeCostInfo.setTotalCost();

        pathFindingInfo.addStartPathFindingNode(
            new PathFindingNode(null, startGeographicMapCellPosition));
           //startGeographicMapCellPosition, startPathFindingNodeCostInfo));

        // End Setup Start Node
    }

    private void addEndPathFindingNode(final PathFindingInfo pathFindingInfo,
            final GeographicMapCellPosition endGeographicMapCellPosition)
       throws Exception
    {
        //logUtil.put(commonStrings.START, this, "addEndPathFindingNode");

        // Setup Start Node
        //int extraTravelCost = RaceTrackGeographicMapCellType.FINISH_LINE_ROAD_CELL_TYPE.getTravelCost();

        //PathFindingNodeCostInfo endPathFindingNodeCostInfo =
          //  new PathFindingNodeCostInfo(extraTravelCost, 0);
        
        //endPathFindingNodeCostInfo.setTotalCost();

        pathFindingInfo.addEndPathFindingNode(
            new PathFindingNode(null, endGeographicMapCellPosition));
            //null, endGeographicMapCellPosition, endPathFindingNodeCostInfo));

        // End Setup Start Node
    }

}
