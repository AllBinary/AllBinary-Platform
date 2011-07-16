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
package allbinary.game.midlet;

import abcs.logic.system.security.licensing.AbeLicenseInterface;
import abcs.logic.system.security.licensing.LicenseInterfaceSingleton;
import abcs.logic.system.security.licensing.LicenseTypeFactory;

public class LicenseLevelUtil
{
    private static final LicenseLevelUtil instance = new LicenseLevelUtil();

    public static LicenseLevelUtil getInstance()
    {
        return instance;
    }

    private final LicenseTypeFactory licenseTypeFactory = LicenseTypeFactory.getInstance();
    
    public int getMaxLevel(int maxLevel, int demoLevel)
    {
        AbeLicenseInterface licenseInterface = 
            LicenseInterfaceSingleton.getInstance();

        if (licenseInterface.getLicenseType() == this.licenseTypeFactory.NORMAL)
        {
            return maxLevel;
        } else if (licenseInterface.getLicenseType() == this.licenseTypeFactory.DEMO)
        {
            return demoLevel;
        } else
        {
            return demoLevel;
        }
    }
}
