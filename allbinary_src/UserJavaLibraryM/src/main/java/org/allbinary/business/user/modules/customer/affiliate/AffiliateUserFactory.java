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
package org.allbinary.business.user.modules.customer.affiliate;

import java.util.HashMap;

import org.allbinary.business.user.UserFactoryInterface;
import org.allbinary.business.user.UserInterface;

public class AffiliateUserFactory implements UserFactoryInterface
{
   public AffiliateUserFactory()
   {
   }

   public UserInterface getInstance() throws Exception
   {
      return new AffiliateUser();
   }

   public UserInterface getInstance(HashMap hashMap) throws Exception
   {
      return new AffiliateUser(hashMap);
   }
}