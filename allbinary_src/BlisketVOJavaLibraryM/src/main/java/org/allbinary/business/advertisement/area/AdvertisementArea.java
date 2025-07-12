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

import java.util.HashMap;

import org.allbinary.business.advertisement.campaign.AdvertisementCampaignData;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.w3c.dom.Document;

public class AdvertisementArea implements AdvertisementAreaInterface
{
   private String name;
   private String storeName;
   private String description;
   private AdvertisementAreaConstraintsInterface advertisementAreaConstraintsInterface;
   
   //Ad campaign associated with the ad area
   private String campaign;
   
   public AdvertisementArea(HashMap hashMap) throws Exception
   {
      this.name = (String) hashMap.get(AdvertisementAreaData.getInstance().NAME);
      this.storeName = (String) hashMap.get(StoreFrontData.getInstance().NAME);
      this.description = (String) hashMap.get(AdvertisementAreaData.getInstance().DESCRIPTION);

      String constraintsDomString = 
         (String) hashMap.get(AdvertisementAreaData.getInstance().CONSTRAINTS);
      Document document = DomDocumentHelper.create(constraintsDomString);

      this.advertisementAreaConstraintsInterface
         = new AdvertisementAreaConstraints(document);

      this.campaign = (String) hashMap.get(AdvertisementCampaignData.getInstance().NAME);
   }

   public String getName()
   {
      return this.name;
   }
   
   public String getStoreName()
   {
      return this.storeName;
   }

   public String getDescription()
   {
      return this.description;
   }

   public AdvertisementAreaConstraintsInterface getConstraints()
   {
      return this.advertisementAreaConstraintsInterface;
   }
   
   public String getCampaign()
   {
      return this.campaign;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public void setStoreName(String storeName)
   {
      this.storeName = storeName;
   }
   
   public void setDescription(String description)
   {
      this.description = description;
   }

   public void setConstraints(
      AdvertisementAreaConstraintsInterface advertisementConstraintsInterface)
   {
      this.advertisementAreaConstraintsInterface = 
         advertisementAreaConstraintsInterface;
   }

   public void setCampaign(String campaign)
   {
      this.campaign = campaign;
   }
}
