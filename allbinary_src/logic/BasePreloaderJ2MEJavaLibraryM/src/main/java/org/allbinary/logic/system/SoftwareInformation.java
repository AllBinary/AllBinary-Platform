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

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;

public class SoftwareInformation
{
    private final String name;
    private final String shortName;
    private final String version;
    
    public SoftwareInformation(final String name, final String version, final String shortName)
    {
        this.name = name;
        this.version = version;
        this.shortName = shortName;
    }
    
    public String getName()
    {
        return name;
    }

    public String getVersion()
    {
        return version;
    }

    public String toShortString() {
        return this.shortName;
    }
    
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
