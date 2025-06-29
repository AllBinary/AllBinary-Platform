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

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class GeographicMapCellType
{   
   private final int type;

   public GeographicMapCellType(final int type)
   {
       //LogUtil.put(LogFactory.getInstance(Integer.toString(type), this, commonStrings.CONSTRUCTOR));
      this.type = type;
      if(type != Integer.MIN_VALUE) {
          //LogUtil.put(LogFactory.getInstance("type: " + Integer.toString(type), this, commonStrings.CONSTRUCTOR));
          final GeographicMapCellTypeFactory geographicMapCellTypeFactory = GeographicMapCellTypeFactory.getInstance();
          final GeographicMapCellType[] geographicMapCellTypeArray = geographicMapCellTypeFactory.getGeographicMapCellTypeArray();
          if(geographicMapCellTypeArray[type] == null) {
              geographicMapCellTypeArray[type] = this;
          } else {
              throw new RuntimeException();
          }
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
