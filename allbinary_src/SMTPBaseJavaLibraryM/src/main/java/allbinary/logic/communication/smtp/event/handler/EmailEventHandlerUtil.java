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
package allbinary.logic.communication.smtp.event.handler;

import java.util.Vector;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.UserInterface;
import allbinary.business.user.modules.configuration.UserConfigurationInterface;
import allbinary.logic.communication.smtp.configuration.user.UserEmailConfigurationInterface;
import allbinary.logic.communication.smtp.configuration.user.event.UserEmailEventsConfigurationInterface;
import allbinary.logic.communication.smtp.event.UserEmailEventListenerInterface;
import allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import allbinary.logic.communication.smtp.event.modules.log.LogUserEmailEventListenerModule;

public class EmailEventHandlerUtil
{
	private static final EmailEventHandlerUtil instance = new EmailEventHandlerUtil();
	
   private EmailEventHandlerUtil()
   {
   }
   
   //send request to store admins if subscribed to handler
   /*
   public static void send(
      EmailInfo storeAdminEmailInfo, 
      StoreFrontInterface storeFrontInterface)
   {
      UserEmailInfoEventHandler userEmailInfoEventHandler = 
         UserEmailInfoEventHandlerSingleton.getInstance();
      userEmailInfoEventHandler.receiveEmailInfo(storeAdminUserEmailInfo);
   }
   
   //send request to admins if subscribed to handler
   public static void send(EmailInfo adminEmailInfo)
   {
      UserEmailInfoEventHandler userEmailInfoEventHandler = 
         UserEmailInfoEventHandlerSingleton.getInstance(
            UserEmailInfoEventHandlerNameData.QUOTEREQUEST);
      userEmailInfoEventHandler.receiveEmailInfo(adminUserEmailInfo);
   }
    **/
   
   public static UserEmailEventHandler getEventHandler(UserEmailEventNameData userEmailEventNameData, Vector userVector)
   throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
    	  StringBuffer stringBuffer = new StringBuffer();
    	  
    	  stringBuffer.append("Returning UserEmailEventHandler for ");
    	  stringBuffer.append(userVector.size());
    	  stringBuffer.append(" users.");
    	  
    	  LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "getEventHandler"));
      }
      
      UserEmailEventHandler userEmailEventHandler = new UserEmailEventHandler();
      
      userEmailEventHandler.addListener(new LogUserEmailEventListenerModule());

      int size = userVector.size();
      for(int index = 0; index < size; index++)
      {
         UserInterface userInterface = 
        	 (UserInterface) userVector.get(index);

         Vector vector = 
            EmailEventHandlerUtil.getUserEmailEventListenerVector(
               userEmailEventNameData, userInterface);
         
         userEmailEventHandler.addListener(vector);
      }
      
      return userEmailEventHandler;
   }

   public static Vector getUserEmailEventListenerVector(
      UserEmailEventNameData userEmailEventNameData, UserInterface userInterface)
      throws Exception
   {
         //Currently uses a single user configuration
         UserConfigurationInterface userConfigurationInterface = 
            userInterface.getUserConfigurationInterface();
         
         UserEmailConfigurationInterface userEmailConfigurationInterface = 
            userConfigurationInterface.getUserEmailConfigurationInterface();
         
         UserEmailEventsConfigurationInterface userEmailEventsConfigurationInterface =
            userEmailConfigurationInterface.getUserEmailEventsConfigurationInterface();

         UserEmailEventListenerInterface userEmailEventListenerInterface = 
            userEmailEventsConfigurationInterface.getEventListener(
               userEmailEventNameData, userInterface);

         Vector vector = new Vector();
         //Add event listener
         vector.add(userEmailEventListenerInterface);
         return vector;
   }
}
