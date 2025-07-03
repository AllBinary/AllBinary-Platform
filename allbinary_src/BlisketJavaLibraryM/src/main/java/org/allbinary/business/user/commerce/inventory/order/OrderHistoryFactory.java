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
import org.allbinary.string.CommonStrings;

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
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
         {
            LogUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
         }
         throw e;
      }
       */
      catch(Exception e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, "OrderHistoryFactory",commonStrings.GET_INSTANCE,e));
         }
         return null;
      }
   }
}
