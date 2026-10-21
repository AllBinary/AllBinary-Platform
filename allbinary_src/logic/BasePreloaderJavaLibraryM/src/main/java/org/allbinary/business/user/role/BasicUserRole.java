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

public class BasicUserRole implements Serializable
{
   
   private final int m_int_RoleId;
   private final String role;
   private final String displayValue;
   private final long sessionTimeout;
   private final long sessionInactivityTimeout;

   BasicUserRole(String aRole, int a_int_RoleId,
      long aSessionTimeout, long aSessionInactivityTimeout)
   {
      this(aRole, aRole, a_int_RoleId, aSessionTimeout, aSessionInactivityTimeout);
   }

   BasicUserRole(String aRole, String aDisplayValue, int a_int_RoleId,
      long aSessionTimeout, long aSessionInactivityTimeout)
   {
      this.m_int_RoleId = a_int_RoleId;
      this.role = aRole;
      this.displayValue = aDisplayValue;
      this.sessionTimeout = aSessionTimeout;
      this.sessionInactivityTimeout = aSessionInactivityTimeout;

      BasicUserRoles.getInstance().roleVector.add(this);
   }

   private int getRoleId()
   {
      return this.m_int_RoleId;
   }

   public String getRole()
   {
      return this.role;
   }

   public long getSessionTimeout()
   {
      return this.sessionTimeout;
   }

   public long getSessionInactivityTimeout()
   {
      return this.sessionInactivityTimeout;
   }

   public boolean equals(BasicUserRole basicUserRole)
   {
      if(this.getRoleId() == basicUserRole.getRoleId())
      {
         return true;
      }
      return false;
   }

   public String toString()
   {
      return this.getRole();
   }

}