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
package org.allbinary.logic.visual.dhtml.style.css.property.font;

public class FontFamilyData
{
	private static final FontFamilyData instance = new FontFamilyData();
	
	   public static FontFamilyData getInstance() {
			return instance;
		}
	
   private FontFamilyData()
   {
   }
   
   public final String NAME = "FONT_FAMILY_NAME";
   public final String VALUE = "font-family";

}
