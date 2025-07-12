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

import javax.servlet.jsp.JspTagException;

import admin.taghelpers.QuoteHelperFactory;
import admin.taghelpers.QuoteRequestHelperFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.LicensingException;

public class QuoteRequestTag extends TableTag
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private String storeName;
   
   public QuoteRequestTag()
   {
      this.setTagHelperFactory(new QuoteHelperFactory());
      this.setTagRequestHelperFactory(new QuoteRequestHelperFactory());      
   }
   
   public void setStoreName(String storeName)
   {
      this.storeName = storeName;
   }
      
   private String email() throws LicensingException
   {
      try
      {
         Object object =
         new QuoteHelperFactory().getInstance(
         this.getPropertiesHashMap(), pageContext);
         
         Class helperClass = object.getClass();
         Method method = helperClass.getMethod("email",null);
         
         String result = (String) method.invoke(object,null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to send QuoteRequest emails";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"sendEmail()",e);
         }
         return error;
      }
   }
      
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.getCommand()!=null)
         {
            this.getPropertiesHashMap().put(StoreFrontData.getInstance().NAME, this.storeName);
            
            if (this.getCommand().compareTo("EMAIL")==0)
            {
               this.email();
               return this.EVAL_BODY_INCLUDE;
            }
            else
            {
               return super.doStartTag();
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
