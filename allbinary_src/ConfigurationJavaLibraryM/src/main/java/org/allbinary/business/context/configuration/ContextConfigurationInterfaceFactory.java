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

import org.allbinary.business.context.configuration.ContextConfigurationPathUtil;
import org.allbinary.business.context.configuration.ContextConfigurationData;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.basic.path.AbPath;
import org.allbinary.logic.control.crypt.file.CryptFileReader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ContextConfigurationInterfaceFactory
{
   private ContextConfigurationInterfaceFactory()
   {
   }

   public static ContextConfigurationInterface getInstance(Document document) 
      throws Exception
   {
      Node contextConfigurationNode = DomSearchHelper.getNode(
         ContextConfigurationData.getInstance().NAME, document.getChildNodes());

      return (ContextConfigurationInterface) 
         new ContextConfigurationView(contextConfigurationNode).getContextConfigurationInterface();
   }
   
   public static ContextConfigurationInterface getInstance(String contextName) 
      throws Exception
   {
      //user business object key as configuration file name
      AbPath abPath = ContextConfigurationPathUtil.getAbPath(contextName);

      String documentString = new CryptFileReader(
         ContextConfigurationData.getInstance().UNCRYPTED_EXTENSION,
         ContextConfigurationData.getInstance().ENCRYPTED_EXTENSION).get(abPath);

      Document document = DomDocumentHelper.create(documentString);

      return ContextConfigurationInterfaceFactory.getInstance(document);
   }

}
