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
package allbinary.media.graphics.geography.map;

import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.layer.Layer;

public interface GeographicMapInterface
{
   public Integer getId();
   public String getName();
   
   void reset();
   
   AllBinaryTiledLayer getAllBinaryTiledLayer();

   boolean getCellPositionsAt(
      Layer layer, 
      GeographicMapCellPosition[][] currentCellPositionArray,
      GeographicMapCellPosition[][] cellPositionArray)
      throws Exception;
         
   GeographicMapCellPosition getCellPositionAt(int x, int y)
      throws Exception;

   GeographicMapCellPosition getCellPositionAtNoThrow(int x, int y)
       throws Exception;

   //GPoint getPointAt(GeographicMapCellPosition geographicMapCellPosition)
     // throws Exception;

   int getCellTypeFromMapCellTypeInt(int cellTypeId);

   GeographicMapCellType getCellTypeAt(int x, int y) 
      throws Exception;
   GeographicMapCellType getCellTypeAt(GeographicMapCellPosition geographicMapCellPosition) 
      throws Exception;
   
   BasicGeographicMapCellPositionFactory getGeographicMapCellPositionFactory();
   GeographicMapCellPositionFactoryInterface getGeographicMapCellPositionFactoryInterface();
   
   public BasicColor getForegroundBasicColor();
   public BasicColor getBackgroundBasicColor();

}
