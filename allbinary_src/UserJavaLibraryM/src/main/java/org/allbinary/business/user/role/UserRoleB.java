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
package org.allbinary.business.user.role;

import java.util.Vector;

import org.allbinary.business.user.UserFactoryInterface;
import org.allbinary.logic.string.StringValidationUtil;

/**
 *
 * @author user
 */
public class UserRoleB extends UserRole
{
    public static Vector roleVector = new Vector();

    protected UserRoleB(
        BasicUserRole aBasicUserRole,
        UserFactoryInterface aUserFactoryInterface)
   {
        super(aBasicUserRole, aUserFactoryInterface);

        roleVector.add(this);
   }

   public static synchronized UserRole getRole(String role) throws Exception
   {
      UserRoleFactory.getInstance();
      if(!StringValidationUtil.getInstance().isEmpty(role))
      {
         final Vector roleVector = UserRoleB.getAll();
         final int size = roleVector.size();
         for(int index = 0; index < size; index++)
         {
            UserRole userRole = (UserRole) roleVector.get(index);

            if(role.compareTo(userRole.toString()) == 0)
            {
               return userRole;
            }
         }
      }
      throw new Exception("Unable to get role - Unknown Role = " + role);
   }

   public static Vector getAll()
   {
      UserRoleFactory.getInstance();
      return UserRoleB.roleVector;
   }

}
