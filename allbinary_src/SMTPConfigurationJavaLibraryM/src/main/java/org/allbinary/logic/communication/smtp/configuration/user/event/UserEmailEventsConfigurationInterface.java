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

import java.util.HashMap;

import org.allbinary.business.user.UserInterface;
import org.allbinary.logic.communication.smtp.event.UserEmailEventListenerInterface;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public interface UserEmailEventsConfigurationInterface
{
   public HashMap getEventConfigurationHashMap();
   
   public void addUserEmailEventConfiguration(UserEmailEventConfigurationInterface userEmailEventConfigurationInterface);
   
   public UserEmailEventListenerInterface getEventListener(
       final AbeClientInformationInterface abeClientInformation,
       final UserEmailEventNameData userEmailEventNameData, 
       final UserInterface userInterface) throws Exception;

}
