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
package org.allbinary.globals;

import org.allbinary.logic.string.StringUtil;

//implements
public class AppUrlGlobals extends UrlGlobalsInterface
{
    public static final AppUrlGlobals NULL_APP_URL_GLOBALS = new AppUrlGlobals();
    
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private String path = StringUtil.getInstance().EMPTY_STRING;
   
   @Override
   public boolean isTestingMode()
   {
      return false;
   }
   
   @Override
   public String getWebappPath()
   {
      return this.path;
   }

   public synchronized void setWebappPath(String path)
   {
      this.path = path;

      if(!(this.path.endsWith("\\") || this.path.endsWith("/")))
      {
          this.path = this.path + "\\";
      }
      
      //PreLogUtil.put("Webapp Path: " + this.path, this, "setWebappPath");
   }  
   
   @Override
   public String getTestHtmlPath()
   {
      return StringUtil.getInstance().EMPTY_STRING;
   }
      
   @Override
   public String getMainPath() throws Exception
   {
      return this.path;
   }
}
