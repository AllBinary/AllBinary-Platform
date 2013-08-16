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
package allbinary.logic.communication.smtp.event.handler.factory;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tables.user.UserEntityFactory;
import allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import allbinary.logic.communication.smtp.event.handler.EmailEventHandlerUtil;
import allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;

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
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Start", instance, "getInstance"));
      }
      
      if(AdminUserEmailEventHandlerSingletons.userEmailEventHandlerHashMap == null)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Returning existing UserEmailEventHandler", instance, "getInstance"));
         }

         //Return existing event Handler
         return userEmailEventHandler;
      }
   }
}
