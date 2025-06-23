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
package org.allbinary.business.context.modules.storefront.statistics.inventory;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityInterface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class StoreFrontInventoryStatistics
   implements StoreFrontInventoryStatisticsInterface
{
   private long totalNumberOfItems;
   private Money totalInventorySaleValueMoney;
   
   public StoreFrontInventoryStatistics(StoreFrontInterface storeFrontInterface) throws Exception
   {
      this.totalInventorySaleValueMoney = new Money();
      
      InventoryEntityInterface inventoryEntityInterface = 
         InventoryEntityFactory.getInstance().getInventoryEntityInstance();
      Vector itemVector = inventoryEntityInterface.getItems(storeFrontInterface);
      
      final Object[] itemArray = itemVector.toArray();
      final int size = itemArray.length;
      for (int index = 0; index < size; index++)      
      {
         ItemInterface itemInterface = (ItemInterface) itemArray[index];
         int numberInStock = Integer.valueOf(itemInterface.getNumber()).intValue();
         this.totalNumberOfItems = this.totalNumberOfItems + numberInStock;

         Money itemPriceMoney = itemInterface.getPrice();
         itemPriceMoney.multiply(numberInStock);
         this.totalInventorySaleValueMoney.add(itemPriceMoney.toString());
      }
   }
 
   public Long getNumber()
   {
      return new Long(this.totalNumberOfItems);
   }

   public Money getTotal()
   {
      return this.totalInventorySaleValueMoney;
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      
      hashMap.put(StoreFrontInventoryStatisticsData.getInstance().NUMBEROFITEMS, this.getNumber().toString());
      hashMap.put(StoreFrontInventoryStatisticsData.getInstance().TOTALVALUE, this.getTotal().toString());

      return hashMap;
   }
   
   public Vector toVector()
   {
      return null;
   }

   public Object getKey()
   {
      return null;
   }
}
