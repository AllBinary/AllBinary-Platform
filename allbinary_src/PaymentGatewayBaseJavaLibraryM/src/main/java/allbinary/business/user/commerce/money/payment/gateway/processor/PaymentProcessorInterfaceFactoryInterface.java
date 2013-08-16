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
package allbinary.business.user.commerce.money.payment.gateway.processor;

import allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionInterface;
import allbinary.logic.visual.transform.info.TransformInfoInterface;

public interface PaymentProcessorInterfaceFactoryInterface
{
   public PaymentProcessorInterface getInstance(
      TransformInfoInterface transformInfoInterface, 
      PaymentTransactionInterface paymentTransactionInterface)
      throws Exception;
}
