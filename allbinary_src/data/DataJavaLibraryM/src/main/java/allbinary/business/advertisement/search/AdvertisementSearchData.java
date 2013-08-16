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
package allbinary.business.advertisement.search;

public class AdvertisementSearchData
{
	private static final AdvertisementSearchData instance = new AdvertisementSearchData();

	   public static AdvertisementSearchData getInstance() {
			return instance;
		}
	
   private AdvertisementSearchData()
   {
   }

   public final String TITLE = "AFFILIATE_TITLE";
   public final String PRICE = "AFFILIATE_PRICE";
   public final String RELEVANCY = "AFFILIATE_RELEVANCY";
   public final String POPULARITY = "AFFILIATE_POPULARITY";
   
   //public final String = "AFFILIATE_";
}
