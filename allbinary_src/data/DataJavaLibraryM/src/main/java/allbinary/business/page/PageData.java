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
package allbinary.business.page;

public class PageData
{
	private static final PageData instance = new PageData();
	
	   public static PageData getInstance() {
			return instance;
		}
	
   private PageData()
   {
   }
   
public final String NAME = "PAGE_NAME";
}
