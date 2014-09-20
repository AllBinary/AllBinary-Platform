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

public class AdvertisementAreaData
{
	private static final AdvertisementAreaData instance = new AdvertisementAreaData();

	   public static AdvertisementAreaData getInstance() {
			return instance;
		}
	
   private AdvertisementAreaData()
   {
   }

public final String NAME = "ADVERTISEMENT_NAME";
   public final String DESCRIPTION = "ADVERTISEMENT_DESCRIPTION";
   public final String CONSTRAINTS = "ADVERTISEMENT_CONSTRAINTS";
}
