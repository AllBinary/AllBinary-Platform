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
package allbinary.logic.visual.transform.info.template;

public class TransformInfoTemplateData
{
	private static final TransformInfoTemplateData instance = new TransformInfoTemplateData();
	   
	public static TransformInfoTemplateData getInstance() {
			return instance;
		}
	
   private TransformInfoTemplateData()
   {
   }

public final String UNCRYPTED_EXTENSION = "xsl";
   public final String ENCRYPTED_EXTENSION = "abv";
}
