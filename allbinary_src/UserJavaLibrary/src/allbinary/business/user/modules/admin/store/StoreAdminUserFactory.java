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
package allbinary.business.user.modules.admin.store;

import allbinary.business.user.UserFactoryInterface;
import allbinary.business.user.UserInterface;

import java.util.HashMap;

public class StoreAdminUserFactory implements UserFactoryInterface
{
   public StoreAdminUserFactory()
   {
   }

   public UserInterface getInstance() throws Exception
   {
      return new StoreAdminUser();
   }

   public UserInterface getInstance(HashMap hashMap) throws Exception
   {
      return new StoreAdminUser(hashMap);
   }
}