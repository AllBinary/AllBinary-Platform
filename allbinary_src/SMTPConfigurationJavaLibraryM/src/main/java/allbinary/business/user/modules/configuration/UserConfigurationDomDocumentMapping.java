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
package allbinary.business.user.modules.configuration;

import abcs.data.tree.dom.document.DomDocumentHelper;
import allbinary.data.tree.dom.document.mapping.DomDocumentMappingInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class UserConfigurationDomDocumentMapping implements DomDocumentMappingInterface
{
   private UserConfigurationInterface userConfigurationInterface;
   
   public UserConfigurationDomDocumentMapping(
      UserConfigurationInterface userConfigurationInterface)
   {
      this.userConfigurationInterface = userConfigurationInterface;
   }


   public Document toXmlDoc() throws Exception
   {
      Document document = DomDocumentHelper.create();
      Node node = new UserConfigurationView(this.userConfigurationInterface).toXmlNode(document);
      document.appendChild(node);
      return document;
   }

   public String toDomDocumentString() throws Exception
   {
      return DomDocumentHelper.toString(this.toXmlDoc());
   }
}
