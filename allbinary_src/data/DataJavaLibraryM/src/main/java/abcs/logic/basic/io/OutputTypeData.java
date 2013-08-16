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
package abcs.logic.basic.io;

public class OutputTypeData
{
	private static final OutputTypeData instance = new OutputTypeData();
	
   private OutputTypeData()
   {
   }
   
   //public static final String NAME = "TRANSFORM_INFOOBJECTCONFIGGENERATE_NAME";

   public static OutputTypeData getInstance() {
	return instance;
}

public final String NAME = "OUTPUT_TYPE_NAME";
}
