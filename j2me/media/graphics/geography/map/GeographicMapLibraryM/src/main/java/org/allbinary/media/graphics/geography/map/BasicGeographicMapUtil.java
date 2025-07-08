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
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class BasicGeographicMapUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final BasicGeographicMapUtil instance = new BasicGeographicMapUtil();
    
    public static BasicGeographicMapUtil getInstance()
    {
        return instance;
    }

    public int getBorderingRow(
            final int direction, final GeographicMapCellPosition oldGeographicMapCellPosition)
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
            final int direction, final GeographicMapCellPosition oldGeographicMapCellPosition)
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
            final GeographicMapCellPosition fromGeographicMapCellPosition,
            final GeographicMapCellPosition toGeographicMapCellPosition)
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
        int count = 0;
        for(int index = size + startIndex; --index >= startIndex;) {
            geographicMapInterface = geographicMapInterfaceArray[index];
            tiledLayerArray[count++] = geographicMapInterface.getAllBinaryTiledLayer();
        }

        return tiledLayerArray;

    }

    public void move(final BasicGeographicMap[] geographicMapInterfaceArray, final int dx, final int dy) {

        BasicGeographicMap geographicMapInterface;
        final int size = geographicMapInterfaceArray.length;
        for(int index = size; --index >= 0;) {
            geographicMapInterface = geographicMapInterfaceArray[index];
            geographicMapInterface.getAllBinaryTiledLayer().move(dx, dy);
        }
    }

    public void setPosition(final BasicGeographicMap[] geographicMapInterfaceArray, final int x, final int y) {

        //final CommonStrings commonStrings = CommonStrings.getInstance();
        //logUtil.put(new StringMaker().append("x: ").append(x).append(" y: ").append(y).toString(), this, commonStrings.PROCESS);
        BasicGeographicMap geographicMapInterface;
        final int size = geographicMapInterfaceArray.length;
        for(int index = size; --index >= 0;) {
            geographicMapInterface = geographicMapInterfaceArray[index];
            geographicMapInterface.getAllBinaryTiledLayer().setPosition(x, y, geographicMapInterface.getAllBinaryTiledLayer().getZ());
        }
    }
    
}
