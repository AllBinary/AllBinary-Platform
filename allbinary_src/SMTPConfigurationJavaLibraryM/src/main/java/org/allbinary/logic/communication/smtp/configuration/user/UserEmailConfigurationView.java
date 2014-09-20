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
package org.allbinary.logic.communication.smtp.configuration.user;

import org.allbinary.logic.communication.smtp.configuration.user.UserEmailConfigurationData;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.communication.smtp.configuration.user.event.UserEmailEventsConfigurationData;
import org.allbinary.logic.communication.smtp.configuration.user.event.UserEmailEventsConfigurationInterface;
import org.allbinary.logic.communication.smtp.configuration.user.event.UserEmailEventsConfigurationView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class UserEmailConfigurationView implements DomNodeInterface
{
   private UserEmailConfigurationInterface userEmailConfigurationInterface;
   
   public UserEmailConfigurationView(Node node) throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructor", this, "Constructor"));
      }

      this.userEmailConfigurationInterface = (UserEmailConfigurationInterface) new UserEmailConfiguration();
      
      NodeList childNodeList = node.getChildNodes();

      Node userEmailEventsConfigurationNode = 
         DomSearchHelper.getNodeNoThrow(UserEmailEventsConfigurationData.NAME, childNodeList);

      UserEmailEventsConfigurationView userEmailEventsConfigurationView =
         new UserEmailEventsConfigurationView(userEmailEventsConfigurationNode);
      
      this.userEmailConfigurationInterface.setUserEmailEventsConfigurationInterface(
         userEmailEventsConfigurationView.getUserEmailEventsConfigurationInterface());
   }

   public UserEmailConfigurationView(
      UserEmailConfigurationInterface userEmailConfigurationInterface)
   {
      this.userEmailConfigurationInterface = userEmailConfigurationInterface;
   }
   
   public UserEmailConfigurationInterface getEmailConfigurationInterface()
   {
      return this.userEmailConfigurationInterface;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      UserEmailConfigurationMapping emailConfigurationMapping = 
         new UserEmailConfigurationMapping(this.userEmailConfigurationInterface);

      HashMap hashMap = emailConfigurationMapping.toHashMap();

      Node node = ModDomHelper.createNameValueNodes(
         document, UserEmailConfigurationData.NAME, hashMap);

      UserEmailConfigurationInterface userEmailConfigurationInterface =
         this.getEmailConfigurationInterface();

      UserEmailEventsConfigurationInterface userEmailEventsConfigurationInterface = 
         userEmailConfigurationInterface.getUserEmailEventsConfigurationInterface();

      UserEmailEventsConfigurationView userEmailEventsConfigurationView = 
         new UserEmailEventsConfigurationView(userEmailEventsConfigurationInterface);

      node.appendChild(userEmailEventsConfigurationView.toXmlNode(document));

      return node;
   }
}
