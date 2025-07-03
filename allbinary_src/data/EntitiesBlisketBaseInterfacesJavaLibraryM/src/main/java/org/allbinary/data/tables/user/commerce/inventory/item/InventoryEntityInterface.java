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
package org.allbinary.data.tables.user.commerce.inventory.item;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.business.user.commerce.money.MoneyException;

import java.util.HashMap;
import java.util.Vector;
import org.allbinary.data.tables.BasicDataTableInterface;

public interface InventoryEntityInterface extends BasicDataTableInterface
{      
   public ItemInterface getItem(String id) throws MoneyException;
   public Vector getItems(StoreFrontInterface storeFrontInterface) throws Exception;
   
   public String getWeight(String id);
   
   //public String getItemForm(String id);
   //public Vector getColumnWhereLike(String columnName,String key,String value);

   public void deleteWhere(String key,String value);
   
   public void insert(Vector values);

   public void update(HashMap updatedValues);
}
