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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.business.user.commerce.money.MoneyException;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntity;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;

public class Basket implements BasketInterface, Serializable
{
   private HashMap items = new HashMap();
   
   public Basket()
   {
      
   }
   
   public void addItem(String id, String num)
   {
      if(items.containsKey(id))
      {
         String oldNumStr = new String((String) items.get(id));
         Integer oldNumInt = Integer.valueOf(oldNumStr);
         Integer numInt = Integer.valueOf(num);
         Integer newNumInt = new Integer(oldNumInt.intValue() + numInt.intValue());
         items.put(id,newNumInt.toString());
      }
      else
      {
         items.put(id,num);
      }
   }
   
   public void removeItem(String id)
   {
      items.remove(id);
   }
   
   public void adjustItem(String id, String num)
   {
      if(Integer.valueOf(num).intValue()>0)
      {
         items.put(id,num);
      }
      else
      {
         items.remove(id);
      }
   }
   
   public String getTotalWeight()
   {
      final HashMap itemsAndNumberInBasket = getItems();
      final Money totalWeight = new Money();
      if(itemsAndNumberInBasket.size() > 0)
      {
         final Set items = itemsAndNumberInBasket.keySet();
         String product;
         Float weightFloat;
         final Object[] itemArray = items.toArray();
         final int size = itemArray.length;
         for(int index = 0; index < size; index++)
         {
            product = new String((String) itemArray[index]);
            weightFloat = new Float(new InventoryEntity().getWeight(product));
            weightFloat = new Float(getNumberOf(product).floatValue()*weightFloat.floatValue());
            totalWeight.add(weightFloat.toString());
         }
      }
      return totalWeight.toString();
   }
   
   public Integer getNumberOfItems()
   {
      return new Integer(items.size());
   }
   
   public Set getIds()
   {
      Set set = items.keySet();
      return set;
   }
   
   public HashMap getItems()
   {
      return items;
   }
   
   public Integer getNumberOf(String product)
   {
      String numStr =(String) items.get(product);
      return Integer.valueOf(numStr);
   }
   
   public Money getSubTotal() throws MoneyException
   {
      HashMap itemsAndNumberInBasket = getItems();
      Money subTotal = new Money();
      
      if(itemsAndNumberInBasket.size() > 0)
      {
         
         Set items = itemsAndNumberInBasket.keySet();

         InventoryEntityFactory inventoryEntityFactory =
                 InventoryEntityFactory.getInstance();

         InventoryEntity inventoryEntity = (InventoryEntity)
                 inventoryEntityFactory.getInventoryEntityInstance();

         final Object[] itemArray = items.toArray();
         final int size = itemArray.length;
         for(int index = 0; index < size; index++)
         {
            String product = new String((String) itemArray[index]);
            ItemInterface itemInterface =
                    inventoryEntity.getItem(product);
            
            Money productTotal = itemInterface.getPrice();
            productTotal.multiply(getNumberOf(product).intValue());
            subTotal.add(productTotal.toString());
         }
      }
      return subTotal;
   }
   
}