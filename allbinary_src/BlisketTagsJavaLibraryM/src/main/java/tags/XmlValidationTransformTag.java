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
package tags;

import java.lang.reflect.Method;

import javax.servlet.jsp.JspTagException;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.system.security.licensing.LicensingException;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;

public class XmlValidationTransformTag extends TransformTag
{
   public XmlValidationTransformTag()
   {
   }
   
   private boolean isValid() throws Exception
   {
      try
      {
         Class helperClass = this.getHelper().getClass();
         Method method = helperClass.getMethod("isValid",null);
         Boolean result = (Boolean) method.invoke(this.getHelper(),null);
         return result.booleanValue();
      }
      catch(Exception e)
      {
         String error = "Failed to validate Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"isValid()",e));
         }
         throw e;
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         this.setHelper();
         
         if(this.isValid())
         {
            super.doStartTag();
            return this.EVAL_BODY_INCLUDE;
         }
         else
         {
            super.doStartTag();
            return this.SKIP_BODY;
         }
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
