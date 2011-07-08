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
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.data.tables.user.UserEntityFactory;
import allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import allbinary.logic.communication.smtp.event.handler.EmailEventHandlerUtil;
import allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;

import java.util.HashMap;
import java.util.Vector;

public class StoreAdminUserEmailEventHandlerSingletons
{
	private static final StoreAdminUserEmailEventHandlerSingletons instance = 
		new StoreAdminUserEmailEventHandlerSingletons();

   private static HashMap userEmailEventHandlerHashMap = null;
   
   private StoreAdminUserEmailEventHandlerSingletons()
   {
   }
   
   public static UserEmailEventHandler getInstance(
      UserEmailEventNameData userEmailEventNameData,
      StoreFrontInterface storeFrontInterface)
      throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Start", instance, "getInstance"));
      }
      
      if(StoreAdminUserEmailEventHandlerSingletons.userEmailEventHandlerHashMap == null)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Initializing HashMap", instance, "getInstance"));
         }
         
         StoreAdminUserEmailEventHandlerSingletons.userEmailEventHandlerHashMap = new HashMap();
      }
      
      UserEmailEventHandler userEmailEventHandler = (UserEmailEventHandler)
      StoreAdminUserEmailEventHandlerSingletons.userEmailEventHandlerHashMap.get(
         userEmailEventNameData);
      
      if(userEmailEventHandler == null)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Creating New Named UserEmailEventHandler", instance, "getInstance"));
         }
         
         //Each store admin user my subscribe to emails with their email configuration
         Vector userVector =
            UserEntityFactory.getInstance().getStoreManagers(storeFrontInterface);
         
         //Create New Handler and add listeners
         UserEmailEventHandler newUserEmailEventHandler =
            EmailEventHandlerUtil.getEventHandler(
               userEmailEventNameData, userVector);
         
         StoreAdminUserEmailEventHandlerSingletons.userEmailEventHandlerHashMap.put(
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
