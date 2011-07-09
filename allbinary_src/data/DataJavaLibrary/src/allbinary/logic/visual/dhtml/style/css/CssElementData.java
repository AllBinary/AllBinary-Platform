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
package allbinary.logic.visual.dhtml.style.css;

public class CssElementData
{
	private static final CssElementData instance = new CssElementData();

	   public static CssElementData getInstance() {
			return instance;
		}
	
   private CssElementData()
   {
   }

   public final String NAME = "CSS_ELEMENT_NAME";
}
