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
package allbinary.business.context.modules.storefront.statistics.orders;


public class StoreFrontOrdersStatisticsData
{
   private static final StoreFrontOrdersStatisticsData instance = 
	   new StoreFrontOrdersStatisticsData();
   
   public static StoreFrontOrdersStatisticsData getInstance() {
		return instance;
	}
   
   private StoreFrontOrdersStatisticsData()
   {
   }
   
public final String NAME = "STOREFRONT_ORDERS_STATISTICS_NAME";
}
