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
package org.allbinary.data.tables.user.commerce.money.payment.gateway;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class PaymentGatewayEntityFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   //private static final String CLASSNAME = "org.allbinary.data.tables.user.commerce.money.payment.gateway.PaymentGatewayEntity";
   
   private PaymentGatewayEntityFactory()
   {
   }
   
   public static PaymentGatewayEntity getInstance() throws Exception
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         //Object object = AbeFactory.getInstance().getInstance(CLASSNAME);
         //return (PaymentGatewayTransactionEntityInterface) InterfaceCastProxy.newInstance(object);
         return new org.allbinary.data.tables.user.commerce.money.payment.gateway.PaymentGatewayEntity();
      }
      /*
      catch(LicensingException e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE,e);
         }
         throw e;
      }
       */
      catch(Exception e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, "PaymentGatewayEntityFactory", commonStrings.GET_INSTANCE, e);
         }
         throw e;
      }
   }
}
