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

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.UserInterface;
import allbinary.logic.communication.smtp.event.UserEmailEventListenerInterface;
import allbinary.logic.communication.smtp.event.UserEmailEventNameData;

import java.util.HashMap;

public class UserEmailEventsConfiguration implements UserEmailEventsConfigurationInterface
{
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
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructor", this," Constructor"));
      }
   }

   public HashMap getEventConfigurationHashMap()
   {
      return this.emailEventHashMap;
   }
   
   public void addUserEmailEventConfiguration(UserEmailEventConfigurationInterface userEmailEventConfigurationInterface)
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Adding: " + userEmailEventConfigurationInterface.log(), this,"addUserEmailEventConfiguration"));
      }
      
      this.emailEventHashMap.put(
         userEmailEventConfigurationInterface.getName(), 
         userEmailEventConfigurationInterface);
   }

   //Retrieve the eventlistener associated with a named event
   public UserEmailEventListenerInterface getEventListener(
      UserEmailEventNameData userEmailEventNameData, 
      UserInterface userInterface)
      throws Exception
   {
      UserEmailEventConfigurationInterface userEmailEventConfigurationInterface = 
         (UserEmailEventConfigurationInterface) 
            this.emailEventHashMap.get(userEmailEventNameData.toString());

      return UserEmailEventListenerFactory.getInstance(
         userEmailEventConfigurationInterface, userInterface);
   }
}
