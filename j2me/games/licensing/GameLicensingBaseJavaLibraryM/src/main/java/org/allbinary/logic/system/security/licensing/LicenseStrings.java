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

public class LicenseStrings
{
    private static final LicenseStrings instance = new LicenseStrings();
    
    public final String LOCKED = "Demo Locked Feature";

    public static LicenseStrings getInstance()
    {
        return instance;
    }
}
