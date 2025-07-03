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

import org.allbinary.logic.system.security.licensing.LicensingException;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.context.modules.storefront.StoreFrontData;

import org.allbinary.business.user.commerce.money.payment.PaymentData;

import admin.taghelpers.PaymentHelperFactory;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;

import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.jsp.JspTagException;

public class PaymentTag extends TableTag
{
   private String storeName;
   
   private HashMap propertiesHashMap;
   
   public PaymentTag()
   {
      this.setTagHelperFactory(new PaymentHelperFactory());
      this.setTagRequestHelperFactory(new PaymentHelperFactory());
   }
   
   public void setStoreName(String value)
   {
      this.storeName = value;
   }
   
   private String select() throws LicensingException
   {
      try
      {
         Object object =
         new PaymentHelperFactory().getInstance(
         this.getPropertiesHashMap(), pageContext);
         
         Class addressHelperClass = object.getClass();
         Method method = addressHelperClass.getMethod("select",null);
         
         String result = (String) method.invoke(object,null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to select Payment";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"selectPayment()",e));
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
            this.propertiesHashMap = new HashMap();
            this.propertiesHashMap.put(StoreFrontData.getInstance().NAME, this.storeName);
            
            if(this.getCommand()!=null)
            {
               if (this.getCommand().compareTo(PaymentData.SELECT)==0)
               {
                  String output = this.select();
                  if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                  {
                     this.pageContext.getOut().print(output + "<br />");
                  }
                  return this.EVAL_BODY_INCLUDE;
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
