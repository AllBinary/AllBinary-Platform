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

import org.allbinary.business.user.commerce.inventory.order.OrderHistory;
import org.allbinary.business.user.commerce.money.payment.types.TenderType;


public interface PaymentTransactionInterfaceFactoryInterface
{
   PaymentTransactionInterface getInstance(OrderHistory orderHistory) throws Exception;

   PaymentTransactionInterface getSaleTransaction(TenderType tenderType) throws Exception;

   PaymentTransactionInterface getCreditTransaction(TenderType tenderType) throws Exception;

   PaymentTransactionInterface getAuthorizationTransaction(TenderType tenderType) throws Exception;

   PaymentTransactionInterface getCaptureDelayedTransaction(TenderType tenderType) throws Exception;

   PaymentTransactionInterface getVoidTransaction(TenderType tenderType) throws Exception;

   PaymentTransactionInterface getInquiryTransaction(TenderType tenderType) throws Exception;
}
