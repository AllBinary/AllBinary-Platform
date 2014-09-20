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
package org.allbinary.logic.visual.transform.info.viewObject;

public class TransformInfoObjectData
{
	private static final TransformInfoObjectData instance = new TransformInfoObjectData();

	   public static TransformInfoObjectData getInstance() {
			return instance;
	}
	
   private TransformInfoObjectData()
   {
   }
   
   //public final String FILENAME = "TRANSFORM_INFO_OBJECTFILENAME";
   //public final String NAME = "TRANSFORM_INFO_OBJECT";

public final String UNCRYPTED_EXTENSION = "class";
   public final String ENCRYPTED_EXTENSION = "abw";
}