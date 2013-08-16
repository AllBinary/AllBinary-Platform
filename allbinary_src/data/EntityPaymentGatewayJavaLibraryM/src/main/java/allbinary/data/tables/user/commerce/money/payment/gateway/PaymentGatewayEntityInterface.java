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
package allbinary.data.tables.user.commerce.money.payment.gateway;

import allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayInterface;
import allbinary.business.user.commerce.money.payment.types.BasicPaymentType;
import allbinary.data.tables.BasicTableInterface;

import java.util.Vector;

public interface PaymentGatewayEntityInterface extends BasicTableInterface
{
   public PaymentGatewayInterface getPaymentGatewayInterface(String storeName, BasicPaymentType paymentType);
   
   public Vector findPaymentTypeVectorByStore(String storeName);
 
   public void remove(String storeName, BasicPaymentType paymentType);

   public void add(PaymentGatewayInterface paymentGatewayInterface);
   
   public void update(PaymentGatewayInterface paymentGatewayInterface);
}
