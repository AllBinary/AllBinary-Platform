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
package abcs.globals;

import abcs.logic.basic.string.StringUtil;

//implements
public class AppUrlGlobals extends UrlGlobalsInterface
{
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
