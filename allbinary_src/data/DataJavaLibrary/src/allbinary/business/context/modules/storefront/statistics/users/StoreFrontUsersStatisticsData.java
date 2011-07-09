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
package allbinary.business.context.modules.storefront.statistics.users;

public class StoreFrontUsersStatisticsData
{
	private static final StoreFrontUsersStatisticsData instance = new StoreFrontUsersStatisticsData();
   
   private StoreFrontUsersStatisticsData()
   {
   }
   
   public static StoreFrontUsersStatisticsData getInstance() {
	return instance;
}

public final String NAME = "STOREFRONT_USERS_STATISTICS_NAME";
   public final String NUMBEROFUSERS = "STOREFRONT_USERS_STATISTICS_NUMBER_OF_USERS";
}
