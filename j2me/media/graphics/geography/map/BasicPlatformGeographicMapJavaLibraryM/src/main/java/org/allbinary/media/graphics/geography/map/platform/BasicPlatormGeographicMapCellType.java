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
package org.allbinary.media.graphics.geography.map.platform;

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;

public class BasicPlatormGeographicMapCellType extends GeographicMapCellType
{   
   public static final BasicPlatormGeographicMapCellType BLOCK_CELL_TYPE = 
      new BasicPlatormGeographicMapCellType(
      SmallIntegerSingletonFactory.getInstance().getInstance(1));
   
   private BasicPlatormGeographicMapCellType(Integer type)
   {
      super(type);
   }
   
   public static void init()
   {
      LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, "BasicPlatormGeographicMapCellType", CommonStrings.getInstance().INIT));
      
      GeographicMapCellTypeFactory.getInstance().EMPTY_CELL_TYPE = 
          new BasicPlatormGeographicMapCellType(SmallIntegerSingletonFactory.getInstance().getInstance(0));
   }
}
