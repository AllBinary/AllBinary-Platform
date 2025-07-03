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

import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayInterface;
import org.allbinary.business.user.commerce.money.payment.types.BasicPaymentType;

import java.util.Vector;
import org.allbinary.data.tables.BasicDataTableInterface;

public interface PaymentGatewayEntityInterface extends BasicDataTableInterface
{
   public PaymentGatewayInterface getPaymentGatewayInterface(String storeName, BasicPaymentType paymentType);
   
   public Vector findPaymentTypeVectorByStore(String storeName);
 
   public void remove(String storeName, BasicPaymentType paymentType);

   public void add(PaymentGatewayInterface paymentGatewayInterface);
   
   public void update(PaymentGatewayInterface paymentGatewayInterface);
}
