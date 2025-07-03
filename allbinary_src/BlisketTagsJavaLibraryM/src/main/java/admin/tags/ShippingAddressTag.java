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

import org.allbinary.business.user.address.ShippingAddressData;

import org.allbinary.logic.communication.log.LogUtil;

import admin.taghelpers.ShippingAddressHelperFactory;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;

import javax.servlet.jsp.JspTagException;

public class ShippingAddressTag extends TableTag
{
   private String value;
   
   public ShippingAddressTag()
   {
      this.setTagHelperFactory(new ShippingAddressHelperFactory());
      this.setTagRequestHelperFactory(new ShippingAddressHelperFactory());
   }

   public void setValue(String value)
   {
      this.value=value;
   }
   
   private String set() throws LicensingException
   {
      try
      {
         Object object = new ShippingAddressHelperFactory().getInstance(
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
         String error = "Failed to set Shipping Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"set()",e));
         }
         return error;
      }
   }
   
   private String setToBillingAddress() throws LicensingException
   {
      try
      {
         Object object = new ShippingAddressHelperFactory().getInstance(
         this.getPropertiesHashMap(), this.pageContext);
         
         Class addressHelperClass = object.getClass();
         Method method = addressHelperClass.getMethod("setToBillingAddress",null);
         
         String result = (String) method.invoke(object,null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed Setting Shipping address to Billing Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"setShippingAddressToBillingAddress()",e));
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
               if (this.getCommand().compareTo(ShippingAddressData.SELECT)==0)
               {
                  set();
                  return this.EVAL_BODY_INCLUDE;
               }
               else
                  if (this.getCommand().compareTo(ShippingAddressData.SETTOBILLINGADDRESS)==0)
                  {
                     setToBillingAddress();
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
