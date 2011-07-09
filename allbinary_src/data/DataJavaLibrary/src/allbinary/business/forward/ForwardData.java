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
package allbinary.business.forward;

public class ForwardData
{
	private static final ForwardData instance = new ForwardData();
	
	private ForwardData()
	{
		
	}
	
   public static ForwardData getInstance() {
		return instance;
	}

public final String PAGE = "FORWARD_PAGE";
   public final String NAME = "FORWARD_NAME";
   public final String VALUE = "FORWARD_VALUE";
}
