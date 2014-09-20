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
package org.allbinary.logic.visual.transform.info.objectConfig;

import org.allbinary.data.file.DataFileData;

public class TransformInfoObjectConfigData
{
	private static final TransformInfoObjectConfigData instance = new TransformInfoObjectConfigData();

	   public static TransformInfoObjectConfigData getInstance() {
		return instance;
	}

   private TransformInfoObjectConfigData()
   {
   }

public final String NAME = "OBJECTCONFIG_NAME";

   public final String VARKEY = "$";
   
   public final String UNCRYPTED_EXTENSION = DataFileData.UNCRYPTED_EXTENSION;
   public final String ENCRYPTED_EXTENSION = DataFileData.ENCRYPTED_EXTENSION;
}
