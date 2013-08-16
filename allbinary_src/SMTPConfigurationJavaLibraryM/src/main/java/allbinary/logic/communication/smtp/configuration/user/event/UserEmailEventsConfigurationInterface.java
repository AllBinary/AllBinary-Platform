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

import allbinary.business.user.UserInterface;
import allbinary.logic.communication.smtp.event.UserEmailEventListenerInterface;
import allbinary.logic.communication.smtp.event.UserEmailEventNameData;

import java.util.HashMap;

public interface UserEmailEventsConfigurationInterface
{
   public HashMap getEventConfigurationHashMap();
   
   public void addUserEmailEventConfiguration(UserEmailEventConfigurationInterface userEmailEventConfigurationInterface);
   
   public UserEmailEventListenerInterface getEventListener(
      UserEmailEventNameData userEmailEventNameData, 
      UserInterface userInterface) throws Exception;   
}
