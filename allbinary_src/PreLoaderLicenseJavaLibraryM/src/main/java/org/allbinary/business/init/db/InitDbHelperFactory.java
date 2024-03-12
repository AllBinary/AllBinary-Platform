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
package org.allbinary.business.init.db;

import org.allbinary.logic.system.loader.AbeFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.LicensingException;

public class InitDbHelperFactory
{
   private static final String CLASSNAME = "org.allbinary.business.installer.InitDbCrypted";
   private static final String FACTORYNAME = "InitDbHelperFactory";

   private InitDbHelperFactory()
   {
   }

   public static Object getInstance(
      final AbeClientInformationInterface abeClientInformation, DatabaseConnectionInfoInterface databaseConnectionInfoInterface) 
      throws LicensingException
   {
         Object params[] = new Object[1];
         Class classes[] = new Class[1];
                  
         //Add param types
         classes[0] = DatabaseConnectionInfoInterface.class;
         
         //Add arguments
         params[0] = (Object) databaseConnectionInfoInterface;
                           
         //Object object = AbeFactory.getNoLicenseInstance(CLASSNAME, classes, params);
         Object object = AbeFactory.getInstance(abeClientInformation, CLASSNAME, classes, params);
         return object;
   }  

   public static Object getInstance(final AbeClientInformationInterface abeClientInformation) 
      throws LicensingException
   {
         //Object object = AbeFactory.getNoLicenseInstance(CLASSNAME, null, null);
         Object object = AbeFactory.getInstance(abeClientInformation, CLASSNAME, null, null);
         return object;
   }  
}
