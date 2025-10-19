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

import org.allbinary.graphics.CellPosition;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;

public class GeographicMapCellPosition extends CellPosition
{
   private GPoint point;
   private GPoint midPoint;

   public GeographicMapCellPosition(int column, int row, int columns, int rows, int width, int height)
   {
      super(column, row, columns, rows);

      //Integer x = new Integer(this.getColumn() * tiledLayer.getCellWidth());
      //Integer y = new Integer(this.getRow() * tiledLayer.getCellHeight());
      //this.point = PointFactory.getInstance(x, y);

      int x = this.getColumn() * width;
      int y = this.getRow() * height;
      
      final PointFactory pointFactory = PointFactory.getInstance();
      
      this.point = pointFactory.getInstance(x, y);
      this.midPoint = pointFactory.getInstance(x + (width >> 1), y + (height >> 1));
   }

   public GPoint getPoint()
   {
      return point;
   }

    /**
     * @return the midPoint
     */
    public GPoint getMidPoint()
    {
        return midPoint;
    }

    /**
     * @param midPoint the midPoint to set
     */
    public void setMidPoint(GPoint midPoint)
    {
        this.midPoint = midPoint;
    }
}
