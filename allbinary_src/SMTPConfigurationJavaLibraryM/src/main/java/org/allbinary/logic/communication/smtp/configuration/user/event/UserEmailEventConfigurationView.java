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
package org.allbinary.logic.communication.smtp.configuration.user.event;

import java.util.HashMap;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UserEmailEventConfigurationView implements DomNodeInterface
{
   private UserEmailEventConfigurationInterface userEmailEventConfigurationInterface;

   public UserEmailEventConfigurationView(Node node) throws Exception
   {
      NodeList childNodeList = node.getChildNodes();
      HashMap hashMap = new HashMap();
      
      String name = 
         DomNodeHelper.getTextNodeValue(
            UserEmailEventConfigurationData.NAME, childNodeList);
      hashMap.put(UserEmailEventConfigurationData.NAME,name);
      
      String eventListenerClassPath = 
         DomNodeHelper.getTextNodeValue(
            UserEmailEventConfigurationData.LISTENER_CLASSPATH, childNodeList);
      hashMap.put(
         UserEmailEventConfigurationData.LISTENER_CLASSPATH, 
         eventListenerClassPath);
      
      this.setUserEmailEventConfigurationInterface(
         (UserEmailEventConfigurationInterface) new UserEmailEventConfiguration(hashMap));
   }
   
   public UserEmailEventConfigurationView(
      UserEmailEventConfigurationInterface userEmailEventConfigurationInterface)
   {
      this.setUserEmailEventConfigurationInterface(userEmailEventConfigurationInterface);
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      UserEmailEventConfigurationMapping userEmailEventConfigurationMapping =
         new UserEmailEventConfigurationMapping(this.getUserEmailEventConfigurationInterface());
      
      HashMap hashMap = userEmailEventConfigurationMapping.toHashMap();
      
      Node node = ModDomHelper.createNameValueNodes(
         document, UserEmailEventConfigurationData.NAME, hashMap);
      
      return node;
   }

    public UserEmailEventConfigurationInterface getUserEmailEventConfigurationInterface()
    {
        return this.userEmailEventConfigurationInterface;
    }

    public void setUserEmailEventConfigurationInterface(
       UserEmailEventConfigurationInterface userEmailEventConfigurationInterface)
    {
        this.userEmailEventConfigurationInterface = userEmailEventConfigurationInterface;
    }
}
