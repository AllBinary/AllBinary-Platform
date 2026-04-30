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
package org.allbinary.data.tables.user;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.UserInterface;
import org.allbinary.business.user.role.UserRole;
import org.allbinary.data.tables.BasicDataTableInterface;

public interface UserEntityInterface extends BasicDataTableInterface
{
   Vector getAdministrators() throws Exception;
   
   Vector getStoreManagers(StoreFrontInterface storeFrontInterface) throws Exception;
   
   Vector getCustomers() throws Exception;

   Vector getUsersWithRole(UserRole userRole) throws Exception;
   
   Vector getUsers(StoreFrontInterface storeFrontInterface) throws Exception;
   
   UserInterface getUser(String userName) throws Exception;
      
   //public String getUserRole(String userName, String password);
   
   String login(String userName, String password);

   void deleteWhere(String key, String value);

   void insert(Vector values);
   
   void update(String userName, HashMap updatedValues);
}
