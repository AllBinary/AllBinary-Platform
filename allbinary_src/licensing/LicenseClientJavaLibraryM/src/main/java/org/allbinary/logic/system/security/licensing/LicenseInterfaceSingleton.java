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
package org.allbinary.logic.system.security.licensing;

public class LicenseInterfaceSingleton
{
    private static AbeLicenseInterface licenseInterface = AbeNoLicense.getInstance();

    public static AbeLicenseInterface getInstance()
    {
        return licenseInterface;
    }

    public static void init(AbeLicenseInterface licenseInterface)
    {
        if(licenseInterface != null)
        {
            LicenseInterfaceSingleton.licenseInterface = licenseInterface;
        }
    }
}
