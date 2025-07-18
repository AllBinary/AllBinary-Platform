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
package admin.tags;

import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.jsp.JspTagException;

import admin.taghelpers.CustomLoaderHelperFactory;
import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.LicensingException;
import tags.CustomTagSupport;

public class CustomLoaderTag extends CustomTagSupport
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
   private String command;
   private String webappPath;

   private HashMap propertiesHashMap;
   
   public CustomLoaderTag()
   {
   }
   
   public void setCommand(String command)
   {
      this.command=command;
   }
   
   public void setWebappPath(String value)
   {
      this.webappPath = value;
   }
   
   private void setCustomLoaderWebappPath() throws LicensingException
   {
      try
      {          
         Object object = new CustomLoaderHelperFactory().getInstance(
             this.propertiesHashMap, pageContext);
            
         Class helperClass = object.getClass();
         
         Class[] methodParams =
         {this.webappPath.getClass()};
         Object[] methodArgs =
         {this.webappPath};
         
         Method setMethod = helperClass.getMethod("setWebappPath",methodParams);
         setMethod.invoke(object,methodArgs);                  
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "setCustomLoaderWebappPath()", e);
         }
      }
   }
   
  //       Method method = helperClass.getMethod("getWebappPath",null);
//         String webappPath = (String) method.invoke(object,null);
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
         {
            logUtil.put(this.commonStrings.START, this, "doStartTag()");
         }
         
         if(command!=null)
         {
            this.propertiesHashMap = new HashMap();
            
            if(command.compareTo(org.allbinary.globals.GLOBALS2.SET)==0)
            {
               this.setCustomLoaderWebappPath();
            }
         }
         return SKIP_BODY;
       }
      catch(LicensingException e)
      {
         AbResponseHandler.sendJspTagLicensingRedirect(this.pageContext,e);
         return SKIP_BODY;
     }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext,e);
         return SKIP_BODY;
     }
  }
}