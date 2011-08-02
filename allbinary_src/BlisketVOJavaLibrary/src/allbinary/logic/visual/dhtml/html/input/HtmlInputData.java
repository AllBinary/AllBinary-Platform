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
package allbinary.logic.visual.dhtml.html.input;

public class HtmlInputData {

	private static final HtmlInputData instance = new HtmlInputData();
	
	public static HtmlInputData getInstance() {
		return instance;
	}
	
	   public final String TEXT = "text";
	   public final String RADIO = "radio";
	   public final String CHECKBOX = "checkbox";
	   public final String HIDDEN = "hidden";
	   public final String IMAGE = "image";
	   public final String PASSWORD = "password";
	   public final String RESET = "reset";
	   public final String SUBMIT = "submit";
	   
	   public final String TYPES[]
	   =
	   {TEXT,RADIO,CHECKBOX,HIDDEN,IMAGE,
	    PASSWORD,RADIO,RESET,SUBMIT};	
}
