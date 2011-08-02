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
package allbinary.business.user.commerce.shipping.modules;

import allbinary.business.user.commerce.inventory.order.OrderInterface;
import allbinary.business.user.commerce.money.Money;

public class NoShippingModule implements ShippingInterface
{   
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
