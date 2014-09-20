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
package org.allbinary.logic.communication.http.request.session;

public class WeblisketSessionData
{
   public WeblisketSessionData()
   {
   }
   
   public static final String INVALIDATESESSION = "SESSION_INVALIDATE_SESSION";
   
   public static final String ATTEMPTS = "SESSION_ATTEMPTS";
   
   public static final String TIMEOUT = "SESSION_TIMEOUT";
   
   public static final String REMOVABLEUSERNAME = "SESSION_REMOVABLE_USER_NAME";
   public static final String REMOVABLEPASSWORD = "SESSION_REMOVABLE_PASSWORD";      
   public static final String REMOVABLENEWPASSWORD = "SESSION_REMOVABLE_NEW_PASSWORD";   
   public static final String REMOVABLEREENTERNEWPASSWORD = "SESSION_REMOVABLE_REENTER_NEW_PASSWORD";      
   
   public static final String AUTHENTICATED = "SESSION_AUTHENTICATED";
}
