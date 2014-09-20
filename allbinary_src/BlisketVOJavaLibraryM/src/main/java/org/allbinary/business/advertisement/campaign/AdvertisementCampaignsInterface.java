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

import org.allbinary.business.advertisement.AdvertisementInterface;

public interface AdvertisementCampaignsInterface
{
   public void add(AdvertisementInterface advertisementInterface);
   public AdvertisementInterface get(int index);
   
   public int size();
   public boolean isEnabled();

}
