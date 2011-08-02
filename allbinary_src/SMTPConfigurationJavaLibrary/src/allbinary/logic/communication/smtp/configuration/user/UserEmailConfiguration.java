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
package allbinary.logic.communication.smtp.configuration.user;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.communication.smtp.configuration.user.event.UserEmailEventsConfiguration;
import allbinary.logic.communication.smtp.configuration.user.event.UserEmailEventsConfigurationInterface;

import java.util.HashMap;

public class UserEmailConfiguration implements UserEmailConfigurationInterface
{
   private UserEmailEventsConfigurationInterface userEmailEventsConfigurationInterface;
   
   public UserEmailConfiguration()
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructor", this,"Constructor"));
      }
      
      this.setUserEmailEventsConfigurationInterface(new UserEmailEventsConfiguration());
   }
   
   public UserEmailConfiguration(HashMap hashMap)
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructor", this, "Constructor"));
      }
      
      this.setUserEmailEventsConfigurationInterface(new UserEmailEventsConfiguration(hashMap));
   }

    public UserEmailEventsConfigurationInterface getUserEmailEventsConfigurationInterface()
    {
        return this.userEmailEventsConfigurationInterface;
    }

    public void setUserEmailEventsConfigurationInterface(UserEmailEventsConfigurationInterface userEmailEventsConfigurationInterface)
    {
        this.userEmailEventsConfigurationInterface = userEmailEventsConfigurationInterface;
    }
}
