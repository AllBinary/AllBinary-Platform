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

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.data.tree.dom.document.mapping.DomDocumentMappingInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ContextConfigurationDomDocumentMapping 
    implements DomDocumentMappingInterface
{
   private ContextConfigurationInterface contextConfigurationInterface;
   
   public ContextConfigurationDomDocumentMapping(
      ContextConfigurationInterface contextConfigurationInterface)
   {
      this.contextConfigurationInterface = contextConfigurationInterface;
   }


   public Document toXmlDoc() throws Exception
   {
      Document document = DomDocumentHelper.create();
      Node node = new ContextConfigurationView(this.contextConfigurationInterface).toXmlNode(document);
      document.appendChild(node);
      return document;
   }

   public String toDomDocumentString() throws Exception
   {
      return DomDocumentHelper.toString(this.toXmlDoc());
   }
}
