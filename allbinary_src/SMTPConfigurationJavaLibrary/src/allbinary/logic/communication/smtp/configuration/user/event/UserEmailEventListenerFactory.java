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
package allbinary.logic.communication.smtp.configuration.user.event;

import abcs.logic.system.loader.AbeFactory;
import allbinary.business.user.UserInterface;
import allbinary.logic.communication.smtp.event.UserEmailEventListenerInterface;

public class UserEmailEventListenerFactory
{
   private UserEmailEventListenerFactory()
   {
   }
      
   public static UserEmailEventListenerInterface getInstance(
      UserEmailEventConfigurationInterface userEmailEventConfigurationInterface, 
      UserInterface userInterface) 
      throws Exception
   {
      Object params[] = new Object[1];
      Class classes[] = new Class[1];
       
      classes[0] = UserInterface.class;
       
      params[0] = (Object) userInterface;
      
      return (UserEmailEventListenerInterface) AbeFactory.getInstance(
         userEmailEventConfigurationInterface.getEventListenerClassPath(), classes, params);
   }
}
