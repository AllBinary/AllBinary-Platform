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
package org.allbinary.business.context.modules.storefront.statistics.advertisements;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;

public class StoreFrontAdvertisementsStatistics
implements StoreFrontAdvertisementsStatisticsInterface
{
   //private long totalNumberOfItems;
   //private Money totalAdvertisementsSaleValue;
   
   public StoreFrontAdvertisementsStatistics(StoreFrontInterface storeFrontInterface) throws Exception
   {
      //AdvertisementsEntityInterface advertisementsEntityInterface = 
         //AdvertisementsEntityFactory.getInstance();
   }
 
   /*
   public long getNumberOfAdsShown()
   {
   }

   public Money getTotal()
   {
   }
    */
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      
      //hashMap.put();
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
