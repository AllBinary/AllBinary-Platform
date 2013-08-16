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
package abcs.business.installer;


import abcs.logic.system.security.licensing.LicensingException;

import abcs.business.init.db.DatabaseConnectionInfoInterface;
import abcs.business.init.db.InitDbHelperFactory;

//Warning you must have sql root access
public class DynamicInitDb extends InitDb
{
   
   public DynamicInitDb() throws LicensingException
   {
      this.setHelper(InitDbHelperFactory.getInstance());
   }
   
   public DynamicInitDb(DatabaseConnectionInfoInterface databaseConnectionInfoInterface) throws LicensingException
   {
      this.setHelper(InitDbHelperFactory.getInstance(databaseConnectionInfoInterface));
   }
}
