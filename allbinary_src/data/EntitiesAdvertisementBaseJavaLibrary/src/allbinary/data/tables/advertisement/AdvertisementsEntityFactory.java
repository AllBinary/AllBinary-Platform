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
package allbinary.data.tables.advertisement;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class AdvertisementsEntityFactory
{
   private static final String CLASSNAME = "allbinary.data.tables.AdvertisementEntity";
   
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, "AdvertisementEntityFactory", "getInstance()", e));
         }
         return null;
      }   
   }
}
