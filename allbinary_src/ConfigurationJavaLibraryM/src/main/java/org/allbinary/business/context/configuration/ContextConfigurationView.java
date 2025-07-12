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
package org.allbinary.business.context.configuration;

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.communication.smtp.configuration.server.EmailServerConfigurationData;
import org.allbinary.logic.communication.smtp.configuration.server.EmailServerConfigurationInterface;
import org.allbinary.logic.communication.smtp.configuration.server.EmailServerConfigurationView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
   
public class ContextConfigurationView implements DomNodeInterface
{
   private ContextConfigurationInterface contextConfigurationInterface;

   public ContextConfigurationView(Node node) throws Exception
   {
      NodeList childNodeList = node.getChildNodes();

      this.contextConfigurationInterface = new ContextConfiguration();

      Node emailConfigurationNode = DomSearchHelper.getNode(
         EmailServerConfigurationData.NAME, childNodeList);
      
      EmailServerConfigurationView emailServerConfigurationView =
         new EmailServerConfigurationView(emailConfigurationNode);
      
      this.contextConfigurationInterface.setEmailServerConfigurationInterface(
         (EmailServerConfigurationInterface) 
         emailServerConfigurationView.getEmailConfigurationInterface());
   }

   public ContextConfigurationView(
      ContextConfigurationInterface contextConfigurationInterface)
   {
      this.contextConfigurationInterface = contextConfigurationInterface;
   }
   
   public ContextConfigurationInterface getContextConfigurationInterface()
   {
      return this.contextConfigurationInterface;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      Node node = document.createElement(ContextConfigurationData.getInstance().NAME);

      EmailServerConfigurationView emailServerConfigurationView = 
         new EmailServerConfigurationView(
            this.getContextConfigurationInterface().getEmailServerConfigurationInterface());

      node.appendChild(emailServerConfigurationView.toXmlNode(document));
      
      return node;
   }
}
