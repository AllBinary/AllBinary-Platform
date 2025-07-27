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
package org.allbinary.game;

import org.allbinary.logic.string.StringUtil;

public class PlayerType
{
    private int id;
    private String name = StringUtil.getInstance().EMPTY_STRING;
    
    PlayerType(final String name, final int value)
    {
        this.name = name;
        this.id = value;
    }

//    private void setId(int id)
//    {
//        this.id = id;
//    }

    public int getId()
    {
        return id;
    }

//    private void setName(String name)
//    {
//        this.name = name;
//    }

    public String getName()
    {
        return name;
    }
    
    public String toString()
    {
        return this.getName();
    }
}
