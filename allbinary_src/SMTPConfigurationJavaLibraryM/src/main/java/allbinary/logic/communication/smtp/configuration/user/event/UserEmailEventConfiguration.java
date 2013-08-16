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

import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

import java.util.HashMap;

public class UserEmailEventConfiguration implements UserEmailEventConfigurationInterface
{  
   private String name;
   
   //contains a vector of class paths to the event listeners
   //associated with this email event configuration
   private String eventListenerClassPath;

   public UserEmailEventConfiguration()
   {
      this.setName(StringUtil.getInstance().EMPTY_STRING);
      this.setEventListenerClassPath(StringUtil.getInstance().EMPTY_STRING);

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("New", this," Constructor"));
      }
   }
   
   public UserEmailEventConfiguration(HashMap hashMap)
   {
      this.setName((String) hashMap.get(UserEmailEventConfigurationData.NAME));
      this.setEventListenerClassPath((String) hashMap.get(UserEmailEventConfigurationData.LISTENER_CLASSPATH));

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Log: " + this.log(), this," Constructor"));
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
