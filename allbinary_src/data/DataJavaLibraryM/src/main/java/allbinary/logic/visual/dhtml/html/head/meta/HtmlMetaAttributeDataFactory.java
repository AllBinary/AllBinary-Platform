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

public class HtmlMetaAttributeDataFactory {

	private static final HtmlMetaAttributeDataFactory instance = 
		new HtmlMetaAttributeDataFactory();
	
	   public static HtmlMetaAttributeDataFactory getInstance() {
		return instance;
	}
	
	private HtmlMetaAttributeDataFactory()
	{
		
	}

	public HtmlMetaAttributeData NAME = 
		      new HtmlMetaAttributeData(0,"HTMLMETA_ATTRIBUTE_NAME");
		   public HtmlMetaAttributeData HTTP_EQUIV = 
		      new HtmlMetaAttributeData(1,"HTMLMETA_ATTRIBUTE_HTTPEQUIV");
		   public HtmlMetaAttributeData CONTENT = 
		      new HtmlMetaAttributeData(2,"HTMLMETA_ATTRIBUTE_CONTENT");
}
