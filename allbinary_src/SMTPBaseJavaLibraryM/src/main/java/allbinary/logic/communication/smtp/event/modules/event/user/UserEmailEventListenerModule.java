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
package allbinary.logic.communication.smtp.event.modules.event.user;

import abcs.logic.communication.log.LogFactory;
 import abcs.logic.communication.log.LogUtil;
 import allbinary.business.user.UserInterface;
 import allbinary.logic.communication.smtp.BasicEmail;
 import allbinary.logic.communication.smtp.EmailInterface;
 import allbinary.logic.communication.smtp.event.EmailEvent;
 import allbinary.logic.communication.smtp.event.UserEmailEventListenerInterface;
 import allbinary.logic.communication.smtp.queue.EmailQueueFactory;

public class UserEmailEventListenerModule 
   implements UserEmailEventListenerInterface
{
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
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Add Email To Que For Sending: " + emailEvent.toString(), this, "onEmailSendRequest"));
      }
      
      String to = this.userInterface.getMainEmail();

      BasicEmail email = new BasicEmail(emailEvent.getEmailInfo(), to);

      EmailQueueFactory.getInstance().offer((EmailInterface) email.getEmail());
   }
}
