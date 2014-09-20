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
package org.allbinary.data.tables.user.commerce.inventory.order;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class OrderHistoryEntityFactory
{
   //private static final String CLASSNAME = "allbinary.data.tables.OrderHistoryEntity";
   
   private OrderHistoryEntityFactory()
   {
   }
   
   public static OrderHistoryEntityInterface getInstance() //throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance(CLASSNAME);
         //return (OrderHistoryEntityInterface) InterfaceCastProxy.newInstance(object);
         return (OrderHistoryEntityInterface) new org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntity();
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(error,"OrderHistoryEntityFactory","getInstance()",e);
         }
         throw e;
      }
       */
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, "OrderHistoryEntityFactory", "getInstance()", e));
         }
         return null;
      }
   }
}
