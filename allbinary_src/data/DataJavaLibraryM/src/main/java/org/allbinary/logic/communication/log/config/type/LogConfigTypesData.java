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

public class LogConfigTypesData
{
	private static final LogConfigTypesData instance = new LogConfigTypesData();

	public static LogConfigTypesData getInstance() {
		return instance;
	}
	
   public final String NAME = "LOG_CONFIG_TYPES_NAME";
   public final String DESCRIPTION = "LOG_CONFIG_TYPES_DESCRIPTION";
}
