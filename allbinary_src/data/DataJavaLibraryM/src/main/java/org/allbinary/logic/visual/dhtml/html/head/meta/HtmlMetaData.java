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
package org.allbinary.logic.visual.dhtml.html.head.meta;

public class HtmlMetaData
{
	private static final HtmlMetaData instance = new HtmlMetaData();
	
   private HtmlMetaData()
   {
   }

   public static HtmlMetaData getInstance() {
	return instance;
}

public String NAME = "HTMLMETA_NAME";

   public String LABEL = "HTMLMETA_LABEL";

   public String VALUE = "meta";
}
