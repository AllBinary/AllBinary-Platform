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

import org.allbinary.business.user.role.UserRole;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.control.crypt.file.CryptFileReader;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class UserConfigurationInterfaceFactory
{
   private static final String configurationName = "DefaultUserConfiguration";

   //Provide the default user configuration
   private UserConfigurationInterfaceFactory()
   {
   }

   public static UserConfigurationInterface getInstance(Document document)
      throws Exception
   {
      Node configurationNode = DomSearchHelper.getNode(
         UserConfigurationData.NAME, document.getChildNodes());

      return (UserConfigurationInterface) 
         new UserConfigurationView(configurationNode
            ).getUserConfigurationInterface();
   }

   public static UserConfigurationInterface getInstance(UserRole userRole)
      throws Exception
   {
      //user business object key as configuration file name
      AbPath abPath = new AbPath(
         URLGLOBALS.getMainPath() + FREEBLISKET_PATH_GLOBALS.getInstance().USERCONFIGURATIONPATH,
         configurationName + userRole.toString() + AbPathData.getInstance().EXTENSION_SEP +
         UserConfigurationData.UNCRYPTED_EXTENSION);

      String documentString = new CryptFileReader(
         UserConfigurationData.UNCRYPTED_EXTENSION,
         UserConfigurationData.ENCRYPTED_EXTENSION).get(abPath);

      Document document = DomDocumentHelper.create(documentString);

      return UserConfigurationInterfaceFactory.getInstance(document);
   }

   //Default User Configuration
   public static UserConfigurationInterface getInstance()
      throws Exception
   {
      return new UserConfiguration();
   }
}
