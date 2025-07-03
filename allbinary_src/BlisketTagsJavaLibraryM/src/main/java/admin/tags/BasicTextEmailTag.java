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

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.logic.communication.log.LogUtil;

import admin.taghelpers.BasicTextEmailHelperFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import javax.servlet.jsp.JspTagException;

import org.allbinary.string.CommonStrings;
import tags.CustomTagSupport;

public class BasicTextEmailTag extends CustomTagSupport
{
    
   private String subject;
   private String body;
   
   public BasicTextEmailTag()
   {
   }
   
   public void setSubject(String value)
   {
      this.subject = value;
   }

   public void setBody(String value)
   {
      this.body = value;
   }

   public String send()
   {
      try
      {
         HashMap propertiesHashMap = new HashMap();
         propertiesHashMap.put("Subject", this.subject);
         propertiesHashMap.put("Body", this.body);

         Object object = 
            new BasicTextEmailHelperFactory().getInstance(
               propertiesHashMap, this.pageContext);
         
         Method method = object.getClass().getMethod("send", null);
         method.invoke(object, null);
         return "Email Sent";

      }
      catch(Exception e)
      {
         String error = "Failed to Send Email.";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"send()",e));
         }
         return error;
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         this.pageContext.getOut().print(this.send());
        
         return SKIP_BODY;
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }
}
