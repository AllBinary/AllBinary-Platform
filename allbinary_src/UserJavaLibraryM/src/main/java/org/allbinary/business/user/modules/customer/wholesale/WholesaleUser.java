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
package org.allbinary.business.user.modules.customer.wholesale;

import java.util.HashMap;

import org.allbinary.business.user.modules.User;
import org.allbinary.logic.communication.http.request.session.WeblisketSessionInterface;

public class WholesaleUser extends User
{
   public WholesaleUser() throws Exception
   {
      super();
   }

   public WholesaleUser(HashMap userHashMap) throws Exception
   {
      super(userHashMap);
   }

   public void validateSession(WeblisketSessionInterface weblisketSession)
   {
      super.validateSession(weblisketSession);
      this.updateSession(weblisketSession);
   }

   public void updateSession(WeblisketSessionInterface weblisketSession)
   {
      weblisketSession.setStoreName(this.getPermissions());
   }   
   
   /*
   public void validateSession(WeblisketSession weblisketSession, RequestParams requestParams)
   {
      super.validateSession(weblisketSession, requestParams);
      this.updateSession(weblisketSession, requestParams);
   }

   public void updateSession(WeblisketSession weblisketSession, RequestParams requestParams)
   {
      String storeName = (String) requestParams.toHashMap().get(UserData.PERMISSIONS);
      weblisketSession.setStoreName(storeName);
   }   
   
   public boolean hasPermission(RequestParams requestParams)
   {
      String storeName = (String) requestParams.toHashMap().get(UserData.PERMISSIONS);
      if(storeName!=null && this.getPermissions().compareTo(storeName)==0)
      {
         return true;
      }
      return false;
   } 
    */  
}
