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
package org.allbinary.business.installer;


import org.allbinary.logic.system.security.licensing.LicensingException;

import org.allbinary.business.init.db.DatabaseConnectionInfoInterface;
import org.allbinary.business.init.db.InitDbHelperFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

//Warning you must have sql root access
public class DynamicInitDb extends InitDb
{
   
   public DynamicInitDb(final AbeClientInformationInterface abeClientInformation) throws LicensingException
   {
      this.setHelper(InitDbHelperFactory.getInstance(abeClientInformation));
   }
   
   public DynamicInitDb(final AbeClientInformationInterface abeClientInformation, DatabaseConnectionInfoInterface databaseConnectionInfoInterface) throws LicensingException
   {
      this.setHelper(InitDbHelperFactory.getInstance(abeClientInformation, databaseConnectionInfoInterface));
   }
}
