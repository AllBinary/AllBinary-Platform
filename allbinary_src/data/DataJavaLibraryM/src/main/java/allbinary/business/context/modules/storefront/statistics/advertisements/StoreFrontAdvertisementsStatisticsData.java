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
package allbinary.business.context.modules.storefront.statistics.advertisements;

public class StoreFrontAdvertisementsStatisticsData
{
   private static final StoreFrontAdvertisementsStatisticsData instance =
	   new StoreFrontAdvertisementsStatisticsData();
   
   public static StoreFrontAdvertisementsStatisticsData getInstance() {
		return instance;
	}
   
   private StoreFrontAdvertisementsStatisticsData()
   {
   }
   
   public final String NAME = "STOREFRONT_ADVERTISEMENTS_STATISTICS_NAME";
}
