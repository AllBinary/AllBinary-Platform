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
package allbinary.business.context.configuration;

import allbinary.data.file.DataFileData;

public class ContextConfigurationData
{
	private static final ContextConfigurationData instance = new ContextConfigurationData();

	   public static ContextConfigurationData getInstance() {
			return instance;
		}
	
   private ContextConfigurationData()
   {
   }

public final String NAME = "CONTEXT_CONFIGURATION_NAME";
   
   public final String UNCRYPTED_EXTENSION = DataFileData.UNCRYPTED_EXTENSION;
   public final String ENCRYPTED_EXTENSION = DataFileData.ENCRYPTED_EXTENSION;
}
