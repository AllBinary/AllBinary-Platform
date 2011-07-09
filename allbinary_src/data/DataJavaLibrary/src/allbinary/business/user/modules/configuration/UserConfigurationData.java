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
package allbinary.business.user.modules.configuration;

import allbinary.data.file.DataFileData;

public class UserConfigurationData
{
   private UserConfigurationData()
   {
   }
   
   public static final String NAME = "USER_CONFIGURATION_NAME";
   
   public static final String UNCRYPTED_EXTENSION = DataFileData.UNCRYPTED_EXTENSION;
   public static final String ENCRYPTED_EXTENSION = DataFileData.ENCRYPTED_EXTENSION;
}
