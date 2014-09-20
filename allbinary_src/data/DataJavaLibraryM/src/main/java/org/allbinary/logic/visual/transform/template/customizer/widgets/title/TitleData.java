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
package org.allbinary.logic.visual.transform.template.customizer.widgets.title;

public class TitleData
{
	private static final TitleData instance = new TitleData();
	
	   public static TitleData getInstance() {
			return instance;
		}
	
   private TitleData()
   {
   }
   
public final String NAME = "TITLE_NAME";
   
   public final String TEXT = "TITLE_TEXT";
   
   public final String VIEWNAMEKEY = "Title";
   
   public final int MAXLEN = 255;
}
