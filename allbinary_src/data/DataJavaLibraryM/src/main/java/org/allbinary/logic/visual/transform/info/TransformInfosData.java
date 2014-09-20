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

public class TransformInfosData
{
	private static final TransformInfosData instance = new TransformInfosData();
	
   private TransformInfosData()
   {
   }

   public static TransformInfosData getInstance() {
	return instance;
}

public final String NAME = "TRANSFORM_INFOS_NAME";
   
   //The group key specifies a specific group of componenets to build from a
   //shared objectconfig and used in the view name - 
   //This is use for template generator views with shared object configs to 
   //reduce the amount of object config data - A developer could just use
   //different object configs for a template view that would probably have 
   //duplicate view components
   public final String GROUP = "TRANSFORM_INFOS_GROUP";

   public final String ALL = "TRANSFORM_INFOS_ALL";
   public final String PREVIEW = "TRANSFORM_INFOS_PREVIEW";
   public final String SMALL_PREVIEW = "TRANSFORM_INFOS_SMALL_PREVIEW";

}
