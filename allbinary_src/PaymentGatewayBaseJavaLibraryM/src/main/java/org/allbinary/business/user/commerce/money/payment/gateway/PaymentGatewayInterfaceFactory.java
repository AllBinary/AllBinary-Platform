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
package org.allbinary.business.user.commerce.money.payment.gateway;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.commerce.money.payment.types.BasicPaymentType;
import org.allbinary.business.user.commerce.money.payment.types.BasicPaymentTypeUtil;
import org.allbinary.logic.communication.http.request.RequestParams;
import org.allbinary.string.CommonStrings;

public class PaymentGatewayInterfaceFactory
   implements PaymentGatewayInterfaceFactoryInterface
{
   public PaymentGatewayInterfaceFactory()
   {
   }
   
   public PaymentGatewayInterface getInstance(
      HttpServletRequest httpServletRequest) throws Exception
   {
       final CommonStrings commonStrings = CommonStrings.getInstance();
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENT))
         {
            LogUtil.put(LogFactory.getInstance("Getting", this, commonStrings.GET_INSTANCE));
         }
         
         return this.getInstance(
            new RequestParams(httpServletRequest).toHashMap());
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENTERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed", this, "getInstance(HttpServletRequest)", e));
         }
         throw e;
      }
   }
   
   public PaymentGatewayInterface getInstance(
      BasicPaymentType paymentType)
      throws Exception
   {
      HashMap hashMap = new HashMap();
      hashMap.put(PaymentGatewayData.NAME.toString(), paymentType.getName());

      return this.getInstance(hashMap);
   }

   public PaymentGatewayInterface getInstance(
      HashMap hashMap) throws Exception
   {
       final CommonStrings commonStrings = CommonStrings.getInstance();
      try
      {
         String gatewayName = (String) hashMap.get(PaymentGatewayData.NAME.toString());
         BasicPaymentType paymentType = BasicPaymentTypeUtil.getInstance().get(gatewayName);

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENT))
         {
            LogUtil.put(LogFactory.getInstance("Getting PaymentGatewayInterface for PaymentType: " +
               paymentType.getName() + " with: " + hashMap, this, commonStrings.GET_INSTANCE));
         }

         PaymentGatewayInterfaceFactoryInterface paymentGatewayInterfaceFactoryInterface =
            paymentType.getPaymentGatewayInterfaceFactoryInterface();

         return paymentGatewayInterfaceFactoryInterface.getInstance(hashMap);
         //return new PaymentGateway(hashMap);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENTERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed", this, commonStrings.GET_INSTANCE, e));
         }
         throw e;
      }
   }
}
