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
package allbinary.logic.visual.dhtml.html.head.meta;

public class HtmlMetaAttributeValuesData
{
	private static final HtmlMetaAttributeValuesData instance = 
		new HtmlMetaAttributeValuesData();

	   public static HtmlMetaAttributeValuesData getInstance() {
			return instance;
		}
	
   private HtmlMetaAttributeValuesData()
   {
   }

public String ABSTRACT = "abstract";
   public String DESCRIPTION = "description";
   public String KEYWORDS = "keywords";
}
