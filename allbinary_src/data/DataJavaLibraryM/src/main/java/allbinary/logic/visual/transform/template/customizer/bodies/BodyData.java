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
package allbinary.logic.visual.transform.template.customizer.bodies;

public class BodyData
{
	private static final BodyData instance = new BodyData();
	
   private BodyData()
   {
   }
   
   public static BodyData getInstance() {
	return instance;
}

   public final String NAME = "BODY_NAME";
   
   public final String VIEWNAMEKEY = "Body";
   
   public final int MIN = 0;
}
