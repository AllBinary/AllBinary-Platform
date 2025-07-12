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
package org.allbinary.business.context.modules.storefront.statistics.orders.history;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryInterface;
import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntityFactory;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntityInterface;

//import org.allbinary.business.context.modules.storefront.statistics.orders.history.*;

public class StoreFrontOrdersHistoryStatistics
    implements StoreFrontOrdersHistoryStatisticsInterface
{
   private Long numberOfOrdersLong;
   private Money subTotalMoney;
   private Money shippingCostMoney;
   private Money taxesMoney;
   private Money totalMoney;
      
   public StoreFrontOrdersHistoryStatistics(StoreFrontInterface storeFrontInterface) throws Exception
   {
      this.subTotalMoney = new Money();
      this.shippingCostMoney = new Money();
      this.taxesMoney = new Money();
      this.totalMoney = new Money();
   
      final OrderHistoryEntityInterface orderHistoryEntityInterface = OrderHistoryEntityFactory.getInstance();
      final Vector orderHistoryInterfaceVector = orderHistoryEntityInterface.getStoreOrders(storeFrontInterface);
      
      long numberOfOrders = 0;
      OrderHistoryInterface orderHistoryInterface;
      
      final int size = orderHistoryInterfaceVector.size();
      for (int index = 0; index < size; index++)      
      {
         orderHistoryInterface = (OrderHistoryInterface) orderHistoryInterfaceVector.get(index);

	 numberOfOrders = numberOfOrders + 1;
	 
         this.subTotalMoney.add(orderHistoryInterface.getSubTotal());
         this.shippingCostMoney.add(orderHistoryInterface.getShippingCost());
         this.taxesMoney.add(orderHistoryInterface.getTaxes());
         this.totalMoney.add(orderHistoryInterface.getTotal());
      }
      
      this.numberOfOrdersLong = new Long(numberOfOrders);
   }

   public Long getNumberOfOrders()
   {
      return this.numberOfOrdersLong;
   }

   public Money getSubTotal()
   {
      return this.subTotalMoney;
   }

   public Money getShippingCost()
   {
      return this.shippingCostMoney;
   }
   
   public Money getTaxes()
   {
      return this.taxesMoney;
   }
   
   public Money getTotal()
   {
      return this.totalMoney;
   }

   /*
   public Long getNumberOfItemsSold()
   {
      return this.numberOfItemsSold;
   }
   */
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      
      StoreFrontOrdersHistoryStatisticsData storeFrontOrdersHistoryStatisticsData =
    	  StoreFrontOrdersHistoryStatisticsData.getInstance();
      
      hashMap.put(storeFrontOrdersHistoryStatisticsData.NUMBEROFORDERS, this.getNumberOfOrders().toString());
      hashMap.put(storeFrontOrdersHistoryStatisticsData.SUBTOTAL, this.getSubTotal().toString());
      hashMap.put(storeFrontOrdersHistoryStatisticsData.SHIPPINGCOST, this.getShippingCost().toString());
      hashMap.put(storeFrontOrdersHistoryStatisticsData.TAXES, this.getTaxes().toString());
      hashMap.put(storeFrontOrdersHistoryStatisticsData.TOTAL, this.getTotal().toString());
      
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