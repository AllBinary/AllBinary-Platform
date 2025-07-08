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
import org.allbinary.logic.communication.log.PreLogUtil;

//implements
public class AppUrlGlobals extends UrlGlobalsInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private String path;
   
   public boolean isTestingMode()
   {
      return false;
   }
   
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
   
   public String getTestHtmlPath()
   {
      return StringUtil.getInstance().EMPTY_STRING;
   }
      
   public String getMainPath() throws Exception
   {
      return this.path;
   }
}
