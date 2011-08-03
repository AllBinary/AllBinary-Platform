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
package allbinary.business.user.commerce.money.payment.gateway.transaction;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.commerce.inventory.order.OrderHistory;
import allbinary.business.user.commerce.money.payment.types.PaymentType;
import allbinary.business.user.commerce.money.payment.types.PaymentTypeUtil;

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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PAYMENTERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate test data",
               this, "getInstance()", e));
         }
         throw e;
      }
   }
}
