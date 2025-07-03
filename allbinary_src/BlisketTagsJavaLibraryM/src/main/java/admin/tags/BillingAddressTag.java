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

import javax.servlet.jsp.*;

import org.allbinary.logic.system.security.licensing.LicensingException;

import org.allbinary.business.user.address.BillingAddressData;

import org.allbinary.logic.communication.log.LogUtil;

import admin.taghelpers.BillingAddressHelperFactory;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;

public class BillingAddressTag extends TableTag
{
   private String value;
   
   public BillingAddressTag()
   {
      this.setTagHelperFactory(new BillingAddressHelperFactory());
      this.setTagRequestHelperFactory(new BillingAddressHelperFactory());
   }
   
   public void setValue(String value)
   {
      this.value=value;
   }
   
   private String set() throws LicensingException
   {
      try
      {
         Object object = new BillingAddressHelperFactory().getInstance(
         this.getPropertiesHashMap(), this.pageContext);
         
         Class addressHelperClass = object.getClass();
         
         Method method = addressHelperClass.getMethod("set",null);
         String result = (String) method.invoke(object,null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to set billing Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"set()",e));
         }
         return error;
      }
   }
   
   private String setToShippingAddress() throws LicensingException
   {
      try
      {
         Object object = new BillingAddressHelperFactory().getInstance(
         this.getPropertiesHashMap(), this.pageContext);
         
         Class addressHelperClass = object.getClass();
         Method method = addressHelperClass.getMethod("setToShippingAddress",null);
         
         String result = (String) method.invoke(object,null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to Set Billing address to Shipping Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"setToShippingAddress()",e));
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
               //this.getPropertiesHashMap().put(StreetAddressData.INDEX,this.value);
               
               if (this.getCommand().compareTo(BillingAddressData.SELECT)==0)
               {
                  set();
                  return this.EVAL_BODY_INCLUDE;
               }
               else
                  if (this.getCommand().compareTo(BillingAddressData.SETTOSHIPPINGADDRESS)==0)
                  {
                     setToShippingAddress();
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
