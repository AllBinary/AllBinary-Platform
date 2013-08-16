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

import abcs.logic.communication.http.request.AbResponseHandler;
import abcs.logic.communication.log.LogFactory;

import abcs.logic.system.security.licensing.LicensingException;

import admin.taghelpers.PaymentProcessHelperFactory;

import abcs.logic.communication.log.LogUtil;

import tags.StoreValidationTransformTag;

import java.lang.reflect.Method;
import javax.servlet.jsp.JspTagException;

public class PaymentProcessTag extends StoreValidationTransformTag
{
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

         Method method = helperClass.getMethod("process",null);

         String result = (String) method.invoke(object,null);

         return result;
      }
      catch(LicensingException e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSINGERROR))
         {
            LogUtil.put(LogFactory.getInstance("LicensingException", this, "process()", e));
         }         
         throw e;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAGERROR))
         {
            String error = "Failed to process a gateway to a store";
            LogUtil.put(LogFactory.getInstance(error, this, "process()", e));
         }
         throw e;
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.getCommand().compareTo(allbinary.globals.GLOBALS.AUTHORIZEORDEREVALBODYONERROR)==0 ||
            this.getCommand().compareTo(allbinary.globals.GLOBALS.AUTHORIZEORDERANDEVALBODY)==0 ||
            this.getCommand().compareTo(allbinary.globals.GLOBALS.AUTHORIZEFORMEVALBODYONERROR)==0 )
            //AUTHORIZEFORMEVALBODYONERROR is used for form payment processing instead of order in session
         {
            this.pageContext.getOut().print(this.process() + "<br />");
            
            if(this.getCommand().compareTo(
            allbinary.globals.GLOBALS.AUTHORIZEORDEREVALBODYONERROR)==0)
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
