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
import javax.servlet.jsp.tagext.TagSupport;

import admin.taghelpers.OrderHelperFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayData;
import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.LicensingException;
import tags.CustomTagSupport;

public class OrderTag extends CustomTagSupport
{
    protected final LogUtil logUtil = LogUtil.getInstance();
    
   private String command;
   private String storeName;

   private HashMap propertiesHashMap;
   
   public OrderTag()
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
   
   private String process() throws LicensingException
   {
      try
      {
         Object object = new OrderHelperFactory().getInstance(
               this.propertiesHashMap, this.pageContext);         
         
         Class helperClass = object.getClass();
         Method method = helperClass.getMethod(commonStrings.PROCESS, null);
         
         String result = (String) method.invoke(object, null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to Process Order: ";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,commonStrings.PROCESS,e);
         }
         return error;
      }
   }

   private Boolean setPaymentGateway() throws LicensingException
   {
      try
      {
         Object object = 
            new OrderHelperFactory().getInstance(
               this.propertiesHashMap, this.pageContext);         
         
         Class addressHelperClass = object.getClass();
         Method method = addressHelperClass.getMethod("setPaymentGateway", null);
         
         Boolean result = (Boolean) method.invoke(object,null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to setPaymentGateway for Order";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "setPaymentGateway()", e);
         }
         return Boolean.FALSE;
      }
   }

   /*
   private String getMaxAmount() throws LicensingException
   {
      try
      {
         Object object = OrderRequestHelperFactory.getInstance(this.storeName,
         pageContext.getSession(),(HttpServletRequest) this.pageContext.getRequest());
         Class addressHelperClass = object.getClass();
         Method method = addressHelperClass.getMethod("getMaxAmount",null);
         
         String result = (String) method.invoke(object,null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed Get Max Amount for delayed credit card processing for Order: ";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"getMaxAmount()",e);
         }
         return "Unknown";
      }
   }
   */
   /*
   private String getOrderIdFromSession() throws LicensingException
   {
      try
      {
         Object object = OrderHelperFactory.getInstance(this.storeName,
         pageContext.getSession();
         Class addressHelperClass = object.getClass();
         Method method = addressHelperClass.getMethod("getOrderIdFromSession",null);
         
         String result = (String) method.invoke(object,null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to get Order id from session";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"getOrderIdFromSession()",e);
         }
         return "Unknown";
      }
   }   
   */
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(command!=null)
         {
            this.propertiesHashMap = new HashMap();
            this.propertiesHashMap.put(StoreFrontData.getInstance().NAME,this.storeName);            
            
            if (command.compareTo(org.allbinary.globals.GLOBALS2.PROCESS)==0)
            {
               this.process();
            }
            else
               if (command.compareTo(PaymentGatewayData.NAME.toString())==0)
               {
                  if(this.setPaymentGateway() == Boolean.TRUE)
                  {
                     return TagSupport.EVAL_BODY_INCLUDE;
                  }
               }
         }
         return TagSupport.SKIP_BODY;
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
