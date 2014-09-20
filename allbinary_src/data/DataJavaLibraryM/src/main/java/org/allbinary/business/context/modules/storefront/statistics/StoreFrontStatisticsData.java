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
package org.allbinary.business.context.modules.storefront.statistics;

public class StoreFrontStatisticsData
{
	private static final StoreFrontStatisticsData instance = new StoreFrontStatisticsData();
	
	   public static StoreFrontStatisticsData getInstance() {
			return instance;
		}
   
   private StoreFrontStatisticsData()
   {
   }
   
public final String NAME = "STOREFRONT_STATISTICS_NAME";
   
}
