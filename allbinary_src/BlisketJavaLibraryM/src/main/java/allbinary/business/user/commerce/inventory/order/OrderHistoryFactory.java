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
package allbinary.business.user.commerce.inventory.order;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntity;

//import allbinary.logic.java.proxy.InterfaceCastProxy;

//Retrieves a single order for a user
public class OrderHistoryFactory
{
   //private static final String CLASSNAME = "allbinary.data.tables.OrderHistoryEntity";
   
   private OrderHistoryFactory()
   {
   }
   
   public static OrderHistory getInstance(String id) //throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance(CLASSNAME);
         //OrderHistoryEntityInterface orderHistoryEntityInterface
         //   = (OrderHistoryEntityInterface) InterfaceCastProxy.newInstance(object);
         OrderHistoryEntity orderHistoryEntityInterface
         = new allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntity();
         return (OrderHistory) orderHistoryEntityInterface.getOrder(id);
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
         {
            LogUtil.put(error,"OrderHistoryFactory","getInstance()",e);
         }
         throw e;
      }
       */
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"OrderHistoryFactory","getInstance()",e));
         }
         return null;
      }
   }
}
