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

public class StoreFrontInventoryStatisticsData
{
   private static final StoreFrontInventoryStatisticsData instance = 
	   new StoreFrontInventoryStatisticsData();

   public static StoreFrontInventoryStatisticsData getInstance() {
		return instance;
	}
   
   private StoreFrontInventoryStatisticsData()
   {
   }

   public final String NAME = "STOREFRONT_INVENTORY_STATISTICS_NAME";
   public final String NUMBEROFITEMS = "STOREFRONT_INVENTORY_STATISTICS_NUMBER_OF_ITEMS";
   public final String TOTALVALUE = "STOREFRONT_INVENTORY_STATISTICS_TOTAL_VALUE";
}
