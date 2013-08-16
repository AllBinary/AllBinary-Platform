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

public class AdvertisementCampaignData
{
	private static final AdvertisementCampaignData instance = new AdvertisementCampaignData();

	   public static AdvertisementCampaignData getInstance() {
			return instance;
		}
	
   private AdvertisementCampaignData()
   {
   }

public final String NAME = "ADVERTISEMENTCAMPAIGN_NAME";
   public final String DESCRIPTION = "ADVERTISEMENTCAMPAIGN_DESCRIPTION";
   public final String CONFIG = "ADVERTISEMENTCAMPAIGN_CONFIG";
}