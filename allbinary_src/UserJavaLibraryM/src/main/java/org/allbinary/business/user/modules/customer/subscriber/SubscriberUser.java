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
package org.allbinary.business.user.modules.customer.subscriber;

import java.util.HashMap;

import org.allbinary.business.user.modules.User;
import org.allbinary.business.user.subscription.Subscription;
import org.allbinary.logic.communication.http.request.session.WeblisketSessionInterface;

public class SubscriberUser extends User
{
   //Store Subscriber Info in another table or order
   private Subscription subscription;
   
   public SubscriberUser() throws Exception
   {
      super();
      this.subscription = new Subscription();
   }

   public SubscriberUser(HashMap userHashMap) throws Exception
   {
      super(userHashMap);
      this.subscription = new Subscription();
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
   
   public Boolean isSessionValid()
   {
      return this.subscription.isSubscribed();
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
