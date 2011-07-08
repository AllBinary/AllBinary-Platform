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
package allbinary.business.context.modules.storefront.statistics;

import allbinary.business.context.modules.storefront.statistics.advertisements.StoreFrontAdvertisementsStatisticsInterface;
import allbinary.business.context.modules.storefront.statistics.inventory.StoreFrontInventoryStatisticsInterface;
import allbinary.business.context.modules.storefront.statistics.orders.history.StoreFrontOrdersHistoryStatisticsInterface;
import allbinary.business.context.modules.storefront.statistics.users.StoreFrontUsersStatisticsInterface;

public interface StoreFrontStatisticsInterface
{
   public StoreFrontAdvertisementsStatisticsInterface getAdvertisements();
   public StoreFrontUsersStatisticsInterface getUsers();
   public StoreFrontInventoryStatisticsInterface getInventory();
   public StoreFrontOrdersHistoryStatisticsInterface getOrders();
}
