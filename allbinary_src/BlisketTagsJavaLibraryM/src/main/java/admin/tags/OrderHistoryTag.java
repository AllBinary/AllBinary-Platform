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

import javax.servlet.http.HttpServletRequest;

import org.allbinary.logic.system.security.licensing.LicensingException;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;

import admin.taghelpers.OrderHistoryHelperFactory;
import admin.taghelpers.OrderHistoryRequestHelperFactory;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;

import javax.servlet.jsp.JspTagException;

public class OrderHistoryTag extends TableTag
{
   private String status;
   
   public OrderHistoryTag()
   {
      this.setTagHelperFactory(new OrderHistoryHelperFactory());
      this.setTagRequestHelperFactory(new OrderHistoryRequestHelperFactory());
   }

   public void setStatus(String value)
   {
      this.status=value;
   }
   
   private String setOrderStatus() throws LicensingException
   {
      try
      {
         HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
         
         Object object =
         new OrderHistoryRequestHelperFactory().getInstance(
         this.getPropertiesHashMap(), pageContext);
         
         if(this.status==null)
         {
            Class helperClass = object.getClass();
            Method method = helperClass.getMethod("setOrderStatus",null);
            String result = (String) method.invoke(object,null);
            return result;
         }
         else
         {
            Class helperClass = object.getClass();
            Class[] methodParams =
            {this.status.getClass()};
            Method method = helperClass.getMethod("setOrderStatus",methodParams);
            Object[] methodArgs =
            {this.status};
            String result = (String) method.invoke(object,methodArgs);
            return result;
         }
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to set Order Status";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"setOrderStatus()",e));
         }
         return error;
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.isEnabled())
         {
            if(this.getCommand()!=null)
            {
               this.getPropertiesHashMap().put(OrderHistoryData.STATUS,this.status);
               
               if (this.getCommand().compareTo(OrderHistoryData.SETSTATUS)==0)
               {
                  this.setOrderStatus();
               }
               else
               {
                  return super.doStartTag();
               }
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

