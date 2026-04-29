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

import org.allbinary.string.CommonStrings;

public class LicenseTypeFactory
{
    private static final LicenseTypeFactory instance = new LicenseTypeFactory();

    public static LicenseTypeFactory getInstance()
    {
        return LicenseTypeFactory.instance;
    }
    
    public final LicenseType UNKNOWN = new LicenseType(CommonStrings.getInstance().UNKNOWN);
    public final LicenseType DEMO = new LicenseType("Demo");
    public final LicenseType NORMAL = new LicenseType("Normal");

    public LicenseType getInstance(String name)
    {
        if(this.DEMO.getName().compareTo(name) == 0)
        {
            return this.DEMO;
        }
        else
        if(this.NORMAL.getName().compareTo(name) == 0)
        {
            return this.NORMAL;
        }
        return this.UNKNOWN;
    }

}
