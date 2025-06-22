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
package org.allbinary.logic.communication.smtp.email;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.UserInterface;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;
import org.allbinary.logic.communication.smtp.event.handler.factory.AdminUserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.event.handler.factory.UserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.info.AdminEmailInfo;
import org.allbinary.logic.communication.smtp.info.BasicEmailInfo;
import org.allbinary.logic.communication.smtp.info.EmailInfo;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;

public class NewPasswordEmail
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final AbeClientInformationInterface abeClientInformation;
    
   private UserInterface userInterface;
   private String newPassword;
   
   public NewPasswordEmail(final AbeClientInformationInterface abeClientInformation, final UserInterface userInterface, String newPassword)
   {
       this.abeClientInformation = abeClientInformation;
      this.userInterface = userInterface;
      this.newPassword = newPassword;
   }

   public void process() throws Exception
   {
      this.notifyStoreAdmin();
      this.notifyUser();
   }
   
   private void notifyStoreAdmin() throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("notifyStoreAdmin", this, "notifyStoreAdmin"));
         }

         String emailSubject = 
            "New Password For User: " + this.userInterface.getUserName();
         String emailBody = "New Password: " + this.newPassword;

         BasicEmailInfo basicEmailInfo = (BasicEmailInfo)
            new AdminEmailInfo(emailSubject, emailBody);

         /*
         BasicEmailInfo storeAdminBasicEmailInfo = (BasicEmailInfo)
            new StoreEmailInfo(this.storeFrontInterface, 
            emailSubject, emailTextBody);
         */
         
         //EmailInfo storeAdminEmailInfo = new EmailInfo(storeAdminBasicEmailInfo);

         EmailInfo emailInfo = new EmailInfo(basicEmailInfo);

         //Send response to Admin(s)
         UserEmailEventHandler adminUserEmailEventHandler =
            AdminUserEmailEventHandlerSingletons.getInstance().getInstance(
                abeClientInformation, UserEmailEventNameData.NEWPASSWORD);

         /*
         UserEmailEventHandler storeAdminUserEmailEventHandler =
            StoreAdminUserEmailEventHandlerSingletons.getInstance(
               UserEmailEventNameData.NEWPASSWORD,
               this.storeFrontInterface);

          */

         //storeAdminUserEmailEventHandler.receiveEmailInfo(emailInfo);
         adminUserEmailEventHandler.receiveEmailInfo(UserEmailEventNameData.NEWPASSWORD, emailInfo);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, "emailAdmin", e));
         }
         //throw e;
      }
   }

   ///String subject, String textBody
   private void notifyUser() throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Email User", this, "notifyUser()"));
         }

         String subject = "New Password";
         String body = "New Password: " + newPassword;

         /*
         BasicEmailInfo basicEmailInfo = (BasicEmailInfo)
            new StoreEmailInfo(this.storeFrontInterface, subject, body);
         */

         BasicEmailInfo basicEmailInfo = (BasicEmailInfo)
            new AdminEmailInfo(subject, body);
         
         EmailInfo emailInfo = new EmailInfo(basicEmailInfo);

         UserEmailEventHandler userEmailEventHandler =
            UserEmailEventHandlerSingletons.getInstance().getInstance(
               abeClientInformation, UserEmailEventNameData.NEWPASSWORD, this.userInterface);

         userEmailEventHandler.receiveEmailInfo(UserEmailEventNameData.NEWPASSWORD, emailInfo);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, "notifyUser", e));
         }
         throw e;
      }
   }   
}
