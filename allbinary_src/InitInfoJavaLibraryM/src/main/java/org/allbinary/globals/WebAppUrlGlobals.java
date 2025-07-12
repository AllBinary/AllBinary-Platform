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

import org.allbinary.business.init.InitInfo;
import org.allbinary.logic.string.StringValidationUtil;

public class WebAppUrlGlobals extends UrlGlobalsInterface
{
   private String path;
   private boolean isWebappPathSet = false;
   
   public boolean isTestingMode()
   {
      return InitInfo.getInstance().isTesting();
   }
   
   public String getWebappPath()
   {
      return path;
   }

   public synchronized void setWebappPath(String path)
   {
      path = path;
      isWebappPathSet = true;
   }  
   
   public String getTestHtmlPath()
   {
      return InitInfo.getInstance().getTestHtmlPath();
   }
      
   public String getMainPath() throws Exception
   {
      String mainPath = InitInfo.getInstance().getMainPath();
      if(!StringValidationUtil.getInstance().isEmpty(mainPath))
      {
         return mainPath;
      }
      else
      if(isWebappPathSet)
      {
         return getWebappPath(); 
      }
      else
      {
         throw new Exception("Webapp Path is not set");
      }
   }   
}
