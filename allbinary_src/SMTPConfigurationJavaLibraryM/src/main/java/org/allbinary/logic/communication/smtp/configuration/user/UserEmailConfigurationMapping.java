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

import org.allbinary.logic.communication.smtp.configuration.user.UserEmailConfigurationData;
import org.allbinary.data.tables.TableMappingInterface;

import java.util.HashMap;
import java.util.Vector;

public class UserEmailConfigurationMapping implements TableMappingInterface
{
   private UserEmailConfigurationInterface userEmailConfigurationInterface;
   
   public UserEmailConfigurationMapping(
      UserEmailConfigurationInterface userEmailConfigurationInterface)
   {
      this.userEmailConfigurationInterface = userEmailConfigurationInterface;
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();

      return hashMap;
   }
   
   public Object getKey() throws Exception
   {
      return UserEmailConfigurationData.NAME;
   }
   
   public Vector toVector() throws Exception
   {
      Vector vector = new Vector();

      return vector;
   }   
}
