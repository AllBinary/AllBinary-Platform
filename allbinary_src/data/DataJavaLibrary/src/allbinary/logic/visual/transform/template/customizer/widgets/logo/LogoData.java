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
package allbinary.logic.visual.transform.template.customizer.widgets.logo;

public class LogoData
{
	private static final LogoData instance = new LogoData();

	   public static LogoData getInstance() {
			return instance;
		}
	
   private LogoData()
   {
   }

public final String NAME = "LOGO_NAME";

   public final String IMAGEPATH = "LOGO_IMAGE_PATH";
   public final String IMAGEFILENAME = "LOGO_IMAGE_FILE_NAME";

   public final String IMAGE = "LOGO_IMAGE";   
}
