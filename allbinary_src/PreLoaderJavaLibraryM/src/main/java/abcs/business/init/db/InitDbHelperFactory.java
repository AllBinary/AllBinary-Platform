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
package abcs.business.init.db;

import abcs.logic.system.loader.AbeFactory;
import abcs.logic.system.security.licensing.LicensingException;

public class InitDbHelperFactory
{
   private static final String CLASSNAME = "allbinary.business.installer.InitDbCrypted";
   private static final String FACTORYNAME = "InitDbHelperFactory";

   private InitDbHelperFactory()
   {
   }

   public static Object getInstance(
      DatabaseConnectionInfoInterface databaseConnectionInfoInterface) 
      throws LicensingException
   {
         Object params[] = new Object[1];
         Class classes[] = new Class[1];
                  
         //Add param types
         classes[0] = DatabaseConnectionInfoInterface.class;
         
         //Add arguments
         params[0] = (Object) databaseConnectionInfoInterface;
                           
         //Object object = AbeFactory.getNoLicenseInstance(CLASSNAME, classes, params);
         Object object = AbeFactory.getInstance(CLASSNAME, classes, params);
         return object;
   }  

   public static Object getInstance() 
      throws LicensingException
   {
         //Object object = AbeFactory.getNoLicenseInstance(CLASSNAME, null, null);
         Object object = AbeFactory.getInstance(CLASSNAME, null, null);
         return object;
   }  
}
