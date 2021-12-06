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
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.LicensingException;
import admin.taghelpers.TagHelperFactoryInterface;

public class ModifyTag extends HelperTag 
{
   public ModifyTag(TagHelperFactoryInterface tagHelperFactoryInterface)
   {
      super(tagHelperFactoryInterface);
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.JSPTAG))
      {
         LogUtil.put(LogFactory.getInstance("Tag Constructed",this,"Constructor"));
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
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"insert()",e));
         }
         return error;
      }
   }
   
   public String delete() throws LicensingException
   {
      try
      {
         Class helperClass = this.getHelper().getClass();
         Method method = helperClass.getMethod("delete",null);
         
         String result = (String) method.invoke(this.getHelper(),null);
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to delete";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"delete()",e));
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
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"update()",e));
         }
         return error;
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
          if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.JSPTAG))
          {
              HttpServletRequest request =
                  (HttpServletRequest) this.pageContext.getRequest();

              StringBuffer stringBuffer = new StringBuffer();
              
              stringBuffer.append("Command: ");
              stringBuffer.append(this.getCommand());
              stringBuffer.append(" Request URI: ");
              stringBuffer.append(request.getRequestURI());

              LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "doStartTag()"));
          }
    	  
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.JSPTAG))
         {
            
         }
         
         if(this.getCommand()!=null)
         {
            this.setHelper();

            if (this.getCommand().compareTo(allbinary.globals.GLOBALS.INSERT)==0)
            {
               String output = this.insert();
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.JSPTAGEXTRAOUTPUT))
               {
                  this.pageContext.getOut().print(output + "<br />");
               }
            }
            else
               if (this.getCommand().compareTo(allbinary.globals.GLOBALS.DELETE)==0)
               {
                  String output = this.delete();
                  if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.JSPTAGEXTRAOUTPUT))
                  {
                     this.pageContext.getOut().print(output + "<br />");
                  }
               }
               else
                  if (this.getCommand().compareTo(allbinary.globals.GLOBALS.UPDATE)==0)
                  {
                     String output = this.update();
                     if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.JSPTAGEXTRAOUTPUT))
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
