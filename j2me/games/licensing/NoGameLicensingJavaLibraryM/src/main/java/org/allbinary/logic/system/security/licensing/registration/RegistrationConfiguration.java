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

import jsinterop.annotations.JsType;

import org.allbinary.logic.string.StringMaker;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class RegistrationConfiguration
{
    private static final RegistrationConfiguration SINGLETON = new RegistrationConfiguration();

    private String registrationCode = "No Registration Code";

    @JsProperty
    public final String NAME = "registrationid";

    @JsConstructor
    private RegistrationConfiguration()
    {
    }

    @JsMethod
    public static RegistrationConfiguration getInstance()
    {
        return RegistrationConfiguration.SINGLETON;
    }

    @JsMethod
    private void read() throws Exception
    {
    }

    @JsMethod
    public void write() throws Exception
    {    
    }

    @JsMethod
    public String toString()
    {
        return new StringMaker().append("Registration Code: ").append(this.getRegistrationCode()).toString();
    }

    @JsMethod
    public void setRegistrationCode(String registrationCode)
    {
        this.registrationCode = registrationCode;
    }

    @JsMethod
    public String getRegistrationCode()
    {
        return this.registrationCode;
    }
}
