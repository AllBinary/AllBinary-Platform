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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;

public class UserEmailEventConfiguration implements UserEmailEventConfigurationInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();
  
   private String name;
   
   //contains a vector of class paths to the event listeners
   //associated with this email event configuration
   private String eventListenerClassPath;

   public UserEmailEventConfiguration()
   {
      this.setName(StringUtil.getInstance().EMPTY_STRING);
      this.setEventListenerClassPath(StringUtil.getInstance().EMPTY_STRING);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
         logUtil.put("New", this," Constructor");
      }
   }
   
   public UserEmailEventConfiguration(HashMap hashMap)
   {
      this.setName((String) hashMap.get(UserEmailEventConfigurationData.NAME));
      this.setEventListenerClassPath((String) hashMap.get(UserEmailEventConfigurationData.LISTENER_CLASSPATH));

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
         logUtil.put("Log: " + this.log(), this," Constructor");
      }
   }
   
   public String getName()
   {
      return name;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public String getEventListenerClassPath()
   {
      return eventListenerClassPath;
   }
   
   public void setEventListenerClassPath(String eventListenerClassPath)
   {
      this.eventListenerClassPath = eventListenerClassPath;
   }
   
   public String log()
   {
      return "\nUser Email Event Log Info:" + 
         "\nName: " + this.getName() + 
         "\nEventListenerClassPath: " + this.getEventListenerClassPath();
   }
}
