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

import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.direction.Direction;
import allbinary.direction.DirectionFactory;
import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.game.layer.geographic.map.LayerCoveringCellPositionsUtil;
import allbinary.graphics.GPoint;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import allbinary.media.graphics.geography.map.GeographicMapCellType;
import allbinary.media.graphics.geography.map.GeographicMapDirectionUtil;

/**
 * 
 * @author user
 */
public class RaceTrackAdjacentDropCellPositionGenerator
    extends RaceTrackDropCellPositionGenerator
{

    private static RaceTrackAdjacentDropCellPositionGenerator SINGLETON =
        new RaceTrackAdjacentDropCellPositionGenerator();

    private RaceTrackAdjacentDropCellPositionGenerator()
    {
    }

    public static RaceTrackDropCellPositionGenerator getInstance()
    {
        return SINGLETON;
    }
    //protected final BasicArrayList list = new BasicArrayList();
    //private GeographicMapCellPosition[] surroundingCellPositions = new GeographicMapCellPosition[8];
    private GeographicMapCellPosition[] surroundingCellPositions = new GeographicMapCellPosition[4];

    private GeographicMapCellPosition getFirstNonRoadAdjacentCellPosition(
        int column, int row) throws Exception
    {
        GeographicMapCellPosition nonRoadGeographicMapCellPosition = null;

        //AllBinaryTiledLayer tiledLayer =
        // this.raceTrackGeographicMap.getAllBinaryTiledLayer();
        //tiledLayer.getRows()

        BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
            this.raceTrackGeographicMap.getGeographicMapCellPositionFactory();

        surroundingCellPositions[0] =
            geographicMapCellPositionFactory.getInstance(column, row - 1);
        surroundingCellPositions[1] =
            geographicMapCellPositionFactory.getInstance(column, row + 1);
        surroundingCellPositions[2] =
            geographicMapCellPositionFactory.getInstance(column - 1, row);
        surroundingCellPositions[3] =
            geographicMapCellPositionFactory.getInstance(column + 1, row);

        /*
        surroundingCellPositions[0] =
        geographicMapCellPositionFactory.getInstance(column - 1, row - 1);
        surroundingCellPositions[1] =
        geographicMapCellPositionFactory.getInstance(column, row - 1);
        surroundingCellPositions[2] =
        geographicMapCellPositionFactory.getInstance(column + 1, row - 1);
        surroundingCellPositions[3] =
        geographicMapCellPositionFactory.getInstance(column - 1, row);
        surroundingCellPositions[4] =
        geographicMapCellPositionFactory.getInstance(column + 1, row);
        surroundingCellPositions[5] =
        geographicMapCellPositionFactory.getInstance(column - 1, row + 1);
        surroundingCellPositions[6] =
        geographicMapCellPositionFactory.getInstance(column, row + 1);
        surroundingCellPositions[7] =
        geographicMapCellPositionFactory.getInstance(column + 1, row + 1);
         */
        
        final RaceTrackGeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = 
            RaceTrackGeographicMapCellTypeFactory.getInstance();

        GeographicMapCellPosition geographicMapCellPosition;
        RaceTrackGeographicMapCellType raceTrackGeographicMapCellType;
        
        for (int index = surroundingCellPositions.length; --index >= 0;)
        {
            geographicMapCellPosition = surroundingCellPositions[index];

            raceTrackGeographicMapCellType = (RaceTrackGeographicMapCellType) 
                raceTrackGeographicMap.getCellTypeAt(geographicMapCellPosition);

            if (!raceTrackGeographicMapCellTypeFactory.isRoad(raceTrackGeographicMapCellType))
            {
                nonRoadGeographicMapCellPosition = geographicMapCellPosition;
                break;
            }
        }

        /*
        if (nonRoadGeographicMapCellPosition == null)
        {
        throw new Exception("No non road adjacent: column: " + column + " row: " + row);
        }
         */

        return nonRoadGeographicMapCellPosition;
    }
    private final Hashtable hashtable = new Hashtable();

    public void update(AllBinaryGameLayerManager allBinaryGameLayerManager,
        BaseRaceTrackGeographicMap raceTrackGeographicMap) throws Exception
    {
        hashtable.put(AllBinaryGameLayerManager.ID, 
                allBinaryGameLayerManager);
        hashtable.put(BaseRaceTrackGeographicMap.ID, raceTrackGeographicMap);

        super.update(allBinaryGameLayerManager, raceTrackGeographicMap);
    }

    private final GeographicMapDirectionUtil geographicMapDirectionUtil = GeographicMapDirectionUtil.getInstance();
    
    protected void drop(AllBinaryLayerManager allBinaryLayerManager, int index)
        throws Exception
    {
        GeographicMapCellPosition geographicMapCellPosition =
            (GeographicMapCellPosition) this.list.get(index);

        //LogUtil.put(LogFactory.getInstance("Road: " + geographicMapCellPosition.toString() + " " + geographicMapCellPosition.getPoint(), this, "drop"));

        GeographicMapCellPosition randomGeographicMapCellPosition =
            this.getFirstNonRoadAdjacentCellPosition(
            geographicMapCellPosition.getColumn(),
            geographicMapCellPosition.getRow());

        if (randomGeographicMapCellPosition == null)
        {
            return;
        }

        if (!DropCellPositionHistory.getInstance().isCellPositionWithDrop(geographicMapCellPosition))
        {
            Direction direction =
                geographicMapDirectionUtil.getDirectionFromCellPositionToAdjacentCellPosition(
                randomGeographicMapCellPosition,
                geographicMapCellPosition);

            LogUtil.put(LogFactory.getInstance("Road: " + geographicMapCellPosition + " " + randomGeographicMapCellPosition + " " + direction, this, "drop"));

            hashtable.put(DirectionFactory.getInstance().NAME, direction);

            GPoint point = randomGeographicMapCellPosition.getPoint();
            int x = point.getX();
            int y = point.getY();

            //LogUtil.put(LogFactory.getInstance("Dropping: " + randomGeographicMapCellPosition.toString() + " = " + randomGeographicMapCellPosition.getPoint(), this, "drop"));

            AllBinaryLayer layerInterface =
                RaceTrackAdjacentDropLayerFactory.getInstance().getRandomInstance().getInstance(
                hashtable, x, y);

            BasicArrayList list = LayerCoveringCellPositionsUtil.getAll(
                this.raceTrackGeographicMap,
                randomGeographicMapCellPosition, layerInterface,
                new BasicArrayList());

            if (DropCellPositionHistory.getInstance().anyCellPositionWithDrop(list))
            {
                return;
            }

            final RaceTrackGeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = 
                RaceTrackGeographicMapCellTypeFactory.getInstance();
            
            GeographicMapCellType geographicMapCellType;
            
            for (int index2 = list.size(); --index2 >= 0;)
            {
                geographicMapCellType =
                    this.raceTrackGeographicMap.getCellTypeAt(
                    (GeographicMapCellPosition) list.get(index2));

                if (raceTrackGeographicMapCellTypeFactory.isRoad(geographicMapCellType))
                {
                    return;
                }
            }

            DropCellPositionHistory.getInstance().add(
                list, layerInterface);

            allBinaryLayerManager.append(layerInterface);

        }
    }
}
