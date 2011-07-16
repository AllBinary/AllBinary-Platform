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
package abcs.logic.system.security.licensing;

import allbinary.debug.DebugFactory;
import allbinary.debug.NoDebug;

public class LockedUtil
{
    private static final LockedUtil instance = new LockedUtil();

    public static LockedUtil getInstance()
    {
        return instance;
    }
 
    private final LicenseTypeFactory licenseTypeFactory = LicenseTypeFactory.getInstance();
    
    public boolean isLockedFeature()
    {
        AbeLicenseInterface licenseInterface = 
            LicenseInterfaceSingleton.getInstance();

        if(licenseInterface.getLicenseType() != this.licenseTypeFactory.NORMAL && 
                DebugFactory.getInstance() == NoDebug.getInstance() 
                //&& false
                )
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
}
