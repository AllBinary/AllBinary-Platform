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

import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.basic.string.StringMaker;

public class SoftwareInformation
{
    private String name;
    private String version;
    
    public SoftwareInformation(String name, String version)
    {
        this.setName(name);
        this.setVersion(version);
    }
    
    private void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }

    private void setVersion(String version)
    {
        this.version = version;
    }

    public String getVersion()
    {
        return version;
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
