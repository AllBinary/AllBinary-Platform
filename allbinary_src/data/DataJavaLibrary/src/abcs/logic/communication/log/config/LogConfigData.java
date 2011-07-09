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
package abcs.logic.communication.log.config;

public class LogConfigData
{
	private static final LogConfigData instance = new LogConfigData();
	
	   public static LogConfigData getInstance() {
			return instance;
		}
	
	private LogConfigData()
	{
		
	}
	
   public final String NAME = "LOG_CONFIG_NAME";
   public final String DESCRIPTION = "LOG_CONFIG_DESCRIPTION";
   //public final String PATH = "LOG_CONFIG_PATH";
   public final String FILE = "LOG_CONFIG_FILE";
}
