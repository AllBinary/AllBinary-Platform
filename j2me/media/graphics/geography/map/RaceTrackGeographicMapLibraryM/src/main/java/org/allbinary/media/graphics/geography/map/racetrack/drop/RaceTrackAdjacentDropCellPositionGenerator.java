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
package org.allbinary.media.graphics.geography.map.racetrack.drop;

import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionFactory;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.geographic.map.LayerCoveringCellPositionsUtil;
import org.allbinary.graphics.GPoint;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.GeographicMapDirectionUtil;
import org.allbinary.media.graphics.geography.map.drop.DropCellPositionGeneratorInterface;
import org.allbinary.media.graphics.geography.map.drop.DropCellPositionHistory;
import org.allbinary.media.graphics.geography.map.racetrack.BaseRaceTrackGeographicMap;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapCellType;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapCellTypeFactory;

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

    public static DropCellPositionGeneratorInterface getInstance()
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
                (RaceTrackGeographicMapCellTypeFactory) this.raceTrackGeographicMap.getGeographicMapCellTypeFactory();

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
            BasicGeographicMap geographicMapInterface) throws Exception
    {
        hashtable.put(AllBinaryGameLayerManager.ID, 
                allBinaryGameLayerManager);
        hashtable.put(BaseRaceTrackGeographicMap.ID, geographicMapInterface);

        super.update(allBinaryGameLayerManager, geographicMapInterface);
    }

    private final GeographicMapDirectionUtil geographicMapDirectionUtil = GeographicMapDirectionUtil.getInstance();
    
    protected void drop(AllBinaryLayerManager allBinaryLayerManager, int index)
        throws Exception
    {
        GeographicMapCellPosition geographicMapCellPosition =
            (GeographicMapCellPosition) this.list.get(index);

        //StringMaker stringBuffer = new StringMaker();
        
        //final String METHOD_NAME = "drop";
        //final String ROAD_LABEL = "Road: ";
        
        //stringBuffer.append("Dropping around ");
        //stringBuffer.append(ROAD_LABEL);
        //stringBuffer.append(geographicMapCellPosition.toString());
        //stringBuffer.append(CommonSeps.getInstance().SPACE);
        //stringBuffer.append(geographicMapCellPosition.getPoint());
        //PreLogUtil.put(stringBuffer.toString(), this, METHOD_NAME);
        //LogUtil.put(, this, "drop"));

        GeographicMapCellPosition randomGeographicMapCellPosition =
            this.getFirstNonRoadAdjacentCellPosition(
            geographicMapCellPosition.getColumn(),
            geographicMapCellPosition.getRow());

        if (randomGeographicMapCellPosition == null)
        {
            return;
        }

        final DropCellPositionHistory dropCellPositionHistory = DropCellPositionHistory.getInstance();
        
        if (!dropCellPositionHistory.isCellPositionWithDrop(geographicMapCellPosition))
        {
            Direction direction =
                geographicMapDirectionUtil.getDirectionFromCellPositionToAdjacentCellPosition(
                randomGeographicMapCellPosition,
                geographicMapCellPosition);

            //stringBuffer.delete(0, stringBuffer.length());
            //stringBuffer.append(ROAD_LABEL);
            //stringBuffer.append(geographicMapCellPosition.toString());
            //stringBuffer.append(CommonSeps.getInstance().SPACE);
            //stringBuffer.append(randomGeographicMapCellPosition.toString());
            //stringBuffer.append(CommonSeps.getInstance().SPACE);
            //stringBuffer.append(direction);

            //PreLogUtil.put(stringBuffer.toString(), this, METHOD_NAME);
            //LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_NAME));

            hashtable.put(DirectionFactory.getInstance().NAME, direction);

            GPoint point = randomGeographicMapCellPosition.getPoint();
            int x = point.getX();
            int y = point.getY();
            int z = point.getZ();

            //LogUtil.put(LogFactory.getInstance("Dropping: " + randomGeographicMapCellPosition.toString() + " = " + randomGeographicMapCellPosition.getPoint(), this, "drop"));

            AllBinaryLayer layerInterface =
                RaceTrackAdjacentDropLayerFactory.getInstance().getRandomInstance().getInstance(
                hashtable, x, y, z);

            BasicArrayList list = LayerCoveringCellPositionsUtil.getAll(
                this.raceTrackGeographicMap,
                randomGeographicMapCellPosition, layerInterface,
                new BasicArrayList());

            if (dropCellPositionHistory.anyCellPositionWithDrop(list))
            {
                //PreLogUtil.put("Already Has A Drop", this, METHOD_NAME);
                return;
            }
            
            GeographicMapCellType geographicMapCellType;
            
            for (int index2 = list.size(); --index2 >= 0;)
            {
                geographicMapCellType =
                    this.raceTrackGeographicMap.getCellTypeAt(
                    (GeographicMapCellPosition) list.get(index2));

                if (raceTrackGeographicMapCellTypeFactory.isRoad(geographicMapCellType))
                {
                    //PreLogUtil.put("Can't Drop On a Road", this, METHOD_NAME);
                    return;
                }
            }

            //PreLogUtil.put("Dropping: " + layerInterface, this, METHOD_NAME);
            
            dropCellPositionHistory.add(list, layerInterface);

            allBinaryLayerManager.append(layerInterface);

        }
    }
}
