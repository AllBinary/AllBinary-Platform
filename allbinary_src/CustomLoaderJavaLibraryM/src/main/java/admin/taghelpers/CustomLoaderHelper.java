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
package admin.taghelpers;

import org.allbinary.globals.AppUrlGlobals;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogFactory;
import javax.servlet.jsp.PageContext;

import org.allbinary.logic.communication.log.LogUtil;
import java.util.HashMap;

public class CustomLoaderHelper
{
   public CustomLoaderHelper(HashMap hashMap, PageContext pageContext)
   {
   }

   public CustomLoaderHelper()
   {
   }
   
   public String getWebappPath() throws Exception
   {
      try
      {
         return org.allbinary.globals.URLGLOBALS.getWebappPath();
      }
      catch(Exception e)
      {
         /*
         String error = "Failed to getWebappPath";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(error,this,"getWebappPath()",e);
         }*/
         return null;
      }
   }

   public void setWebappPath(String path) throws Exception
   {
      try
      {
          AppUrlGlobals urlGlobals = new AppUrlGlobals();
          urlGlobals.setWebappPath(path);
         URLGLOBALS.init(urlGlobals);
      }
      catch(Exception e)
      {         
         String error = "Failed to getWebappPath";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"setWebappPath()",e));
         }
      }
   }
   
}
