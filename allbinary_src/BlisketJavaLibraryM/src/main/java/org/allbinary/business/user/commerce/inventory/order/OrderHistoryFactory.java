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
package org.allbinary.business.user.commerce.inventory.order;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntity;

//import org.allbinary.logic.java.proxy.InterfaceCastProxy;

//Retrieves a single order for a user
public class OrderHistoryFactory
{
   //private static final String CLASSNAME = "org.allbinary.data.tables.OrderHistoryEntity";
   
   private OrderHistoryFactory()
   {
   }
   
   public static OrderHistory getInstance(String id) //throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance().getInstance(CLASSNAME);
         //OrderHistoryEntityInterface orderHistoryEntityInterface
         //   = (OrderHistoryEntityInterface) InterfaceCastProxy.newInstance(object);
         OrderHistoryEntity orderHistoryEntityInterface
         = new org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntity();
         return (OrderHistory) orderHistoryEntityInterface.getOrder(id);
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
         {
            LogUtil.put(error,"OrderHistoryFactory","getInstance()",e);
         }
         throw e;
      }
       */
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"OrderHistoryFactory","getInstance()",e));
         }
         return null;
      }
   }
}
