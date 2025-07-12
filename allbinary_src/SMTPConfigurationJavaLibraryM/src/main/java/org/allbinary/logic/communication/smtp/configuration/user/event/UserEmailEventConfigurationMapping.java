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
import java.util.Vector;

import org.allbinary.data.tables.TableMappingInterface;

public class UserEmailEventConfigurationMapping implements TableMappingInterface
{
   private UserEmailEventConfigurationInterface userEmailEventConfigurationInterface;
   
   public UserEmailEventConfigurationMapping(UserEmailEventConfigurationInterface userEmailEventConfigurationInterface)
   {
      this.userEmailEventConfigurationInterface = userEmailEventConfigurationInterface;
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();

      hashMap.put(UserEmailEventConfigurationData.NAME, this.userEmailEventConfigurationInterface.getName());
      hashMap.put(UserEmailEventConfigurationData.LISTENER_CLASSPATH, this.userEmailEventConfigurationInterface.getEventListenerClassPath());

      return hashMap;
   }
   
   public Object getKey() throws Exception
   {
      return UserEmailEventConfigurationData.NAME;
   }
   
   public Vector toVector() throws Exception
   {
      Vector vector = new Vector();

      vector.add(this.userEmailEventConfigurationInterface.getName());
      vector.add(this.userEmailEventConfigurationInterface.getEventListenerClassPath());

      return vector;
   }   
}
