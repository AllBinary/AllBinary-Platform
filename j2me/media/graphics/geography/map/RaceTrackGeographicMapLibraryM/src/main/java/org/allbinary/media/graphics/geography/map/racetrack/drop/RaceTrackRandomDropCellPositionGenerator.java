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

import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactoryInterface;

import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.game.layer.drop.DropLayerFactory;
import org.allbinary.game.layer.pickup.PickupLayerCircularStaticPool;
import org.allbinary.game.layer.pickup.RaceTrackPickupLayer;
import org.allbinary.game.rand.MyRandomFactory;
import org.allbinary.graphics.GPoint;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

/**
 * 
 * @author user
 */
public class RaceTrackRandomDropCellPositionGenerator 
   extends RaceTrackDropCellPositionGenerator
{
    private static RaceTrackRandomDropCellPositionGenerator SINGLETON = new RaceTrackRandomDropCellPositionGenerator();

    public static RaceTrackRandomDropCellPositionGenerator getInstance()
    {
        return SINGLETON;
    }

    private final MyRandomFactory myRandomFactory = MyRandomFactory.getInstance();

    private final int cellsPerRowOrColumn = 3;
    private final int totalCells = cellsPerRowOrColumn * cellsPerRowOrColumn;

    private final int[] rowArray =
    { 0, 1, 2, 0, 1, 2, 0, 1, 2 };
    private final int[] columnArray =
    { 0, 0, 0, 1, 1, 1, 2, 2, 2 };

    private int cellWidth;
    private int cellHeight;

    private RaceTrackRandomDropCellPositionGenerator()
    {
    }

    public void update(AllBinaryGameLayerManager allBinaryGameLayerManager,
            BasicGeographicMap geographicMapInterface) throws Exception
    {
        super.update(allBinaryGameLayerManager, geographicMapInterface);
        
        AllBinaryTiledLayer tiledLayer = geographicMapInterface.getAllBinaryTiledLayer();

        this.cellWidth = tiledLayer.getCellWidth() / this.cellsPerRowOrColumn;
        this.cellHeight = tiledLayer.getCellHeight() / this.cellsPerRowOrColumn;
    }

    protected void drop(AllBinaryLayerManager allBinaryLayerManager, int index)
            throws Exception
    {
        GeographicMapCellPosition geographicMapCellPosition = 
            (GeographicMapCellPosition) this.list.get(index);

        //LogUtil.put(LogFactory.getInstance("Dropping: " + geographicMapCellPosition.toString()
          //      + " Point: " + geographicMapCellPosition.getPoint(), this,
            //    commonStrings.DROP));

        // RaceTrackGeographicMapCellType raceTrackGeographicMapCellType =
        // (RaceTrackGeographicMapCellType) this.typeList.get(index);

        GPoint point = geographicMapCellPosition.getPoint();

        int randomCell = myRandomFactory.getAbsoluteNextInt(this.totalCells);

        int row = this.rowArray[randomCell];
        int column = this.columnArray[randomCell];

        int x = point.getX() + (row * this.cellWidth);
        int y = point.getY() + (column * this.cellHeight);

        PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactory = 
            DropLayerFactory.getInstance().getRandomInstance();

        AllBinaryTiledLayer tiledLayer = this.raceTrackGeographicMap.getAllBinaryTiledLayer();
        
        RaceTrackPickupLayer pickupLayer = (RaceTrackPickupLayer) 
            PickupLayerCircularStaticPool.getInstance().getInstance(pickedUpLayerInterfaceFactory, x, y, tiledLayer.getZ() + 3);

        pickupLayer.setTiledLayer(tiledLayer);

        /*
         * PickedUpLayerInterfaceFactoryInterface
         * pickedUpLayerInterfaceFactoryInterface =
         * pickupLayer.getPickedUpLayerInterfaceFactoryInterface();
         * 
         * IndexedAnimationInterface iconIndexedAnimationInterface =
         * (IndexedAnimationInterface) ((IconLayer)
         * pickedUpLayerInterfaceFactoryInterface.getIconLayer()).getAnimationInterface();
         * 
         * IndexedAnimationInterface indexedAnimationInterface =
         * (IndexedAnimationInterface)
         * pickedUpLayerInterfaceFactoryInterface.getAnimationInterface();
         * 
         * if(raceTrackGeographicMapCellType ==
         * RaceTrackGeographicMapCellType.HORIZONTAL_STRAIGHT_ROAD_CELL_TYPE) {
         * indexedAnimationInterface.setFrame(0);
         * iconIndexedAnimationInterface.setFrame(0); } else {
         * indexedAnimationInterface.setFrame(1);
         * iconIndexedAnimationInterface.setFrame(1); }
         */

        allBinaryLayerManager.append(pickupLayer);
    }
}
