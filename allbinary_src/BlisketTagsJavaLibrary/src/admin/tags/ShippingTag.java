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

import abcs.logic.system.security.licensing.LicensingException;

import abcs.logic.communication.log.LogUtil;

import admin.taghelpers.ShippingHelperFactory;

import allbinary.business.context.modules.storefront.StoreFrontData;

import abcs.logic.communication.http.request.AbResponseHandler;
import abcs.logic.communication.log.LogFactory;

import java.util.HashMap;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class ShippingTag extends TagSupport
{
   private String command;  
   private String storeName;   

   private HashMap propertiesHashMap;
      
   public ShippingTag()
   {
   }
   
   public void setCommand(String command)
   {
      this.command=command;
   }
            
   public void setStoreName(String value)
   {
      this.storeName=value;
   }       

   private String setShippingType() throws LicensingException
   {
      try
      {
         
         Object object = new ShippingHelperFactory().getInstance(
            propertiesHashMap, this.pageContext);
         Class addressHelperClass = object.getClass();
         Method method = addressHelperClass.getMethod("setShippingType",null);
         
         String result = (String) method.invoke(object,null);
         return result;                  
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to view Shipping Type";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"setShippingType()",e));
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
            propertiesHashMap.put(StoreFrontData.getInstance().NAME, this.storeName);
            
            if (command.compareTo(allbinary.globals.GLOBALS.SETSHIPPINGTYPE)==0)
            {
               this.setShippingType();
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