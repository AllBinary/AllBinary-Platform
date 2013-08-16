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
package allbinary.logic.communication.smtp.configuration.user.event;

import abcs.data.tree.dom.DomSearchHelper;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tree.dom.DomNodeInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class UserEmailEventsConfigurationView implements DomNodeInterface
{
   private UserEmailEventsConfigurationInterface userEmailEventsConfigurationInterface;
   
   public UserEmailEventsConfigurationView(Node node) throws Exception
   {
      this.userEmailEventsConfigurationInterface = 
         (UserEmailEventsConfigurationInterface) new UserEmailEventsConfiguration();
      
      NodeList childNodeList = node.getChildNodes();

      Vector emailEventConfigurationNodeVector = 
         DomSearchHelper.getAllNodesNoThrow(
            UserEmailEventConfigurationData.NAME, childNodeList);

      Iterator iter = emailEventConfigurationNodeVector.iterator();
      while(iter.hasNext())
      {
         Node userEmailConfigurationNode = (Node) iter.next();

         UserEmailEventConfigurationView userEmailEventConfigurationView =
            new UserEmailEventConfigurationView(userEmailConfigurationNode);

         this.userEmailEventsConfigurationInterface.addUserEmailEventConfiguration(
            userEmailEventConfigurationView.getUserEmailEventConfigurationInterface());
      }
   }

   public UserEmailEventsConfigurationView(
      UserEmailEventsConfigurationInterface userEmailEventsConfigurationInterface)
   {
      this.userEmailEventsConfigurationInterface = userEmailEventsConfigurationInterface;
   }
   
   public UserEmailEventsConfigurationInterface getUserEmailEventsConfigurationInterface()
   {
      return this.userEmailEventsConfigurationInterface;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      UserEmailEventsConfigurationMapping userEmailConfigurationMapping = 
         new UserEmailEventsConfigurationMapping(this.userEmailEventsConfigurationInterface);

      //HashMap hashMap = userEmailConfigurationMapping.toHashMap();

      Node node = document.createElement(UserEmailEventsConfigurationData.NAME);

      HashMap emailEventHashMap = 
         this.getUserEmailEventsConfigurationInterface().getEventConfigurationHashMap();
      Set set = emailEventHashMap.keySet();
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Number Of Email Events Specified in file: " + set.size(), this, "Constructor"));
      }
      
      Iterator iter = set.iterator();
      while(iter.hasNext())
      {
         String eventName = (String) iter.next();

         UserEmailEventConfigurationInterface userEmailEventsConfigurationInterface = 
            (UserEmailEventConfigurationInterface) emailEventHashMap.get(eventName);

         UserEmailEventConfigurationView userEmailEventConfigurationView = 
            new UserEmailEventConfigurationView(userEmailEventsConfigurationInterface);

         node.appendChild(userEmailEventConfigurationView.toXmlNode(document));
      }
      return node;
   }
}
