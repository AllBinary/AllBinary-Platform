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

import admin.taghelpers.PaymentProcessHelperFactory;
import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.LicensingException;
import tags.StoreValidationTransformTag;

public class PaymentProcessTag extends StoreValidationTransformTag
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   public PaymentProcessTag()
   {
   }

   public String process() throws LicensingException, Exception
   {
      try
      {
         Object object = 
            new PaymentProcessHelperFactory().getInstance(
               this.getPropertiesHashMap(), this.pageContext);

         Class helperClass = object.getClass();

         Method method = helperClass.getMethod(commonStrings.PROCESS,null);

         String result = (String) method.invoke(object,null);

         return result;
      }
      catch(LicensingException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
         {
            logUtil.put("LicensingException", this, commonStrings.PROCESS, e);
         }         
         throw e;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.PROCESS, e);
         }
         throw e;
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.AUTHORIZEORDEREVALBODYONERROR)==0 ||
            this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.AUTHORIZEORDERANDEVALBODY)==0 ||
            this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.AUTHORIZEFORMEVALBODYONERROR)==0 )
            //AUTHORIZEFORMEVALBODYONERROR is used for form payment processing instead of order in session
         {
            this.pageContext.getOut().print(this.process() + "<br />");
            
            if(this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.AUTHORIZEORDEREVALBODYONERROR)==0)
               return this.SKIP_BODY;
            else return this.EVAL_BODY_INCLUDE;
         }
         else
         {
            throw new Exception("No Such Payment Processing Command");
         }
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext,e);
         return SKIP_BODY;
      }
   }
}
