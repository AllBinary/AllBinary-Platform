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
package org.allbinary.logic.communication.smtp.configuration.server;

import java.util.HashMap;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EmailServerConfigurationView implements DomNodeInterface
{
   private EmailServerConfigurationInterface emailServerConfigurationInterface;
   
   public EmailServerConfigurationView(Node node) throws Exception
   {
      NodeList childNodeList = node.getChildNodes();

      String server = 
         DomNodeHelper.getTextNodeValue(
            EmailServerConfigurationData.SERVER, childNodeList);

      String accountName = 
         DomNodeHelper.getTextNodeValue(
            EmailServerConfigurationData.ACCOUNT, childNodeList);

      String password = 
         DomNodeHelper.getTextNodeValue(
            EmailServerConfigurationData.PASSWORD, childNodeList);
    
      this.emailServerConfigurationInterface = 
         (EmailServerConfigurationInterface) 
         new EmailServerConfiguration(accountName, password, server);
   }

   public EmailServerConfigurationView(
      EmailServerConfigurationInterface emailServerConfigurationInterface)
   {
      this.emailServerConfigurationInterface = emailServerConfigurationInterface;
   }
   
   public EmailServerConfigurationInterface getEmailConfigurationInterface()
   {
      return this.emailServerConfigurationInterface;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      EmailServerConfigurationMapping emailConfigurationMapping = 
         new EmailServerConfigurationMapping(this.emailServerConfigurationInterface);

      HashMap hashMap = emailConfigurationMapping.toHashMap();

      Node node = ModDomHelper.createNodeWithValueNodes(
         document, EmailServerConfigurationData.NAME, hashMap);

      return node;
   }
}
