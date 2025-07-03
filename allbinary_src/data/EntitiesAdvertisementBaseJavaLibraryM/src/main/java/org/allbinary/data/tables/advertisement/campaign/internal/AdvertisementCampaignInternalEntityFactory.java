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
package org.allbinary.data.tables.advertisement.campaign.internal;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class AdvertisementCampaignInternalEntityFactory
{
   private static final String CLASSNAME = "org.allbinary.data.tables.AdvertisementCampaignEntity";
   
   private AdvertisementCampaignInternalEntityFactory()
   {
   }
   
   public static AdvertisementCampaignInternalEntityInterface getInstance() //throws LicensingException
   {
      try
      {
         return (AdvertisementCampaignInternalEntityInterface) new AdvertisementCampaignInternalEntity();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, "AdvertisementCampaignEntityFactory", commonStrings.GET_INSTANCE, e));
         }
         return null;
      }   
   }
}
