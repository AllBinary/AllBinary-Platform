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
package allbinary.business.error;

public class ErrorData
{
	private static final ErrorData instance = new ErrorData();

	   public static ErrorData getInstance() {
			return instance;
		}
	
   private ErrorData()
   {
   }

public final String NAME = "ERROR_NAME";
   public final String TEXT = "ERROR_TEXT";
}
