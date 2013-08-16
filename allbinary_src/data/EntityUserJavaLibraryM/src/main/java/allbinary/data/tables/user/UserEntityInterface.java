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
package allbinary.data.tables.user;

import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.user.UserInterface;
import allbinary.business.user.role.UserRole;
import allbinary.data.tables.BasicTableInterface;

import java.util.HashMap;
import java.util.Vector;

public interface UserEntityInterface extends BasicTableInterface
{
   public Vector getAdministrators() throws Exception;
   
   public Vector getStoreManagers(StoreFrontInterface storeFrontInterface) throws Exception;
   
   public Vector getCustomers() throws Exception;

   public Vector getUsersWithRole(UserRole userRole) throws Exception;
   
   public Vector getUsers(StoreFrontInterface storeFrontInterface) throws Exception;
   
   public UserInterface getUser(String userName) throws Exception;
      
   //public String getUserRole(String userName, String password);
   
   public String login(String userName, String password);

   public void deleteWhere(String key, String value);

   public void insert(Vector values);
   
   public void update(String userName, HashMap updatedValues);
}
