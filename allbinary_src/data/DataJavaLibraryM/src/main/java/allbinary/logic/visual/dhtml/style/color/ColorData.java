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
package allbinary.logic.visual.dhtml.style.color;

public class ColorData
{
	private static final ColorData instance = new ColorData();
	
	   public static ColorData getInstance() {
			return instance;
		}
	
   private ColorData()
   {
   }
   
   public final String NAME = "COLOR_NAME";
}
