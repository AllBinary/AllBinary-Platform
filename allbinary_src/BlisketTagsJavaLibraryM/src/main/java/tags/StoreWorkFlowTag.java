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

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.logic.system.security.licensing.LicensingException;

import org.allbinary.logic.visual.transform.info.TransformInfoData;

import org.allbinary.logic.communication.log.LogUtil;
import javax.servlet.jsp.JspTagException;

import taghelpers.StoreTagWorkFlowHelperFactory;

//classes that extend the xxxWorkFlowTag are the intermediary between using the 
//request servlet xml driven workflow vs using jsp tags as the workflow
//Reasons: workflows for non-jsp(s) like asp, php, and other
//and hidden field removal
public class StoreWorkFlowTag extends HelperTag
{
   private String viewName;
   private String viewFile;

   public StoreWorkFlowTag()
   {
      super(new StoreTagWorkFlowHelperFactory());
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
      {
         LogUtil.put(LogFactory.getInstance(this.commonStrings.START,this,this.commonStrings.CONSTRUCTOR));
      }
   }

   public void setName(String value)
   {
      this.viewName = value;
      this.getPropertiesHashMap().put(TransformInfoData.getInstance().NAME, this.viewName);
   }
   
   /*
   public void setObjectFile(String value)
   {
      this.viewFile = value;
      this.getPropertiesHashMap().put(TransformInfoData.OBJECTFILENAME, this.viewFile);
   }
   */
   
   private int process() throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START,this,commonStrings.PROCESS));
         }
         
         Class helperClass = this.getHelper().getClass();
         Method method = helperClass.getMethod(commonStrings.PROCESS,null);
         Integer result = (Integer) method.invoke(this.getHelper(),null);
         return result.intValue();
      }
      catch(Exception e)
      {         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,commonStrings.PROCESS,e));
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
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START,this,"doStartTag()"));
         }
         
         this.setHelper();
         return this.process();
      }
      catch(LicensingException e)
      {
         AbResponseHandler.sendJspTagLicensingRedirect(this.pageContext,e);
         return SKIP_BODY;
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext,e);
         return SKIP_BODY;
      }
   }
}
