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

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.LicensingException;
import admin.taghelpers.CategoryHelperFactory;
import admin.taghelpers.CategoryRequestHelperFactory;
import org.allbinary.business.category.CategoryData;
import org.allbinary.logic.visual.transform.info.TransformInfoData;

public class CategoryTag extends TableTag
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private String xsl;
   
   public CategoryTag()
   {
      this.setTagHelperFactory(new CategoryHelperFactory());
      this.setTagRequestHelperFactory(new CategoryRequestHelperFactory());
   }
   
   public void setXsl(String value)
   {
      this.xsl = value;
   }
   
   private String viewCategory() throws LicensingException
   {
      try
      {
         Object object =
         new CategoryRequestHelperFactory().getInstance(
         this.getPropertiesHashMap(), this.pageContext);
         
         Class helperClass = object.getClass();
         Method method = helperClass.getMethod("viewCategory",null);
         
         String result = (String) method.invoke(object,null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to view a Category";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"viewCategory()",e);
         }
         return error;
      }
   }
   
   private String viewCategories() throws LicensingException
   {
      try
      {
         Object object =
         new CategoryRequestHelperFactory().getInstance(
         this.getPropertiesHashMap(), this.pageContext);
         
         Class helperClass = object.getClass();
         Method method = helperClass.getMethod("viewCategories",null);
         
         String result = (String) method.invoke(object,null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to view a Categories";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"viewCategories()",e);
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
               this.getPropertiesHashMap().put(TransformInfoData.getInstance().TEMPLATEFILENAME,this.xsl);
               
               if (this.getCommand().compareTo(CategoryData.getInstance().VIEW)==0)
               {
                  String output = this.viewCategory();
                  if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                  {
                     pageContext.getOut().print(output);
                  }
               }
               else
                  if (this.getCommand().compareTo(CategoryData.getInstance().VIEW)==0)
                  {
                     String output = this.viewCategories();
                     if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                     {
                        pageContext.getOut().print(output);
                     }
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
