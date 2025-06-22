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
package org.allbinary.business.user.commerce.shipping.modules;

import org.allbinary.business.user.commerce.inventory.order.OrderInterface;
import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.string.CommonStrings;

public class NoShippingModule implements ShippingInterface
{   
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private final String name = "No Shipping";
   private final String description = "This shipping method is for items that are not shipped.";

   public NoShippingModule()
   {      
   }
   
   public Money getCost(OrderInterface order)
   {
      return new Money();      
   }
      
   public String getDescription()
   {
      return this.description;
   }
   
   public String getName()
   {
      return this.name;
   }   
}
