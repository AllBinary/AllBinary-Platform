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
package org.allbinary.logic.communication.smtp.event.handler.factory;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tables.user.UserEntityFactory;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.communication.smtp.event.handler.EmailEventHandlerUtil;
import org.allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;

import java.util.HashMap;
import java.util.Vector;

public class AdminUserEmailEventHandlerSingletons
{
	private static final AdminUserEmailEventHandlerSingletons instance = 
		new AdminUserEmailEventHandlerSingletons();
	
   private static HashMap userEmailEventHandlerHashMap = null;
   
   private AdminUserEmailEventHandlerSingletons()
   {
   }
   
   public static UserEmailEventHandler getInstance(UserEmailEventNameData userEmailEventNameData)
      throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Start", instance, "getInstance"));
      }
      
      if(AdminUserEmailEventHandlerSingletons.userEmailEventHandlerHashMap == null)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Initializing HashMap", instance, "getInstance"));
         }
         
         AdminUserEmailEventHandlerSingletons.userEmailEventHandlerHashMap = new HashMap();
      }
      
      UserEmailEventHandler userEmailEventHandler = (UserEmailEventHandler)
         AdminUserEmailEventHandlerSingletons.userEmailEventHandlerHashMap.get(
         userEmailEventNameData);
      
      //Load Info if not found - check logic - logic is poor should load on null above
      if(userEmailEventHandler == null)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Creating New Named UserEmailEventHandler", instance, "getInstance"));
         }
         
         //Each store admin user my subscribe to emails with their email configuration
         Vector userVector = UserEntityFactory.getInstance().getAdministrators();
         
         //Create New Handler and add listeners
         UserEmailEventHandler newUserEmailEventHandler =
            EmailEventHandlerUtil.getEventHandler(
               userEmailEventNameData, userVector);
         
         AdminUserEmailEventHandlerSingletons.userEmailEventHandlerHashMap.put(
            userEmailEventNameData, newUserEmailEventHandler);
         
         return newUserEmailEventHandler;
      }
      else
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Returning existing UserEmailEventHandler", instance, "getInstance"));
         }

         //Return existing event Handler
         return userEmailEventHandler;
      }
   }
}
