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
import org.allbinary.business.user.UserInterface;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.communication.smtp.event.handler.EmailEventHandlerUtil;
import org.allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;
import org.allbinary.logic.communication.smtp.event.modules.log.LogUserEmailEventListenerModule;

import java.util.HashMap;
import java.util.Vector;

public class UserEmailEventHandlerSingletons
{
	private static final UserEmailEventHandlerSingletons instance = 
		new UserEmailEventHandlerSingletons();
	
   private static HashMap userEmailEventHandlerHashMap = null;
   
   private UserEmailEventHandlerSingletons()
   {
   }

   public static UserEmailEventHandler getInstance(
      UserEmailEventNameData userEmailEventNameData, 
      UserInterface userInterface)
      throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Start", instance, "getInstance"));
      }
      
      if(UserEmailEventHandlerSingletons.userEmailEventHandlerHashMap == null)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Initializing HashMap", instance, "getInstance"));
         }
         
         UserEmailEventHandlerSingletons.userEmailEventHandlerHashMap = new HashMap();
      }
      
      UserEmailEventHandler userEmailEventHandler = (UserEmailEventHandler)
         UserEmailEventHandlerSingletons.userEmailEventHandlerHashMap.get(
         userEmailEventNameData);
      
      if(userEmailEventHandler == null)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Creating New Named UserEmailEventHandler",
               instance, "getInstance"));
         }
         
         //Create New Handler and add listeners
         UserEmailEventHandler newUserEmailEventHandler = new UserEmailEventHandler();

         Vector vector = 
            EmailEventHandlerUtil.getUserEmailEventListenerVector(
               userEmailEventNameData, userInterface);
         newUserEmailEventHandler.addListener(vector);
         newUserEmailEventHandler.addListener(new LogUserEmailEventListenerModule());
         
         UserEmailEventHandlerSingletons.userEmailEventHandlerHashMap.put(
            userEmailEventNameData, newUserEmailEventHandler);

         return newUserEmailEventHandler;
      }
      else
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Returning existing UserEmailEventHandler",
               instance, "getInstance"));
         }

         //Return existing event Handler
         return userEmailEventHandler;
      }
   }

}
