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

import java.util.HashMap;
import java.util.Vector;

import javax.servlet.jsp.PageContext;
import org.allbinary.business.context.modules.storefront.StoreFront;

import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.UserInterface;
import org.allbinary.business.user.quoterequest.QuoteRequest;
import org.allbinary.data.tables.user.UserEntityFactory;
import org.allbinary.data.tables.user.quoterequest.QuoteRequestEntity;
import org.allbinary.data.tables.user.quoterequest.QuoteRequestEntityFactory;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;
import org.allbinary.logic.communication.smtp.event.handler.factory.AdminUserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.event.handler.factory.StoreAdminUserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.event.handler.factory.UserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.info.BasicEmailInfo;
import org.allbinary.logic.communication.smtp.info.EmailInfo;
import org.allbinary.logic.communication.smtp.info.StoreEmailInfo;
import org.allbinary.logic.communication.sql.AbSqlTableUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;


public class QuoteHelper extends BasicTable
{
    private final AbeClientInformationInterface abeClientInformation = 
        ServiceClientInformationInterfaceFactory.getInstance();
    
    
   private final WeblisketSession weblisketSession;
   
   private final StoreFrontInterface storeFrontInterface;

   private final Portion portion;
   
   public QuoteHelper(HashMap hashMap, PageContext pageContext)
   {
      String storeName = (String) hashMap.get(StoreFrontData.getInstance().NAME);
      
      if(storeName!=null)
      {
         this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);
      }
      else
      {
    	  this.storeFrontInterface = new StoreFront();
      }
      
      this.weblisketSession = new WeblisketSession(hashMap, pageContext);
      
      this.portion = new Portion(hashMap);
   }

   private void emailUser(QuoteRequest quoteRequest) throws Exception
   {
      UserInterface user =
         UserEntityFactory.getInstance().getUser(quoteRequest.getUserName());

      String userEmailSubject = "Quote Request Receipt";
      
      StringBuffer stringBuffer = new StringBuffer();
      
      stringBuffer.append("This is just a verification email. ");
      stringBuffer.append("We usually respond to quote request within 24 hours.");
      stringBuffer.append("\n\nThank You For Your Business.");
      
      String userEmailTextBody = stringBuffer.toString();

      BasicEmailInfo basicEmailInfo = (BasicEmailInfo)
         new StoreEmailInfo(this.storeFrontInterface, userEmailSubject, userEmailTextBody);

      EmailInfo emailInfo = new EmailInfo(basicEmailInfo);

      //Send response to customer
      UserEmailEventHandler userEmailEventHandler =
         UserEmailEventHandlerSingletons.getInstance().getInstance(
             this.abeClientInformation, UserEmailEventNameData.QUOTEREQUEST, user);

      userEmailEventHandler.receiveEmailInfo(UserEmailEventNameData.QUOTEREQUEST, emailInfo);
   }

   private void emailAdmins(QuoteRequest quoteRequest) throws Exception
   {
      String adminEmailSubject = "Quote Request";
      
      StringBuffer stringBuffer = new StringBuffer();
      
      stringBuffer.append("\nUserName: ");
      stringBuffer.append(quoteRequest.getUserName());
      stringBuffer.append("\nProject Info: \n");
      stringBuffer.append(quoteRequest.getProjectInfo());
      stringBuffer.append("\nUser Comments: \n");
      stringBuffer.append(quoteRequest.getUserComments());
      stringBuffer.append("\nBudget: \n");
      stringBuffer.append(quoteRequest.getBudget());
      stringBuffer.append("\nTime Frame: \n");
      stringBuffer.append(quoteRequest.getTimeFrame());
      stringBuffer.append("\nComments: \n");
      stringBuffer.append(quoteRequest.getComments());
      
      String adminEmailTextBody = stringBuffer.toString();
      
      BasicEmailInfo basicEmailInfo = (BasicEmailInfo)
         new StoreEmailInfo(this.storeFrontInterface, 
         adminEmailSubject, adminEmailTextBody);
      
      EmailInfo emailInfo = new EmailInfo(basicEmailInfo);
      
      //send request to store admins if subscribed to handler name for review and response
      UserEmailEventHandler storeAdminUserEmailEventHandler =
         AdminUserEmailEventHandlerSingletons.getInstance().getInstance(
         this.abeClientInformation, UserEmailEventNameData.QUOTEREQUEST);
      
      UserEmailEventHandler adminUserEmailEventHandler =
         StoreAdminUserEmailEventHandlerSingletons.getInstance().getInstance(
         UserEmailEventNameData.QUOTEREQUEST, this.abeClientInformation, this.storeFrontInterface);
      
      storeAdminUserEmailEventHandler.receiveEmailInfo(UserEmailEventNameData.QUOTEREQUEST, emailInfo);
      adminUserEmailEventHandler.receiveEmailInfo(UserEmailEventNameData.QUOTEREQUEST, emailInfo);
   }
   
   public String email() throws Exception
   {
      try
      {
         final QuoteRequestEntity quoteRequestEntity =
            QuoteRequestEntityFactory.getInstance().getQuoteRequestEntityInstance();

         final String userName = this.weblisketSession.getUserName();
         
         Vector vector = quoteRequestEntity.getIds(userName);
         
         int id = 0;
         
         //get last quote request
         for(int index = 0; index < vector.size(); index++)
         {
        	 int nextId = ((Integer) vector.get(index)).intValue();
        	 if(id < nextId)
        	 {
        		 id = nextId;
        	 }
         }

         QuoteRequest quoteRequest = quoteRequestEntity.get(userName, id);
         
         if(quoteRequest != null)
         {
            this.emailUser(quoteRequest);
            this.emailAdmins(quoteRequest);
            
            return "Thank You For Your Business.<p/>";
         }
         else
         {
            throw new Exception("No Quote Request");
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "email", e));
         }
         return "Thank You For Your Business.<p>";
      }
   }
   
   public String drop()
   {
      try
      {
         String success = QuoteRequestEntityFactory.getInstance().getQuoteRequestEntityInstance().dropTable();
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success, this, commonStrings.DROP));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to drop QuoteRequest table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.DROP, e));
         }
         return error;
      }
   }
   
   public String create()
   {
      try
      {
         String success = QuoteRequestEntityFactory.getInstance().getQuoteRequestEntityInstance().createTable();
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success, this, "create()"));
         }
         
         return success;
      }
      catch(Exception e)
      {
         String error= "Failed to create new QuoteRequest table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "create()", e));
         }
         return error;
      }
   }
   
   public String restore()
   {
      try
      {
         final String success = "Restore Successful";
         final String result = AbSqlTableUtil.getInstance().restoreTable(QuoteRequestEntityFactory.getInstance().getQuoteRequestEntityInstance(), portion);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success, this, "restore()"));
         }
         
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to restore backup";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "restore()", e));
         }
         
         return error;
      }
   }
   
   public String backup()
   {
      try
      {
         final String success = "Restore Successful";
         final String result = AbSqlTableUtil.getInstance().backupTable(QuoteRequestEntityFactory.getInstance().getQuoteRequestEntityInstance());
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success, this, "backup()"));
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to make backup";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "backup()", e));
         }
         return error;
      }
   }
}
