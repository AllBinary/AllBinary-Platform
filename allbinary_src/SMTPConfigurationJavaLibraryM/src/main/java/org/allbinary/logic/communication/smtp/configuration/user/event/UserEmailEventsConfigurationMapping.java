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

public class UserEmailEventsConfigurationMapping implements TableMappingInterface
{
   private UserEmailEventsConfigurationInterface userEmailEventsConfigurationInterface;
   
   public UserEmailEventsConfigurationMapping(
      UserEmailEventsConfigurationInterface userEmailEventsConfigurationInterface)
   {
      this.userEmailEventsConfigurationInterface = userEmailEventsConfigurationInterface;
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();

      return hashMap;
   }
   
   public Object getKey() throws Exception
   {
      return UserEmailEventsConfigurationData.NAME;
   }
   
   public Vector toVector() throws Exception
   {
      Vector vector = new Vector();

      return vector;
   }   
}
