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
package org.allbinary.media.graphics.geography.map;

import org.allbinary.layer.Layer;

public class BasicGeographicMapUtil {

    private static final BasicGeographicMapUtil instance = new BasicGeographicMapUtil();
    
    public static BasicGeographicMapUtil getInstance()
    {
        return instance;
    }

    public int getBorderingRow(
            int direction, GeographicMapCellPosition oldGeographicMapCellPosition)
    throws Exception
 {
    final int LOCATION_CHANGE = 1;
    switch(direction)
    {
       case 0:
          return oldGeographicMapCellPosition.getRow();
       case 1:
          return oldGeographicMapCellPosition.getRow();
       case 2:
          return oldGeographicMapCellPosition.getRow() + LOCATION_CHANGE;
       case 3:
          return oldGeographicMapCellPosition.getRow() - LOCATION_CHANGE;
       default:
          throw new Exception("Only Four Directions");
    }
 }

    public int getBorderingColumn(
            int direction, GeographicMapCellPosition oldGeographicMapCellPosition)
    throws Exception
 {
    final int LOCATION_CHANGE = 1;
    switch(direction)
    {
       case 0:
          return oldGeographicMapCellPosition.getColumn() - LOCATION_CHANGE;
       case 1:
          return oldGeographicMapCellPosition.getColumn() + LOCATION_CHANGE;
       case 2:
          return oldGeographicMapCellPosition.getColumn();
       case 3:
          return oldGeographicMapCellPosition.getColumn();
       default:
          throw new Exception("Only Four Directions");
    }
 }
    
    public boolean isSameCellPosition(
            GeographicMapCellPosition fromGeographicMapCellPosition,
            GeographicMapCellPosition toGeographicMapCellPosition)
            throws Exception
    {
       int fromColumn = fromGeographicMapCellPosition.getColumn();
       int fromRow = fromGeographicMapCellPosition.getRow();

       int goColumn = toGeographicMapCellPosition.getColumn();
       int goRow = toGeographicMapCellPosition.getRow();

       if(fromColumn == goColumn && fromRow == goRow)
       {
           return true;
       }
       else
       {
           return false;
       }
    }

    public Layer[] createAllBinaryTiledLayerArray(final BasicGeographicMap[] geographicMapInterfaceArray) {
        final Layer[] tiledLayerArray = new Layer[geographicMapInterfaceArray.length];
        return this.createAllBinaryTiledLayerArray(geographicMapInterfaceArray, tiledLayerArray, 0);
    }
    
    public Layer[] createAllBinaryTiledLayerArray(final BasicGeographicMap[] geographicMapInterfaceArray, final Layer[] tiledLayerArray, final int startIndex) {

        BasicGeographicMap geographicMapInterface;
        final int size = geographicMapInterfaceArray.length;
        for(int index = size + startIndex; --index >= startIndex;) {
            geographicMapInterface = geographicMapInterfaceArray[index];
            tiledLayerArray[index] = geographicMapInterface.getAllBinaryTiledLayer();
        }

        return tiledLayerArray;

    }
}
