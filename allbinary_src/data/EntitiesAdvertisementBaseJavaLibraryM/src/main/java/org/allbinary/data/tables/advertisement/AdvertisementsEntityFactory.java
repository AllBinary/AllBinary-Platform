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
package org.allbinary.data.tables.advertisement;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class AdvertisementsEntityFactory
{
   private static final String CLASSNAME = "org.allbinary.data.tables.AdvertisementEntity";
   
   private AdvertisementsEntityFactory()
   {
   }
   
   public static AdvertisementsEntityInterface getInstance()
   {
      try
      {
         return (AdvertisementsEntityInterface) new AdvertisementsEntity();
      }
      catch(Exception e)
      {
         String error = "Failed get Instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, "AdvertisementEntityFactory", "getInstance()", e));
         }
         return null;
      }   
   }
}
