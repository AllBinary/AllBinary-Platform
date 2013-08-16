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

import allbinary.business.user.commerce.inventory.order.OrderHistory;
import allbinary.business.user.commerce.money.payment.types.TenderType;


public interface PaymentTransactionInterfaceFactoryInterface
{
   public PaymentTransactionInterface getInstance(OrderHistory orderHistory) throws Exception;

   public PaymentTransactionInterface getSaleTransaction(TenderType tenderType) throws Exception;

   public PaymentTransactionInterface getCreditTransaction(TenderType tenderType) throws Exception;

   public PaymentTransactionInterface getAuthorizationTransaction(TenderType tenderType) throws Exception;

   public PaymentTransactionInterface getCaptureDelayedTransaction(TenderType tenderType) throws Exception;

   public PaymentTransactionInterface getVoidTransaction(TenderType tenderType) throws Exception;

   public PaymentTransactionInterface getInquiryTransaction(TenderType tenderType) throws Exception;
}
