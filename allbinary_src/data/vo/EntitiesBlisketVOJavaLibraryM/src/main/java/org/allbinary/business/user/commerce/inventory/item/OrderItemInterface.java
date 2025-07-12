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
package org.allbinary.business.user.commerce.inventory.item;

import org.allbinary.business.user.address.StreetAddress;

public interface OrderItemInterface extends ItemInterface
{
   void setGroup(String value);

   void setTax(String value);
   
   void setShippingType(String value);
   
   void setShippingCost(String value);
   
   void setShippingAddress(StreetAddress value);
   
   void setSpecial(String value);
   
   String getGroup();
   
   String getTax();
   
   String getShippingType();

   String getShippingCost();
   
   StreetAddress getShippingAddress();

   String getSpecial();
}
