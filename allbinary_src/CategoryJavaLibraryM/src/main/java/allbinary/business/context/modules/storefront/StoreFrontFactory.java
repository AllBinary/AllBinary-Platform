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
package allbinary.business.context.modules.storefront;

//import allbinary.logic.java.proxy.InterfaceCastProxy;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tables.context.module.storefronts.StoreFrontsEntity;
import allbinary.data.tables.context.module.storefronts.StoreFrontsEntityFactory;

public class StoreFrontFactory
{   
   private StoreFrontFactory()
   {
   }
   
   public static StoreFrontInterface getInstance(String storeName) //throws LicensingException
   {
      try
      {
         StoreFrontsEntity storeFronts = 
             StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance();

         return storeFronts.getStoreFrontInterface(storeName);
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
         {
            LogUtil.put(error,"StoreFrontFactory","getInstance()",e);
         }
         //throw e;
         return null;
      }
       */
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"StoreFrontFactory","getInstance()",e));
         }
         return null;
      }
   }
}
