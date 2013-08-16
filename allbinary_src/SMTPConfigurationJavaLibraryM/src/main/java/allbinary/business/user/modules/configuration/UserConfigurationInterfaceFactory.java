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
import abcs.data.tree.dom.DomSearchHelper;
import abcs.globals.URLGLOBALS;
import abcs.logic.basic.path.AbPath;
import abcs.logic.basic.path.AbPathData;
import allbinary.business.user.role.UserRole;
import allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import allbinary.logic.control.crypt.file.CryptFileReader;
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
