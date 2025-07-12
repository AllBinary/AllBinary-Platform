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
package org.allbinary.business.user;

import java.util.HashMap;

import org.allbinary.business.user.role.UserRole;
import org.allbinary.business.user.role.UserRoleB;
import org.allbinary.business.user.role.UserRoleData;
import org.allbinary.logic.string.StringValidationUtil;

public class CreateUserFactory
{
   private CreateUserFactory()
   {
   }

   public static UserInterface getInstance(HashMap hashMap) throws Exception
   {
      if(hashMap != null)
      {
         String roleString = (String) hashMap.get(UserRoleData.NAME.toString());
         
         if(!StringValidationUtil.getInstance().isEmpty(roleString))
         {
            UserRole role = UserRoleB.getRole(roleString);
            return role.getUserFactory().getInstance(hashMap);
         }
         else 
         {
            throw new Exception("No Role Specified");
         }
      }
      else 
      {
         throw new Exception("Null HashMap");
      }
   }   
}