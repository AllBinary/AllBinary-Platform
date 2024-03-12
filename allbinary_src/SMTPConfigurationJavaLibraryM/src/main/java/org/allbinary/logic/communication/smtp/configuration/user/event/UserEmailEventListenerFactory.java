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
package org.allbinary.logic.communication.smtp.configuration.user.event;

import org.allbinary.logic.system.loader.AbeFactory;
import org.allbinary.business.user.UserInterface;
import org.allbinary.logic.communication.smtp.event.UserEmailEventListenerInterface;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class UserEmailEventListenerFactory
{
   private UserEmailEventListenerFactory()
   {
   }
      
   public static UserEmailEventListenerInterface getInstance(
       final AbeClientInformationInterface abeClientInformation,
       final UserEmailEventConfigurationInterface userEmailEventConfigurationInterface, 
       final UserInterface userInterface) 
      throws Exception
   {
      Object params[] = new Object[1];
      Class classes[] = new Class[1];
       
      classes[0] = UserInterface.class;
       
      params[0] = (Object) userInterface;
      
      return (UserEmailEventListenerInterface) AbeFactory.getInstance(abeClientInformation, 
          userEmailEventConfigurationInterface.getEventListenerClassPath(), classes, params);
   }
}
