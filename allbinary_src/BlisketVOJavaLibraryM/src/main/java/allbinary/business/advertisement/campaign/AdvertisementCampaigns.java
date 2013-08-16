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
package allbinary.business.advertisement.campaign;

import java.util.Vector;

import allbinary.business.advertisement.AdvertisementInterface;
import allbinary.business.advertisement.AdvertisementsInterface;
import allbinary.business.advertisement.search.AdvertisementSearchInterface;

public class AdvertisementCampaigns implements AdvertisementCampaignsInterface
{
   private Vector advertisementsVector;
   
   public AdvertisementCampaigns(Vector vector)
   {
      this.advertisementsVector = advertisementsVector;
   }
   
   public void add(AdvertisementInterface advertisementInterface)
   {
      this.advertisementsVector.add(advertisementInterface);
   }
   
   public AdvertisementInterface get(int index)
   {
      return (AdvertisementInterface) this.advertisementsVector.get(index);
   }

   public int size()
   {
      return this.advertisementsVector.size();
   }
   
   public boolean isEnabled()
   {
      return true;
   }
   
   public AdvertisementsInterface search(
      AdvertisementSearchInterface advertisementSearchInterface)
   {
      return null;
   }
}
