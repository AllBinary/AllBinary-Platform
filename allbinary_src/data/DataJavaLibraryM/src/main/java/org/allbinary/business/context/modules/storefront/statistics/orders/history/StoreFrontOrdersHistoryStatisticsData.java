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


public class StoreFrontOrdersHistoryStatisticsData
{
   private static final StoreFrontOrdersHistoryStatisticsData instance = 
	   new StoreFrontOrdersHistoryStatisticsData();
   
   public static StoreFrontOrdersHistoryStatisticsData getInstance() {
		return instance;
	}

   private StoreFrontOrdersHistoryStatisticsData()
   {
   }
   
   public final String NAME = "STOREFRONT_ORDERS_HISTORY_STATISTICS_NAME";
   public final String NUMBEROFORDERS = "STOREFRONT_ORDERS_HISTORY_STATISTICS_NUMBER_OF_ORDERS";
   public final String SUBTOTAL = "STOREFRONT_ORDERS_HISTORY_STATISTICS_SUBTOTAL";
   public final String SHIPPINGCOST = "STOREFRONT_ORDERS_HISTORY_STATISTICS_SHIPPINGCOST";
   public final String TAXES = "STOREFRONT_ORDERS_HISTORY_STATISTICS_TAXES";
   public final String TOTAL = "STOREFRONT_ORDERS_HISTORY_STATISTICS_TOTAL";
}
