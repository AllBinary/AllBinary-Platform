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
package org.allbinary.data.tables.advertisement.areas;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class AdvertisementAreasEntityFactory
{
   private static final String CLASSNAME = "org.allbinary.data.tables.AdvertisementEntity";
   
   private AdvertisementAreasEntityFactory()
   {
   }
   
   public static AdvertisementAreasEntityInterface getInstance()
   {
      try
      {
         return (AdvertisementAreasEntityInterface) new AdvertisementAreasEntity();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, "AdvertisementAreasEntityFactory", commonStrings.GET_INSTANCE, e));
         }
         return null;
      }   
   }
}
