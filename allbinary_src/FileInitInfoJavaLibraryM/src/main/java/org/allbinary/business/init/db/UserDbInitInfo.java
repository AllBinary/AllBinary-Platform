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
package org.allbinary.business.init.db;

public class UserDbInitInfo extends DbInitInfo
{
   
   private static final String INITFILE = "userdbinitdata.dat";

   public UserDbInitInfo()
   {
      super(INITFILE, true);
   }

   public UserDbInitInfo(boolean read)
   {
      super(INITFILE, read);
   }
}
