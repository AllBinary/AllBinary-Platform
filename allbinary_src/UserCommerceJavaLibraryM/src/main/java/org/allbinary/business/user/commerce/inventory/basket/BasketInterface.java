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
package org.allbinary.business.user.commerce.inventory.basket;

import java.util.HashMap;
import java.util.Set;

import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.business.user.commerce.money.MoneyException;

public interface BasketInterface
{         
   public void addItem(String id, String num);
   
   public void removeItem(String id);

   public void adjustItem(String id, String num);
     
   public String getTotalWeight();
   
   public Integer getNumberOfItems();
   
   public Set getIds();
   
   public HashMap getItems();

   public Integer getNumberOf(String product);
   
   public Money getSubTotal() throws MoneyException;         
}
