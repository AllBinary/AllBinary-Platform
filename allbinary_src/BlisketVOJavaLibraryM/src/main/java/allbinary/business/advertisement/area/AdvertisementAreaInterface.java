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
package allbinary.business.advertisement.area;

public interface AdvertisementAreaInterface
{
   public String getName();
   public String getStoreName();
   public String getDescription();
   
   public AdvertisementAreaConstraintsInterface getConstraints();
   
   public String getCampaign();
   
   public void setName(String name);
   public void setStoreName(String storeName);
   public void setDescription(String description);
   
   public void setConstraints(
      AdvertisementAreaConstraintsInterface advertisementConstraintsInterface);
   
   public void setCampaign(String campaign);
}
