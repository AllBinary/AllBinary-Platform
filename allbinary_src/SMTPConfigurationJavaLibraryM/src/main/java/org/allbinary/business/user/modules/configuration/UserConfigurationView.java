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
package org.allbinary.business.user.modules.configuration;

import org.allbinary.business.entry.EntryData;
import org.allbinary.business.time.created.TimeCreatedView;
import org.allbinary.business.time.modified.TimeLastModifiedView;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.communication.smtp.configuration.user.UserEmailConfigurationData;
import org.allbinary.logic.communication.smtp.configuration.user.UserEmailConfigurationInterface;
import org.allbinary.logic.communication.smtp.configuration.user.UserEmailConfigurationView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UserConfigurationView 
   implements DomNodeInterface
{
   private UserConfigurationInterface userConfigurationInterface;

   public UserConfigurationView(Node node) throws Exception
   {
      this.userConfigurationInterface = (UserConfigurationInterface) new UserConfiguration();
      
      NodeList childNodeList = node.getChildNodes();

      Node emailConfigurationNode = DomSearchHelper.getNode(
         UserEmailConfigurationData.NAME, childNodeList);

      UserEmailConfigurationView userEmailConfigurationView =
         new UserEmailConfigurationView(emailConfigurationNode);

      UserEmailConfigurationInterface userEmailConfigurationInterface = (UserEmailConfigurationInterface) 
         userEmailConfigurationView.getEmailConfigurationInterface();
      userConfigurationInterface.setUserEmailConfigurationInterface(userEmailConfigurationInterface);
  
      /*
      Node ftpConfigurationNode = DomSearchHelper.getNode(
         FtpConfigurationData.NAME, childNodeList);

      FtpConfigurationView ftpConfigurationView =
         new FtpConfigurationView(ftpConfigurationNode);

      FtpConfigurationInterface ftpConfigurationInterface = (FtpConfigurationInterface) 
         ftpConfigurationView.getFtpConfigurationInterface();
      this.userConfigurationInterface.setFtpConfigurationInterface(ftpConfigurationInterface);
      */
      
      Node timeCreatedNode = DomSearchHelper.getNode(
         EntryData.getInstance().TIMECREATED, childNodeList);
      
      TimeCreatedView timeCreatedView = new TimeCreatedView(timeCreatedNode);

      this.userConfigurationInterface.setTimeCreated(timeCreatedView.getTimeCreated());

      Node timeLastModifiedNode = DomSearchHelper.getNode(
         EntryData.getInstance().LASTMODIFIED, childNodeList);

      TimeLastModifiedView timeLastModifiedView = 
         new TimeLastModifiedView(timeLastModifiedNode);

      this.userConfigurationInterface.setTimeLastModified(timeLastModifiedView.getTimeLastModified());
   }

   public UserConfigurationView(UserConfigurationInterface userConfigurationInterface)
   {
      this.userConfigurationInterface = userConfigurationInterface;
   }

   public UserConfigurationInterface getUserConfigurationInterface()
   {
      return this.userConfigurationInterface;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      Node node = document.createElement(UserConfigurationData.NAME);

      UserEmailConfigurationView userEmailConfigurationView = 
         new UserEmailConfigurationView(
            this.userConfigurationInterface.getUserEmailConfigurationInterface());
      node.appendChild(userEmailConfigurationView.toXmlNode(document));

      /*
      FtpConfigurationView ftpConfigurationView = 
         new FtpConfigurationView(userConfigurationInterface.getFtpConfigurationInterface());
      node.appendChild(ftpConfigurationView.toXmlNode(document));
*/
      TimeCreatedView timeCreatedView = 
         new TimeCreatedView(userConfigurationInterface.getTimeCreated());
      node.appendChild(timeCreatedView.toXmlNode(document));

      TimeLastModifiedView timeLastModifiedView = 
         new TimeLastModifiedView(userConfigurationInterface.getTimeLastModified());
      node.appendChild(timeLastModifiedView.toXmlNode(document));

      return node;
   }
}
