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
package allbinary.logic.visual.media;

public class MediaTypeData
{
	private static final MediaTypeData instance = new MediaTypeData();

	   public static MediaTypeData getInstance() {
			return instance;
		}
	
   private MediaTypeData()
   {
   }

   //ImageIO supported image
   public final String RESIZABLE_MEDIA = "RESIZABLE_MEDIA";   
   
   public final String NOT_RESIZABLE_MEDIA = "NOT_RESIZABLE_MEDIA";
}
