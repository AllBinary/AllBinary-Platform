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
package org.allbinary.business.context.modules.storefront;

public class StoreFrontsData
{
	private static final StoreFrontsData instance = new StoreFrontsData();

	public static StoreFrontsData getInstance() {
		return instance;
	}

   //StoreFront Commands
   public final String TYPE = "STOREFRONTS_TYPE";
   
   //Generic Store Types = Not required - Can use your own type
   public final String RETAIL = "STOREFRONTS_RETAIL";
   public final String SUBSCRIPTION = "STOREFRONTS_SUBSCRIPTION";
   public final String AUCTION = "STOREFRONTS_AUCTION";
   public final String NEWS = "STOREFRONTS_NEWS";
   public final String BLOG = "STOREFRONTS_BLOG";
   public final String FORUM = "STOREFRONTS_FORUM";
}
