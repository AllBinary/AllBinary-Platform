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
package org.allbinary.logic.system.security.licensing.registration;

import org.allbinary.logic.string.StringMaker;

public class RegistrationConfiguration
{
    private static final RegistrationConfiguration SINGLETON = new RegistrationConfiguration();

    private String registrationCode = "No Registration Code";

    public final String NAME = "registrationid";

    private RegistrationConfiguration()
    {
    }

    public static RegistrationConfiguration getInstance()
    {
        return SINGLETON;
    }

    private void read() throws Exception
    {
    }

    public void write() throws Exception
    {    
    }

    public String toString()
    {
        return new StringMaker().append("Registration Code: ").append(this.getRegistrationCode()).toString();
    }

    public void setRegistrationCode(String registrationCode)
    {
        this.registrationCode = registrationCode;
    }

    public String getRegistrationCode()
    {
        return registrationCode;
    }
}
