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
package org.allbinary.logic.visual.transform.template;

public class XslData
{
	private final static XslData instance = new XslData();
	
	   public static XslData getInstance() {
			return instance;
		}
	
	   private XslData()
	   {
	   }
	
   public String NAME = "XSL_NAME";
   public String ROOT_IMPORT_URI = "ROOT_IMPORT_URI";
}
