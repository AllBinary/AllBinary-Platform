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
package admin.taghelpers;

import javax.servlet.jsp.PageContext;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.logic.io.file.FileUtil;

import org.allbinary.globals.URLGLOBALS;

import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.logic.communication.log.LogUtil;

import views.admin.inventory.listings.ProductListingFactory;

import org.allbinary.logic.communication.smtp.info.EmailInfo;
import org.allbinary.logic.communication.smtp.info.BasicEmailInfo;
import org.allbinary.logic.communication.smtp.info.AdminEmailInfo;
import org.allbinary.logic.communication.smtp.info.StoreEmailInfo;

import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;

import org.allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;

import org.allbinary.logic.communication.smtp.event.handler.factory.AdminUserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.event.handler.factory.StoreAdminUserEmailEventHandlerSingletons;

import org.allbinary.business.context.AbContext;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;


import org.allbinary.logic.control.search.SearchRequest;
import org.allbinary.logic.control.search.SearchParams;

import org.allbinary.logic.visual.transform.info.TransformInfoData;

import org.allbinary.logic.communication.http.AcceptableResponseGenerator;
import java.util.HashMap;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;

public class StaticPagesRequestHelper extends AbContext
    implements TagHelperInterface
{
    private final AbeClientInformationInterface abeClientInformation = 
        ServiceClientInformationInterfaceFactory.getInstance();
    
   private HttpServletRequest request;
   
   private String storeName;
   private SearchParams searchParams;
   private String xslFile;

   public StaticPagesRequestHelper(HashMap propertiesHashMap, PageContext pageContext)
   {
      super(propertiesHashMap, pageContext);

      //LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR));

      this.request = (HttpServletRequest) pageContext.getRequest();
      this.xslFile = (String) propertiesHashMap.get(
          TransformInfoData.getInstance().TEMPLATEFILENAME);
      this.getFormData();
   }
      
   private void getFormData()
   {      
      this.storeName = request.getParameter(StoreFrontData.getInstance().NAME);
      this.searchParams = new SearchParams(this.request);
   }

   private void email() throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Generated Static Pages Notification Email", this, "email"));
         }

         StoreFrontInterface storeFrontInterface = 
            StoreFrontFactory.getInstance(this.storeName);
            
         String adminEmailSubject = "Generated Static Pages Email Notification";
         String adminEmailTextBody = "Generated static pages for store: " + this.storeName;

         BasicEmailInfo adminBasicEmailInfo = (BasicEmailInfo)
            new AdminEmailInfo(adminEmailSubject, adminEmailTextBody);
         
         BasicEmailInfo storeAdminBasicEmailInfo = (BasicEmailInfo)
            new StoreEmailInfo(storeFrontInterface, adminEmailSubject, adminEmailTextBody);
         
         EmailInfo storeAdminEmailInfo = new EmailInfo(storeAdminBasicEmailInfo);

         EmailInfo adminEmailInfo = new EmailInfo(adminBasicEmailInfo);
         
         //Send response to Admin(s)
         UserEmailEventHandler adminUserEmailEventHandler =
            AdminUserEmailEventHandlerSingletons.getInstance().getInstance(
               this.abeClientInformation, UserEmailEventNameData.STOREGENERATINGSTATICPAGES);

         UserEmailEventHandler storeAdminUserEmailEventHandler =
            StoreAdminUserEmailEventHandlerSingletons.getInstance().getInstance(
               UserEmailEventNameData.STOREGENERATINGSTATICPAGES, this.abeClientInformation, storeFrontInterface);

         storeAdminUserEmailEventHandler.receiveEmailInfo(
             UserEmailEventNameData.STOREGENERATINGSTATICPAGES, storeAdminEmailInfo);
         adminUserEmailEventHandler.receiveEmailInfo(
             UserEmailEventNameData.STOREGENERATINGSTATICPAGES, adminEmailInfo);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "email", e));
         }
         //throw e;
      }
   }

   public String generateStaticPages()
   {
      try
      {
         //LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "generateStaticPages()"));

         String contentType = AcceptableResponseGenerator.getInstance().get(this.request); 
         
         SearchRequest searchRequest = 
            new SearchRequest(
            null, this.searchParams, xslFile, contentType, 
            this.getPropertiesHashMap(), this.getPageContext());
         
         String success = ProductListingFactory.getInstance(searchRequest).generateAll(storeName);

         this.email();
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success, this, "generateStaticPages()"));
         }

         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to generate staticpages table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"generateStaticPages()",e));
         }
         return error;
      }
   }

   public String makePublic()
   {
      try
      {
         StoreFrontInterface storeFrontInterface = 
            StoreFrontFactory.getInstance(this.storeName);

         AbPath fromAbPath = new AbPath(storeFrontInterface.getTestHtmlPath() + 
            storeFrontInterface.getStaticPath());

         AbPath toAbPath = new AbPath(URLGLOBALS.getWebappPath() + 
            storeFrontInterface.getName() + 
            AbPathData.getInstance().SEPARATOR + storeFrontInterface.getStaticPath());

         FileUtil.getInstance().copy(fromAbPath, toAbPath);

         String success = "Made Public";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success, this,"makePublic()"));
         }

         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to makePublic";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"makePublic()",e));
         }
         return error;
      }
   }
   
}
