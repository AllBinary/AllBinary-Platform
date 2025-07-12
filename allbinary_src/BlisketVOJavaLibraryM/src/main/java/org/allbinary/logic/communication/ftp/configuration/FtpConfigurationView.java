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
package org.allbinary.logic.communication.ftp.configuration;

import java.util.HashMap;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FtpConfigurationView implements DomNodeInterface
{
   private FtpConfigurationInterface ftpConfigurationInterface;

   public FtpConfigurationView(Node node) throws Exception
   {
      this.setFtpConfigurationInterface((FtpConfigurationInterface) new FtpConfiguration());

      NodeList chileNodeList = node.getChildNodes();

      String server = 
         DomNodeHelper.getTextNodeValue(
            FtpConfigurationData.SERVER, chileNodeList);
      this.getFtpConfigurationInterface().setServer(server);
      
      String userName = 
         DomNodeHelper.getTextNodeValue(
            FtpConfigurationData.USERNAME, chileNodeList);
      this.getFtpConfigurationInterface().setUserName(userName);
      
      String password = 
         DomNodeHelper.getTextNodeValue(
            FtpConfigurationData.PASSWORD, chileNodeList);
      this.getFtpConfigurationInterface().setPassword(password);

      String path = 
         DomNodeHelper.getTextNodeValue(
            FtpConfigurationData.PATH, chileNodeList);
      this.getFtpConfigurationInterface().setPath(path);
   }

   public FtpConfigurationView(
      FtpConfigurationInterface ftpConfigurationInterface) 
      throws Exception
   {
      this.setFtpConfigurationInterface(ftpConfigurationInterface);
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      FtpConfigurationMapping ftpConfigurationMapping = 
         new FtpConfigurationMapping(this.getFtpConfigurationInterface());

      HashMap hashMap = ftpConfigurationMapping.toHashMap();

      Node node = 
         ModDomHelper.createNameValueNodes(
            document, FtpConfigurationData.NAME, hashMap);

      return node;
   }

    public FtpConfigurationInterface getFtpConfigurationInterface()
    {
        return ftpConfigurationInterface;
    }

    public void setFtpConfigurationInterface(FtpConfigurationInterface ftpConfigurationInterface)
    {
        this.ftpConfigurationInterface = ftpConfigurationInterface;
    }
}
