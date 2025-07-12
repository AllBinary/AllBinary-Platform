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
package org.allbinary.logic.communication.smtp.event.handler;

import java.util.Vector;

import org.allbinary.business.user.UserInterface;
import org.allbinary.business.user.modules.configuration.UserConfigurationInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.smtp.configuration.user.UserEmailConfigurationInterface;
import org.allbinary.logic.communication.smtp.configuration.user.event.UserEmailEventsConfigurationInterface;
import org.allbinary.logic.communication.smtp.event.UserEmailEventListenerInterface;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.communication.smtp.event.modules.log.LogUserEmailEventListenerModule;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class EmailEventHandlerUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final EmailEventHandlerUtil instance = new EmailEventHandlerUtil();
    
    public static EmailEventHandlerUtil getInstance() {
        return instance;
    }

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
   
   public UserEmailEventHandler getEventHandler(
       final AbeClientInformationInterface abeClientInformation, final UserEmailEventNameData userEmailEventNameData, final Vector userVector)
   throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
    	  final StringMaker stringBuffer = new StringMaker();
    	  
    	  stringBuffer.append("Returning UserEmailEventHandler for ");
    	  stringBuffer.append(userVector.size());
    	  stringBuffer.append(" users.");
    	  
    	  logUtil.put(stringBuffer.toString(), this, "getEventHandler");
      }
      
      final UserEmailEventHandler userEmailEventHandler = new UserEmailEventHandler();
      
      userEmailEventHandler.addListener(new LogUserEmailEventListenerModule());

      int size = userVector.size();
      for(int index = 0; index < size; index++)
      {
         final UserInterface userInterface = (UserInterface) userVector.get(index);

         final Vector vector = 
            EmailEventHandlerUtil.getUserEmailEventListenerVector(
               abeClientInformation, userEmailEventNameData, userInterface);
         
         userEmailEventHandler.addListener(vector);
      }
      
      return userEmailEventHandler;
   }

   public static Vector getUserEmailEventListenerVector(
       final AbeClientInformationInterface abeClientInformation,
      final UserEmailEventNameData userEmailEventNameData, final UserInterface userInterface)
      throws Exception
   {
         //Currently uses a single user configuration
         final UserConfigurationInterface userConfigurationInterface = 
            userInterface.getUserConfigurationInterface();
         
         final UserEmailConfigurationInterface userEmailConfigurationInterface = 
            userConfigurationInterface.getUserEmailConfigurationInterface();
         
         final UserEmailEventsConfigurationInterface userEmailEventsConfigurationInterface =
            userEmailConfigurationInterface.getUserEmailEventsConfigurationInterface();

         final UserEmailEventListenerInterface userEmailEventListenerInterface = 
            userEmailEventsConfigurationInterface.getEventListener(
               abeClientInformation, userEmailEventNameData, userInterface);

         final Vector vector = new Vector();
         //Add event listener
         vector.add(userEmailEventListenerInterface);
         return vector;
   }
}
