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
package org.allbinary.logic.basic.io;

public class AbFileSystem {

    private static final AbFileSystem instance = new AbFileSystem();

    /**
     * @return the instance
     */
    public static AbFileSystem getInstance()
    {
        return instance;
    }

    private final String type = "java.io";

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    public boolean isType(String type)
    {
        if(this.type.compareTo(type) == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
