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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.LicensingException;
import taghelpers.ValidatedViewHelperFactory;

public class ValidationTransformTag extends TransformTag
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private String xslFile;
   private boolean logic;
   
   public ValidationTransformTag()
   {
      super(new ValidatedViewHelperFactory());
      this.logic = true;
   }

   public void setXsl(String value)
   {
      this.xslFile=value;
      this.setTemplateFile(this.xslFile);
   }
   
   public void setLogic(boolean logic)
   {
      this.logic = logic;
   }

   private boolean isValid() throws Exception
   {
      try
      {
         Class helperClass = this.getHelper().getClass();
         Method method = helperClass.getMethod(commonStrings.IS_VALID,null);
         Boolean result = (Boolean) method.invoke(this.getHelper(),null);
         return result.booleanValue();
      }
      catch(Exception e)
      {
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,commonStrings.IS_VALID,e);
         }
         throw e;
      }
   }
   
   private String validationInfo() throws Exception
   {
      try
      {
         Class addressHelperClass = this.getHelper().getClass();
         Method method = addressHelperClass.getMethod("validationInfo",null);
         
         String result = (String) method.invoke(this.getHelper(), null);
         return result;
      }
      catch(Exception e)
      {

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"validationInfo()",e);
         }
         throw e;
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
         {
             StringMaker stringBuffer = new StringMaker();

             HttpServletRequest request =
                 (HttpServletRequest) this.pageContext.getRequest();

             stringBuffer.append("ValidationViewTag Start For: ");
             stringBuffer.append(this.getName());
             stringBuffer.append("\nView FIle: ");
             stringBuffer.append(this.getObjectFile());
             stringBuffer.append("\nRequest URI: ");
             stringBuffer.append(request.getRequestURI());

            logUtil.put(stringBuffer.toString(), this, "doStartTag");
         }
         
         this.setHelper();
         
         if(this.isValid())
         {
            super.doStartTag();
            
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
            {
             StringMaker stringBuffer = new StringMaker();

             stringBuffer.append("View File: ");
             stringBuffer.append(this.getObjectFile());
             stringBuffer.append("\nLogic includes body if true=");
             stringBuffer.append(this.logic);

             logUtil.put(stringBuffer.toString(), this, "doStartTag");
            }
            
            if(this.logic)
            {
                return EVAL_BODY_INCLUDE;
            }
            else
            {
                return SKIP_BODY;
            }
         }
         else
         {
            //TWB - convert valid text to dom node and remove validationInfo
            //super.doStartTag();
            pageContext.getOut().print(this.validationInfo());
            
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
            {
                StringMaker stringBuffer = new StringMaker();
                
                stringBuffer.append("View File: ");
                stringBuffer.append(this.getObjectFile());
                stringBuffer.append("\nisValid()=false");
                stringBuffer.append("\nLogic skips body if true=");
                stringBuffer.append(this.logic);
                
                logUtil.put(stringBuffer.toString(), this, "doStartTag");
            }
            
            if(this.logic)
            {
                return SKIP_BODY;
            }
            else
            {
                return EVAL_BODY_INCLUDE;
            }
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
   
   public int doEndTag()
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
      {
         logUtil.put("Tag Ended",this,"doEndTag");
      }
      this.logic = true;
      return super.doEndTag();
   }
}
