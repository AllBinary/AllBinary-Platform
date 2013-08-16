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
package allbinary.logic.communication.smtp.event.modules.log;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.communication.smtp.event.EmailEvent;
import allbinary.logic.communication.smtp.event.UserEmailEventListenerInterface;

public class LogUserEmailEventListenerModule 
   implements UserEmailEventListenerInterface
{
   public LogUserEmailEventListenerModule()
   {
   }

   public void onEmailSendRequest(EmailEvent userEmailInfoEvent) throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         String message = "Logging EmailEvent - Temporary Listener for debugging.\n\n" + userEmailInfoEvent.toString();

         LogUtil.put(LogFactory.getInstance(message, this, "onEmailSendRequest"));
      }
   }
}
