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
package org.allbinary.business.advertisement.campaign;

import java.util.HashMap;

import org.allbinary.business.DynamicObjectData;
import org.allbinary.business.advertisement.AdvertisementsInterface;
import org.allbinary.business.advertisement.search.AdvertisementSearchInterface;

public class AdvertisementCampaign implements AdvertisementCampaignInterface
{
   private HashMap hashMap;
   
   public AdvertisementCampaign(HashMap hashMap)
   {
      this.hashMap = hashMap;
   }

   public Object getComponent()
   {
      //return (Object) this.hashMap.get(AdvertisementCampaignData.NAME);
      return null;
   }
   
   public String getName()
   {
      return (String) this.hashMap.get(AdvertisementCampaignData.getInstance().NAME);
   }
   
   public void setComponentName(String name)
   {
      this.hashMap.put(DynamicObjectData.NAME, name);
   }
   
   public void setName(String name)
   {
      this.hashMap.put(AdvertisementCampaignData.getInstance().NAME, name);
   }
   
   //Will Retrieve the appropriate advertisment from the selected campaigns/advertisementPrograms
   public AdvertisementsInterface search(
      AdvertisementSearchInterface advertisementSearchInterface)
   {
      return null;
   }
}
