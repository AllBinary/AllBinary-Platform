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

import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class GeographicMapCellType
{   
   private int type;

   public GeographicMapCellType(final int type)
   {
      this.type = type;
      if(type != Integer.MIN_VALUE) {
          GeographicMapCellTypeFactory.getInstance().getGeographicMapCellTypeArray()[type] = this;
      }
   }

   public int getType()
   {
      return this.type;
   }
   
   public String toString()
   {
      return SmallIntegerSingletonFactory.getInstance().getInstance(this.type).toString();
   }
}
