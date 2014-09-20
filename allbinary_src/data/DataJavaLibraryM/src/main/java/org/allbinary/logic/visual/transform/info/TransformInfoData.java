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
package org.allbinary.logic.visual.transform.info;

public class TransformInfoData
{
	private static final TransformInfoData instance = new TransformInfoData();

	   public static TransformInfoData getInstance() {
			return instance;
		}
	
   private TransformInfoData()
   {
   }

public final String PARENT = "TRANSFORM_INFO_PARENT";
   public final String MAPPED = "TRANSFORM_INFO_MAPPED";
   
   public final String NAME = "TRANSFORM_INFO_NAME";
   public final String DESCRIPTION = "TRANSFORM_INFO_DESCRIPTION";
   public final String CATEGORY = "TRANSFORM_INFO_CATEGORY";

   public final String OBJECTFILENAME = "TRANSFORM_INFO_OBJECTFILENAME";
   public final String OBJECT = "TRANSFORM_INFO_OBJECT";

   public final String OBJECTCONFIGFILENAME = "TRANSFORM_INFO_OBJECTCONFIGFILENAME";
   public final String OBJECTCONFIG = "TRANSFORM_INFO_OBJECTCONFIG";

   public final String TEMPLATEFILENAME = "TRANSFORM_INFO_TEMPLATEFILENAME";
   public final String TEMPLATE = "TRANSFORM_INFO_TEMPLATE";

   public final String DATAFILENAME = "TRANSFORM_INFO_DATAFILENAME";
   public final String DATA = "TRANSFORM_INFO_DATA";
   
   public final String LABEL = "TRANSFORM_INFO_LABEL";

   //
   public final String OWNER = "OWNER_" + NAME;
   public final String PARTIAL = "PARTIAL_" + NAME;
}