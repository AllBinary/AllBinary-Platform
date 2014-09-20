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
package org.allbinary.logic.communication.smtp.configuration.user;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.smtp.configuration.user.event.UserEmailEventsConfiguration;
import org.allbinary.logic.communication.smtp.configuration.user.event.UserEmailEventsConfigurationInterface;

import java.util.HashMap;

public class UserEmailConfiguration implements UserEmailConfigurationInterface
{
   private UserEmailEventsConfigurationInterface userEmailEventsConfigurationInterface;
   
   public UserEmailConfiguration()
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructor", this,"Constructor"));
      }
      
      this.setUserEmailEventsConfigurationInterface(new UserEmailEventsConfiguration());
   }
   
   public UserEmailConfiguration(HashMap hashMap)
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
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
