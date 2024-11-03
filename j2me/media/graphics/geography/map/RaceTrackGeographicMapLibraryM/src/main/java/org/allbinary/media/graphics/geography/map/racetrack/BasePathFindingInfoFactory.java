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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCelPositionFactoryInitVisitorInterface;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingInfo;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingNode;

/**
 *
 * @author user
 */
public class BasePathFindingInfoFactory {

    public void init(
        final BasicGeographicMap geographicMapInterface,
        final PathFindingInfo pathFindingInfo,
        final int[][] mapArray)
        throws Exception
    {
        //TWB - PathFinding
        //LogUtil.put(LogFactory.getInstance("Map Info: \n").append(ArrayUtil.toString(mapArray), this, CommonStrings.getInstance().INIT));

        class RaceTrackGeographicMapCellPositionFactoryInitVisitor implements
           GeographicMapCelPositionFactoryInitVisitorInterface
        {

            private int startLineId;
            private int finishLineId;

            public RaceTrackGeographicMapCellPositionFactoryInitVisitor()
            {
                final RaceTrackGeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = 
                    (RaceTrackGeographicMapCellTypeFactory) geographicMapInterface.getGeographicMapCellTypeFactory();
                
                this.startLineId = raceTrackGeographicMapCellTypeFactory.START_LINE_ROAD_CELL_TYPE.getType();
                this.finishLineId = raceTrackGeographicMapCellTypeFactory.FINISH_LINE_ROAD_CELL_TYPE.getType();

                //LogUtil.put(LogFactory.getInstance(
                  // "Race Track Map Array: ").append(PathFindingInfoFactory.this.getName() +
                   //" columns: ").append(this.i_Map2DArray.length).append(" rows: ").append(this.i_Map2DArray[0].length, this, CommonStrings.getInstance().CONSTRUCTOR));
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

                final RaceTrackGeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = 
                    (RaceTrackGeographicMapCellTypeFactory) geographicMapInterface.getGeographicMapCellTypeFactory();
                
                final GeographicMapCellTypeFactory geographicMapCellTypeFactory = 
                    GeographicMapCellTypeFactory.getInstance();
                
                // Use the singleton history to verifiy the
                // PathFinder/PathFactory results
                if (raceTrackGeographicMapCellTypeFactory.isRoad(geographicMapCellTypeFactory.getInstance(geographicCellType)))
                {
                    RaceTrackRoadsGeographicMapCellHistoryFactory.getInstance().track(cellPosition);
                }
                else
                {
                    // LogUtil.put(LogFactory.getInstance("Not Added For Tracking: " +
                    // cellPosition).append(" Type: ").append(this.i_Map2DArray[row][col],
                    // this, "setMap"));
                }
                
                }
                catch(Exception e)
                {
                    LogUtil.put(LogFactory.getInstance(
                            new StringMaker().append("[").append(row).append("][").append(column).append("] in [").append(
                            mapArray.length).append("][").append(mapArray[0].length).append("]").toString(), 
                            this, "visit", e));
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
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "addStartPathFindingNode"));

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
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "addEndPathFindingNode"));

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
