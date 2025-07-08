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
package org.allbinary.business.context.modules.storefront;

//import org.allbinary.logic.java.proxy.InterfaceCastProxy;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tables.context.module.storefronts.StoreFrontsEntity;
import org.allbinary.data.tables.context.module.storefronts.StoreFrontsEntityFactory;
import org.allbinary.string.CommonStrings;

public class StoreFrontFactory
{   
   private StoreFrontFactory()
   {
   }
   
   public static StoreFront getInstance(final String storeName) //throws LicensingException
   {
      try
      {
         final StoreFrontsEntity storeFronts = StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance();

         return storeFronts.getStoreFrontInterface(storeName);
      }
      /*
      catch(LicensingException e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
         {
            LogUtil.put(commonStrings.EXCEPTION, this,commonStrings.GET_INSTANCE,e);
         }
         //throw e;
         return null;
      }
       */
      catch(Exception e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, "StoreFrontFactory",commonStrings.GET_INSTANCE,e));
         }
         return null;
      }
   }
}
