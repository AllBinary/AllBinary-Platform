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

import java.io.Serializable;

import org.allbinary.business.user.UserFactoryInterface;

public class UserRole implements Serializable
{
   private BasicUserRole basicUserRole;
   private UserFactoryInterface userFactoryInterface;
   
   protected UserRole(BasicUserRole aBasicUserRole,
      UserFactoryInterface aUserFactoryInterface)
   {
      this.basicUserRole = aBasicUserRole;
      this.userFactoryInterface = aUserFactoryInterface;
   }
         
   public String toString()
   {
      return this.basicUserRole.toString();
   }
   
   public BasicUserRole getBasicUserRole()
   {
      return this.basicUserRole;
   }
   
   public UserFactoryInterface getUserFactory()
   throws Exception
   {
      return this.userFactoryInterface;
   }
   
   public long getSessionTimeout()
   {
      return this.basicUserRole.getSessionTimeout();
   }
   
   public long getSessionInactivityTimeout()
   {
      return this.basicUserRole.getSessionInactivityTimeout();
   }   
}