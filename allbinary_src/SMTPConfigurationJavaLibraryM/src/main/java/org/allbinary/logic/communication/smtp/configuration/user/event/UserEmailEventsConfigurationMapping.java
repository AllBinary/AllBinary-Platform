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
      HashMap hashMap = StdUtil.getInstance().createHashMap();

      return hashMap;
   }
   
   public Object getKey() throws Exception
   {
      return UserEmailEventsConfigurationData.NAME;
   }
   
   public BasicArrayList toVector() throws Exception
   {
      BasicArrayList vector = new BasicArrayListD();

      return vector;
   }   
}
