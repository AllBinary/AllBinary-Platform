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

import admin.taghelpers.TagHelperFactoryInterface;
import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.string.CommonStrings;

public class ModifyTag extends HelperTag 
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   public ModifyTag(TagHelperFactoryInterface tagHelperFactoryInterface)
   {
      super(tagHelperFactoryInterface);
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
      {
         logUtil.put(this.commonStrings.START,this, this.commonStrings.CONSTRUCTOR);
      }
   }
      
   public String insert() throws LicensingException
   {
      try
      {
         Class helperClass = this.getHelper().getClass();
         Method method = helperClass.getMethod("insert",null);
         
         String result = (String) method.invoke(this.getHelper(),null);
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to insert";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"insert()",e);
         }
         return error;
      }
   }
   
   public String delete() throws LicensingException
   {
      try
      {
         Class helperClass = this.getHelper().getClass();
         Method method = helperClass.getMethod(commonStrings.delete,null);
         
         String result = (String) method.invoke(this.getHelper(),null);
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to delete";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"delete()",e);
         }
         return error;
      }
   }
   
   public String update() throws LicensingException
   {
      try
      {
         Class helperClass = this.getHelper().getClass();
         Method method = helperClass.getMethod("update",null);
         
         String result = (String) method.invoke(this.getHelper(),null);
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to update";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"update()",e);
         }
         return error;
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
          if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
          {
              HttpServletRequest request =
                  (HttpServletRequest) this.pageContext.getRequest();

              StringMaker stringBuffer = new StringMaker();
              
              stringBuffer.append("Command: ");
              stringBuffer.append(this.getCommand());
              stringBuffer.append(" Request URI: ");
              stringBuffer.append(request.getRequestURI());

              logUtil.put(stringBuffer.toString(), this, "doStartTag()");
          }
    	  
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
         {
            
         }
         
         if(this.getCommand()!=null)
         {
            this.setHelper();

            final CommonStrings commonStrings = CommonStrings.getInstance();
            
            if (this.getCommand().compareTo(commonStrings.INSERT)==0)
            {
               String output = this.insert();
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
               {
                  this.pageContext.getOut().print(output + "<br />");
               }
            }
            else
               if (this.getCommand().compareTo(commonStrings.DELETE)==0)
               {
                  String output = this.delete();
                  if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                  {
                     this.pageContext.getOut().print(output + "<br />");
                  }
               }
               else
                  if (this.getCommand().compareTo(commonStrings.UPDATE)==0)
                  {
                     String output = this.update();
                     if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                     {
                        this.pageContext.getOut().print(output + "<br />");
                     }
                  }
         }
         return this.EVAL_BODY_INCLUDE;
         //         throw new Exception("Table doStartTag Should Never Be Used.");
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
