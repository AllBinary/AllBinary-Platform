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

import org.allbinary.logic.system.security.licensing.LicensingException;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.context.modules.storefront.StoreFrontData;

import admin.taghelpers.StoreFrontHelperFactory;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;

import tags.CustomTagSupport;

import java.util.HashMap;
import javax.servlet.jsp.JspTagException;

public class StoreFrontTag extends CustomTagSupport
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private String command;
   private String storeName;

   private HashMap propertiesHashMap;
   
   public StoreFrontTag()
   {
   }
   
   public void setCommand(String command)
   {
      this.command=command;
   }
   
   public void setStoreName(String value)
   {
      this.storeName = value;
   }
   
   private String getCurrentLocation() throws LicensingException
   {
      try
      {
         Object object = 
            new StoreFrontHelperFactory().getInstance(
               this.propertiesHashMap, this.pageContext);
         
         Method method = object.getClass().getMethod("getCurrentLocation", null);
         String result = (String) method.invoke(object, null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to retrieve current location";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"getCurrentLocation()",e);
         }
         return error;
      }
   }
   
   private String getCurrentHomeLocation() throws LicensingException
   {
      try
      {
         Object object = 
            new StoreFrontHelperFactory().getInstance(
               this.propertiesHashMap, this.pageContext);
         
         Method method = object.getClass().getMethod("getCurrentHomeLocation", null);
         String result = (String) method.invoke(object, null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to retrieve current home location";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"getCurrentHomeLocation()",e);
         }
         return error;
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(command!=null)
         {
            this.propertiesHashMap = new HashMap();
            this.propertiesHashMap.put(StoreFrontData.getInstance().NAME, this.storeName);
            
            if (command.compareTo(org.allbinary.globals.GLOBALS2.GETCURRENTLOCATION)==0)
            {
               pageContext.getOut().print(this.getCurrentLocation());
            }
            else
               if (command.compareTo(org.allbinary.globals.GLOBALS2.GETCURRENTHOMELOCATION)==0)
               {
                  pageContext.getOut().print(this.getCurrentHomeLocation());
               }
         }
         return SKIP_BODY;
      }
      catch(LicensingException e)
      {
         AbResponseHandler.sendJspTagLicensingRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }
   
}
