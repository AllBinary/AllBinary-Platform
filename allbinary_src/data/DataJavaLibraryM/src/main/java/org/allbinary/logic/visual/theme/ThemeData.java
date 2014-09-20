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
package org.allbinary.logic.visual.theme;

public class ThemeData
{
	private static final ThemeData instance = new ThemeData();
	
	   public static ThemeData getInstance() {
			return instance;
		}
	
   private ThemeData()
   {
   }

   public final String NAME = "THEME_NAME";
   public final String PATH = "THEME_PATH";
   public final String PREVIEW_IMAGE_NAME = "THEME_PREVIEW_IMAGE_NAME";
   public final String PREVIEW_IMAGE_PATH = "THEME_PREVIEW_IMAGE_PATH";
}
