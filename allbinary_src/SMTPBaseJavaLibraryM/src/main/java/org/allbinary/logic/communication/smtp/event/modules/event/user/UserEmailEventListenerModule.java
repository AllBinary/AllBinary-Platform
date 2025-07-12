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
package org.allbinary.logic.communication.smtp.event.modules.event.user;

import org.allbinary.business.user.UserInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.smtp.BasicEmail;
import org.allbinary.logic.communication.smtp.EmailInterface;
import org.allbinary.logic.communication.smtp.event.EmailEvent;
import org.allbinary.logic.communication.smtp.event.UserEmailEventListenerInterface;
import org.allbinary.logic.communication.smtp.queue.EmailQueueFactory;

public class UserEmailEventListenerModule 
   implements UserEmailEventListenerInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private UserInterface userInterface;
   
   public UserEmailEventListenerModule(UserInterface userInterface)
   {
      this.userInterface = userInterface;
   }

   //Recieves the same email event that was sent when a error occurs
   public void onEmailSendFailure(EmailEvent emailEvent) 
      throws Exception
   {
      //Change the destination address errored
      String to = this.userInterface.getSecondaryEmail();

      //Future Imp - Change smtp server if server failed to send

      BasicEmail email = new BasicEmail(emailEvent.getEmailInfo(), to);

      EmailQueueFactory.getInstance().offer((EmailInterface) email.getEmail());
   }
   
   public void onEmailSendRequest(EmailEvent emailEvent) 
      throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
         logUtil.put("Add Email To Que For Sending: " + emailEvent.toString(), this, "onEmailSendRequest");
      }
      
      String to = this.userInterface.getMainEmail();

      BasicEmail email = new BasicEmail(emailEvent.getEmailInfo(), to);

      EmailQueueFactory.getInstance().offer((EmailInterface) email.getEmail());
   }
}
