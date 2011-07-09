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
package allbinary.business.time;

public class TimeData
{
	private static final TimeData instance = new TimeData();

	   public static TimeData getInstance() {
			return instance;
		}
	
   private TimeData()
   {
   }

public final String NAME = "TIME_NAME";

   public final String START = "TIME_START";
   public final String END = "TIME_END";
}