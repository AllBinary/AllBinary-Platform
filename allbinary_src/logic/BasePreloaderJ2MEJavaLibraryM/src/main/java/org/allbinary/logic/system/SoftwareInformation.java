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
package org.allbinary.logic.system;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

import org.allbinary.KotlinUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;

@JsType
public class SoftwareInformation
{
    private final String name;
    private final String shortName;
    private final String version;
    
    @JsConstructor
    public SoftwareInformation(final String name, final String version, final String shortName)
    {
        this.name = name;
        this.version = version;
        //Records are associated with this short name and should only be a few chars to keep compatibility with the J2ME spec.  However, most J2ME devices support more than the spec.
        //Since Kotlin build input mappings are not the same as Java for some reason we need to include that in the shortname to make sure we are not using the incorrect mapping.
        this.shortName = new StringMaker().append(shortName).append(KotlinUtil.getShortName()).toString();
    }
    
    @JsMethod
    public String getName()
    {
        return this.name;
    }

    @JsMethod
    public String getVersion()
    {
        return this.version;
    }

    @JsMethod
    public String toShortString() {
        return this.shortName;
    }
    
    @JsMethod
    public String toString()
    {
        final String NAME = "Name: ";
        final String VERSION = "Version: ";
        
        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append(NAME);
        stringBuffer.append(this.getName());
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(VERSION);
        stringBuffer.append(this.getVersion());
        
        return stringBuffer.toString();
    }
}
