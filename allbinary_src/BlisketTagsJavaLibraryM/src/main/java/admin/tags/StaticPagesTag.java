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

import org.allbinary.logic.communication.log.LogUtil;

import admin.taghelpers.StaticPagesHelperFactory;
import admin.taghelpers.StaticPagesRequestHelperFactory;

import org.allbinary.logic.control.search.SearchData;

import org.allbinary.logic.visual.transform.info.TransformInfoData;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;

import javax.servlet.jsp.JspTagException;

public class StaticPagesTag extends TableTag
{
   private String xslFile;
   
   public StaticPagesTag()
   {
      this.setTagHelperFactory(new StaticPagesHelperFactory());
      this.setTagRequestHelperFactory(new StaticPagesRequestHelperFactory());
   }
   
   public void setXsl(String value)
   {
      this.xslFile=value;
   }

   public String generateStaticPages() throws LicensingException
   {
      try
      {
         //LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "generateStaticPages()"));

         Object object = new StaticPagesRequestHelperFactory().getInstance(
         this.getPropertiesHashMap(), this.pageContext);
         
         Class helperClass = object.getClass();
         
         Method method = helperClass.getMethod("generateStaticPages",null);
         
         String result = (String) method.invoke(object,null);
         
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to generate staticpages table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"generateStaticPages()",e));
         }
         return error;
      }
   }

   public String makePublic() throws LicensingException
   {
      try
      {
         Object object = new StaticPagesRequestHelperFactory().getInstance(
         this.getPropertiesHashMap(), this.pageContext);
         
         Class helperClass = object.getClass();
         
         Method method = helperClass.getMethod("makePublic",null);
         
         String result = (String) method.invoke(object,null);
         
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to makePublic";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"makePublic()",e));
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
               //LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START + this.getCommand(), this, "doStartTag"));

               if (this.getCommand().compareTo(SearchData.GENERATESTATICPAGES)==0)
               {
                  this.getPropertiesHashMap().put(TransformInfoData.getInstance().TEMPLATEFILENAME, this.xslFile);

                  final String output = this.generateStaticPages();
                  if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                  {
                     this.pageContext.getOut().print(output);
                  }
               }
               else
               if (this.getCommand().compareTo(SearchData.MAKEPUBLIC)==0)
               {
                   final String output = this.makePublic();
                  if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                  {
                     this.pageContext.getOut().print(output);
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
