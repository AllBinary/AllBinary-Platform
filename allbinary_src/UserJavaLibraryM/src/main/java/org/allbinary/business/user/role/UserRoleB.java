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

import org.allbinary.business.user.role.UserRole;
import org.allbinary.business.user.role.BasicUserRole;
import org.allbinary.StringValidationUtil;
import org.allbinary.business.user.UserFactoryInterface;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author user
 */
public class UserRoleB extends UserRole
{
    public static Vector roleVector = new Vector();

    static
    {
        Class clazz = UserRoleFactory.class;
    }

    protected UserRoleB(
        BasicUserRole aBasicUserRole,
        UserFactoryInterface aUserFactoryInterface)
   {
        super(aBasicUserRole, aUserFactoryInterface);

        roleVector.add(this);
   }

   public static synchronized UserRole getRole(String role) throws Exception
   {
      if(!StringValidationUtil.getInstance().isEmpty(role))
      {
         Vector roleVector = UserRoleB.getAll();
         Iterator iter = roleVector.iterator();
         while(iter.hasNext())
         {
            UserRole userRole = (UserRole) iter.next();

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
      return UserRoleB.roleVector;
   }

}
