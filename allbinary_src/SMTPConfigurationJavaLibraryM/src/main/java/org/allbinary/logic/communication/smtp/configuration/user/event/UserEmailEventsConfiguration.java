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
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.smtp.event.UserEmailEventListenerInterface;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;

public class UserEmailEventsConfiguration implements UserEmailEventsConfigurationInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private HashMap emailEventHashMap;
   
   public UserEmailEventsConfiguration()
   {
      
      this.init();
   }
   
   public UserEmailEventsConfiguration(HashMap hashMap)
   {
      this.init();
      //this.emailEventVector = EmailEventVectorFactory.getInstance(hashMap);
   }
   
   private void init()
   {
      this.emailEventHashMap = new HashMap();
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
         logUtil.put(this.commonStrings.CONSTRUCTOR, this," Constructor");
      }
   }

   public HashMap getEventConfigurationHashMap()
   {
      return this.emailEventHashMap;
   }
   
   public void addUserEmailEventConfiguration(UserEmailEventConfigurationInterface userEmailEventConfigurationInterface)
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
         logUtil.put("Adding: " + userEmailEventConfigurationInterface.log(), this,"addUserEmailEventConfiguration");
      }
      
      this.emailEventHashMap.put(
         userEmailEventConfigurationInterface.getName(), 
         userEmailEventConfigurationInterface);
   }

   //Retrieve the eventlistener associated with a named event
   public UserEmailEventListenerInterface getEventListener(
       final AbeClientInformationInterface abeClientInformation,
       final UserEmailEventNameData userEmailEventNameData, 
       final UserInterface userInterface)
       throws Exception
   {
      final UserEmailEventConfigurationInterface userEmailEventConfigurationInterface = 
         (UserEmailEventConfigurationInterface) 
            this.emailEventHashMap.get(userEmailEventNameData.toString());

      return UserEmailEventListenerFactory.getInstance(abeClientInformation,
         userEmailEventConfigurationInterface, userInterface);
   }
}
