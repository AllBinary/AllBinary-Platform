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

import java.util.HashMap;

import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.address.ShippingAddressData;
import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.business.user.address.StreetAddressData;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;
import org.allbinary.business.user.commerce.money.MoneyException;
import org.allbinary.business.user.commerce.shipping.ShippingMethodData;
import org.allbinary.logic.string.StringUtil;

public class Item extends BasicItem implements OrderItemInterface
{
   private String group;
   private String tax;
   private String shippingType;
   private String shippingCost;
   private String shippedDate;
   private StreetAddress shippingAddress;
   private String special;

   public Item(HashMap itemHashMap) throws MoneyException 
   {
      super(itemHashMap);
      this.setData(itemHashMap);
   }
   
   public Item() throws Exception
   {
       final String EMPTY = StringUtil.getInstance().EMPTY_STRING;

      group = EMPTY;
      tax = EMPTY;
      shippingType = EMPTY;
      shippingCost = EMPTY;
      shippingAddress = new StreetAddress();   
   }

   private void setData(HashMap itemHashMap)
   {
      this.group = (String) itemHashMap.get(ShippingMethodData.GROUP);
      this.tax = (String) itemHashMap.get(OrderHistoryData.TAX);

      this.shippingType = (String) itemHashMap.get(ShippingMethodData.NAME);
      this.shippingCost = (String) itemHashMap.get(ShippingMethodData.COST);
      this.shippedDate = (String) itemHashMap.get(OrderHistoryData.SHIPPEDDATE);
            
      itemHashMap.put(StreetAddressData.NAME,itemHashMap.get(ShippingAddressData.NAME));
      itemHashMap.put(StreetAddressData.STREET,itemHashMap.get(ShippingAddressData.STREET));
      itemHashMap.put(StreetAddressData.CITY,itemHashMap.get(ShippingAddressData.CITY));
      itemHashMap.put(StreetAddressData.STATE,itemHashMap.get(ShippingAddressData.STATE));
      itemHashMap.put(StreetAddressData.CODE,itemHashMap.get(ShippingAddressData.CODE));
      itemHashMap.put(StreetAddressData.COUNTRY,itemHashMap.get(ShippingAddressData.COUNTRY));
      
      this.shippingAddress = new StreetAddress(itemHashMap);
   }
   
   public void setGroup(String value)
   {
      this.group = value;
   }

   public void setTax(String value)
   {
      this.tax = value;
   }

   public void setShippingType(String value)
   {
      this.shippingType = value;
   }
   public void setShippingCost(String value)
   {
      this.shippingCost = value;
   }
   public void setShippingAddress(StreetAddress value)
   {
      this.shippingAddress = value;
   }

   public void setSpecial(String value)
   {
      this.special = value;
   }      

   public String getGroup()
   {
      return this.group;
   }

   public String getTax()
   {
      return this.tax;
   }

   public String getShippingType()
   {
      return this.shippingType;
   }
   public String getShippingCost()
   {
      return this.shippingCost;
   }
   public StreetAddress getShippingAddress()
   {
      return this.shippingAddress;
   }

   public String getSpecial()
   {
      return this.special;
   }

   public HashMap toHashMap()
   {
      HashMap hashMap = super.toHashMap();
      
      hashMap.put(ShippingMethodData.GROUP,this.group);
      hashMap.put(OrderHistoryData.TAX,this.tax);
      hashMap.put(ShippingMethodData.NAME,this.shippingType);
      hashMap.put(ShippingMethodData.COST,this.shippingCost);
      hashMap.put(OrderHistoryData.SHIPPEDDATE,this.shippedDate);
      hashMap.put(EntryData.getInstance().SPECIAL,this.special);

      return hashMap;
   }
   
}