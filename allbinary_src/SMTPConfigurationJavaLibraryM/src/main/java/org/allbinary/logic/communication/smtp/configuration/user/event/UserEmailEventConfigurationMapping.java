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
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import org.allbinary.data.tables.TableMappingInterface;
import org.allbinary.logic.StdUtil;

public class UserEmailEventConfigurationMapping implements TableMappingInterface
{
   private UserEmailEventConfigurationInterface userEmailEventConfigurationInterface;
   
   public UserEmailEventConfigurationMapping(UserEmailEventConfigurationInterface userEmailEventConfigurationInterface)
   {
      this.userEmailEventConfigurationInterface = userEmailEventConfigurationInterface;
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = StdUtil.getInstance().createHashMap();

      hashMap.put(UserEmailEventConfigurationData.NAME, this.userEmailEventConfigurationInterface.getName());
      hashMap.put(UserEmailEventConfigurationData.LISTENER_CLASSPATH, this.userEmailEventConfigurationInterface.getEventListenerClassPath());

      return hashMap;
   }
   
   public Object getKey() throws Exception
   {
      return UserEmailEventConfigurationData.NAME;
   }
   
   public BasicArrayList toVector() throws Exception
   {
      BasicArrayList vector = new BasicArrayListD();

      vector.add(this.userEmailEventConfigurationInterface.getName());
      vector.add(this.userEmailEventConfigurationInterface.getEventListenerClassPath());

      return vector;
   }   
}
