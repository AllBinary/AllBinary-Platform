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
package org.allbinary.logic.io;

public class InputOutputTypeData
{
	private static final InputOutputTypeData instance = new InputOutputTypeData();
	
	   public static InputOutputTypeData getInstance() {
			return instance;
		}
	
   private InputOutputTypeData()
   {
   }

   //Used in tag properties to specify that an update or insertion should 
   //use filenames or use a filename to get file and insert it
   //Use File for testing purposes or DB blob limitations
   
   public final String NAME = "INPUT_OUTPUT_TYPE_NAME";
   
   public final String FILE = "INPUT_OUTPUT_TYPE_FILE";

   public final String RESPONSE = "INPUT_OUTPUT_TYPE_RESPONSE";
   public final String DB = "INPUT_OUTPUT_TYPE_DB";

   public final String JSP = "jsp";
   public final String JSP_FRAGMENT = "jspf";
   public final String PHP = "php";
   public final String ASP = "asp";
   public final String PL = "pl";

   public final String DEFAULT = JSP;
   public final String DEFAULT_FRAGMENT = JSP_FRAGMENT;
}
