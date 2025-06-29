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
package org.allbinary.logic.visual.dhtml.html.table;

import org.allbinary.string.CommonStrings;

public class HtmlTableData {

	private static final HtmlTableData instance = new HtmlTableData();

	public static HtmlTableData getInstance() {
		return instance;
	}

	   public final String LEFT = CommonStrings.getInstance().LEFT;
	   public final String RIGHT = CommonStrings.getInstance().RIGHT;
	   
	   public final String NONE = "none";
	   public final String TOP = "top";
	   public final String BOTTOM = "bottom";
	   public final String TOPBOT = "topbot";
	   public final String SIDES = "sides";
	   public final String ALL = "all";
	   public final String BORDERED = "border";
	   
	   public final String BASIC = "basic";
	   public final String ROWS = "rows";      
	   
	   public final String[] FRAMES =
	   {NONE,TOP,BOTTOM,TOPBOT,SIDES,ALL,BORDERED};
}
