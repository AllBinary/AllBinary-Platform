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
package org.allbinary.logic.communication.log.config.type;

public class LogConfigTypeData
{
   private static final LogConfigTypeData instance = new LogConfigTypeData();
   
   public static LogConfigTypeData getInstance() {
		return instance;
	}
   
   private LogConfigTypeData()
   {
	   
   }
   
   public final String NAME = "LOG_CONFIG_TYPE_NAME";
   public final String DESCRIPTION = "LOG_CONFIG_TYPE_DESCRIPTION";
}
