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

import org.allbinary.business.user.commerce.inventory.basket.BasketInterface;
import org.allbinary.business.user.commerce.inventory.order.OrderInterface;
import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.string.CommonStrings;

public class BasicWeightShippingModule implements ShippingInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private final String name = "Basic Shipping (5 to 7 working days)";
   private final String description = "We will soon provide a variety of shipping methods.";

   public BasicWeightShippingModule()
   {            
   }

   public Money getCost(OrderInterface order)
   {
      BasketInterface basket = order.getBasket();
      
      Money money = new Money();            
      money.add(basket.getTotalWeight());
      money.multiply(1);      
      money.add("3.50");
      return money;
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
