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
package org.allbinary.business.user.commerce.inventory.order;

//import org.allbinary.data.tables.TableMappingInterface;

import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.business.user.commerce.money.payment.Payment;

public interface OrderHistoryInterface //extends DataMappingInterface
{
   void setUserName(String value);
     
   void setDefaultShippingAddress(StreetAddress shippingAddress);
   
   void setBillingAddress(StreetAddress billingAddress);
   
   void setPaymentInfo(Payment payment);

   void setShipDate(String value);

   void setOrderDate(String value);

   void setTransDate(String value);

   void setCancelDate(String value);

   void setStatus(String value);
   
   void setSubTotal(Money value);

   void setShippingCost(Money value);

   void setTaxes(Money value);

   void setTotal(Money value);
   
   String getUserName();     
   
   StreetAddress getShippingAddress();

   StreetAddress getBillingAddress();

   Payment getPaymentInfo();
   
   String getShipDate();

   String getOrderDate();

   String getTransDate();

   String getCancelDate();

   String getStatus();
   
   Money getSubTotal();

   Money getShippingCost();

   Money getTaxes();

   Money getTotal();      

   String getPaymentMethod();
}
