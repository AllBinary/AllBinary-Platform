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
package org.allbinary.business.context.modules.storefront.statistics.users;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.UserInterface;
import org.allbinary.business.user.role.UserRole;
import org.allbinary.data.tables.user.UserEntityFactory;
import org.allbinary.data.tables.user.UserEntityInterface;

public class StoreFrontUsersStatistics implements StoreFrontUsersStatisticsInterface
{
   private Long totalNumberOfUsers;
   private HashMap totalUsersByRoleHashMap;
   
   public StoreFrontUsersStatistics(StoreFrontInterface storeFrontInterface) throws Exception
   {
      this.totalUsersByRoleHashMap = new HashMap();
      final UserEntityInterface userEntityInterface = UserEntityFactory.getInstance();
      //Vector userVector = userEntityInterface.getUsers(storeFrontInterface);
      final Vector userVector = userEntityInterface.getCustomers();
      this.totalNumberOfUsers = new Long(userVector.size());
      
      final int size = userVector.size();
      for (int index = 0; index < size; index++)      
      {
         UserInterface userInterface = (UserInterface) userVector.get(index);
         UserRole nextUserRole = userInterface.getRole();
         
         Long currentNumberOfUsersForRole = this.getNewTotal(nextUserRole);

         totalUsersByRoleHashMap.put(nextUserRole, currentNumberOfUsersForRole);
      }
   }

   private Long getNewTotal(UserRole userRole)
   {
       Long numberOfUsersForRoleLong = (Long) totalUsersByRoleHashMap.get(userRole);
       
       if(numberOfUsersForRoleLong == null)
       {
           numberOfUsersForRoleLong = new Long(0);
       }
       
       return new Long(numberOfUsersForRoleLong.longValue() + 1);
   }

   public Long getNumberOfUsers()
   {
      return this.totalNumberOfUsers;
   }

   public Long getNumberOfUsersByRole(String role)
   {
      Long totalForRole = (Long) this.totalUsersByRoleHashMap.get(role);
      return totalForRole;
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      
      hashMap.put(StoreFrontUsersStatisticsData.getInstance().NUMBEROFUSERS, this.getNumberOfUsers().toString());
      
      Set setOfUserRoles = this.totalUsersByRoleHashMap.keySet();
      
      UserRole nextUserRole;
      
      final Object[] userRoleArray = setOfUserRoles.toArray();
      final int size = userRoleArray.length;
      for (int index = 0; index < size; index++)      
      {
         nextUserRole = (UserRole) userRoleArray[index];
	 Long totalForRole = (Long) this.totalUsersByRoleHashMap.get(nextUserRole);
         hashMap.put(nextUserRole.toString(), totalForRole.toString());
      }
      return hashMap;
   }
   
   public Vector toVector()
   {
      return null;
   }

   public Object getKey()
   {
      return null;
   }
   
}