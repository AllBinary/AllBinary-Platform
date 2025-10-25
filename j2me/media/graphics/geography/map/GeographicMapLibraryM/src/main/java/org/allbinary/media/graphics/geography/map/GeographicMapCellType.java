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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.string.CommonStrings;

public class GeographicMapCellType
{
    public static final GeographicMapCellType[] NULL_GEOGRAPHIC_MAP_CELL_TYPE_ARRAY = new GeographicMapCellType[0];
    public static final GeographicMapCellType NULL_GEOGRAPHIC_MAP_CELL_TYPE = new GeographicMapCellType(Integer.MIN_VALUE);
    
    protected final LogUtil logUtil = LogUtil.getInstance();
   
   private final int type;

   public GeographicMapCellType(final int type)
   {
       //logUtil.put(Integer.toString(type), this, commonStrings.CONSTRUCTOR);
      this.type = type;
      if(type != Integer.MIN_VALUE) {
          //final CommonStrings commonStrings = CommonStrings.getInstance();
          //logUtil.put("type: " + Integer.toString(type), this, commonStrings.CONSTRUCTOR);
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
