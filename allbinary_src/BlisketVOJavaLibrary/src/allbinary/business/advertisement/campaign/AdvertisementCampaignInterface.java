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

import allbinary.business.advertisement.AdvertisementsInterface;
import allbinary.business.advertisement.search.AdvertisementSearchInterface;

public interface AdvertisementCampaignInterface
{
   //AffiliateData.NAME
   public String getName();
   //DynamicObjectData.NAME
   public Object getComponent();
   
   public void setName(String name);
   //DynamicObjectData.NAME
   public void setComponentName(String name);

   public AdvertisementsInterface search(
      AdvertisementSearchInterface advertisementSearchInterface);
}
