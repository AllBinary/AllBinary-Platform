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

import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.context.modules.storefront.statistics.advertisements.StoreFrontAdvertisementsStatistics;
import allbinary.business.context.modules.storefront.statistics.advertisements.StoreFrontAdvertisementsStatisticsInterface;
import allbinary.business.context.modules.storefront.statistics.inventory.StoreFrontInventoryStatistics;
import allbinary.business.context.modules.storefront.statistics.inventory.StoreFrontInventoryStatisticsInterface;
import allbinary.business.context.modules.storefront.statistics.orders.history.StoreFrontOrdersHistoryStatistics;
import allbinary.business.context.modules.storefront.statistics.orders.history.StoreFrontOrdersHistoryStatisticsInterface;
import allbinary.business.context.modules.storefront.statistics.users.StoreFrontUsersStatistics;
import allbinary.business.context.modules.storefront.statistics.users.StoreFrontUsersStatisticsInterface;

public class RealTimeStoreFrontStatistics implements StoreFrontStatisticsInterface
{
   private StoreFrontAdvertisementsStatisticsInterface storeFrontAdvertisementsStatisticsInterface;
   private StoreFrontUsersStatisticsInterface storeFrontUsersStatisticsInterface;
   private StoreFrontInventoryStatisticsInterface storeFrontInventoryStatisticsInterface;
   private StoreFrontOrdersHistoryStatisticsInterface storeFrontOrderHistoryStatisticsInterface;
   
   public RealTimeStoreFrontStatistics(StoreFrontInterface storeFrontInterface) throws Exception
   {
      this.storeFrontAdvertisementsStatisticsInterface = 
         (StoreFrontAdvertisementsStatisticsInterface) 
            new StoreFrontAdvertisementsStatistics(storeFrontInterface);

      this.storeFrontUsersStatisticsInterface = 
         (StoreFrontUsersStatisticsInterface) 
            new StoreFrontUsersStatistics(storeFrontInterface);

      this.storeFrontInventoryStatisticsInterface = 
         (StoreFrontInventoryStatisticsInterface) 
            new StoreFrontInventoryStatistics(storeFrontInterface);
      
      this.storeFrontOrderHistoryStatisticsInterface = 
         (StoreFrontOrdersHistoryStatisticsInterface) 
            new StoreFrontOrdersHistoryStatistics(storeFrontInterface);
   }
    
   public StoreFrontAdvertisementsStatisticsInterface getAdvertisements()
   {
      return this.storeFrontAdvertisementsStatisticsInterface;
   }

   public StoreFrontUsersStatisticsInterface getUsers()
   {
      return this.storeFrontUsersStatisticsInterface;
   }

   public StoreFrontInventoryStatisticsInterface getInventory()
   {
      return this.storeFrontInventoryStatisticsInterface;
   }

   public StoreFrontOrdersHistoryStatisticsInterface getOrders()
   {
      return this.storeFrontOrderHistoryStatisticsInterface;
   }   
}
