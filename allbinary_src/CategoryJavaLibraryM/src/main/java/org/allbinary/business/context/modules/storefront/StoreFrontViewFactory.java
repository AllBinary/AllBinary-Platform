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
import org.allbinary.data.tree.dom.DomNodeInterface;

public class StoreFrontViewFactory
{   
   private StoreFrontViewFactory()
   {
   }
   
   public static DomNodeInterface getInstance(String storeName) //throws LicensingException
   {
      try
      {
         StoreFrontsEntity storeFronts = 
             StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance();

         return (DomNodeInterface) new StoreFrontView(storeFronts.getStoreFrontInterface(storeName));
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"StoreFrontFactory","getInstance()",e));
         }
         return null;
      }
   }
}
