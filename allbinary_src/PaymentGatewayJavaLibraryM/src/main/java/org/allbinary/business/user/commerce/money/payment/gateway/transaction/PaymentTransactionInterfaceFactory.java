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
package org.allbinary.business.user.commerce.money.payment.gateway.transaction;

import org.allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionInterface;
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionInterfaceFactoryInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.commerce.inventory.order.OrderHistory;
import org.allbinary.business.user.commerce.money.payment.types.PaymentType;
import org.allbinary.business.user.commerce.money.payment.types.PaymentTypeUtil;

public class PaymentTransactionInterfaceFactory
{
	private static final PaymentTransactionInterfaceFactory instance = new PaymentTransactionInterfaceFactory();
	
	public static PaymentTransactionInterfaceFactory getInstance() {
		return instance;
	}   
	
   private PaymentTransactionInterfaceFactory()
   {
   }

   public PaymentTransactionInterface getInstance(OrderHistory orderReview) throws Exception
   {
      try
      {
         String gatewayName = orderReview.getPaymentMethod();
         //should move to payment class .getGatewayName();
         if(gatewayName!=null)
         {
            PaymentType paymentType = PaymentTypeUtil.getInstance().get(gatewayName);

            PaymentTransactionInterfaceFactoryInterface
               paymentTransactionInterfaceFactoryInterface = 
                  paymentType.getPaymentTransactionInterfaceFactoryInterface();

            PaymentTransactionInterface paymentTransactionInterface = 
               paymentTransactionInterfaceFactoryInterface.getInstance(
                  orderReview);

            return paymentTransactionInterface;
         }

         throw new Exception("Failed to create PaymentTransactionInterface: " +
            "GatewayName is: " + gatewayName);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENTERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate test data",
               this, "getInstance()", e));
         }
         throw e;
      }
   }
}
