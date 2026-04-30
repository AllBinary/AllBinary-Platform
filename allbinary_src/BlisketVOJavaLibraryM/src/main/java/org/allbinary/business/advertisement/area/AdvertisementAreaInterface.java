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
package org.allbinary.business.advertisement.area;

public interface AdvertisementAreaInterface
{
   String getName();
   String getStoreName();
   String getDescription();
   
   AdvertisementAreaConstraintsInterface getConstraints();
   
   String getCampaign();
   
   void setName(String name);
   void setStoreName(String storeName);
   void setDescription(String description);
   
   void setConstraints(
      AdvertisementAreaConstraintsInterface advertisementConstraintsInterface);
   
   void setCampaign(String campaign);
}
