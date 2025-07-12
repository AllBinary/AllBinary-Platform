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

import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.control.crypt.file.CryptFileReader;
import org.allbinary.logic.io.path.AbPath;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ContextConfigurationInterfaceFactory
{
    private static final ContextConfigurationInterfaceFactory instance = new ContextConfigurationInterfaceFactory();

    /**
     * @return the instance
     */
    public static ContextConfigurationInterfaceFactory getInstance() {
        return instance;
    }
    
   private ContextConfigurationInterfaceFactory()
   {
   }

   public ContextConfigurationInterface getInstance(final Document document) 
      throws Exception
   {
      final ContextConfigurationData contextConfigurationData = 
          ContextConfigurationData.getInstance();
       
      final Node contextConfigurationNode = DomSearchHelper.getNode(
         contextConfigurationData.NAME, document.getChildNodes());

      return (ContextConfigurationInterface) 
         new ContextConfigurationView(contextConfigurationNode).getContextConfigurationInterface();
   }
   
   public ContextConfigurationInterface getInstance(final String contextName) 
      throws Exception
   {
      //user business object key as configuration file name
      final AbPath abPath = ContextConfigurationPathUtil.getAbPath(contextName);

      final ContextConfigurationData contextConfigurationData = 
          ContextConfigurationData.getInstance();
      
      final String documentString = new CryptFileReader(
         contextConfigurationData.UNCRYPTED_EXTENSION,
         contextConfigurationData.ENCRYPTED_EXTENSION).get(abPath);

      final Document document = DomDocumentHelper.create(documentString);

      return this.getInstance(document);
   }

}
