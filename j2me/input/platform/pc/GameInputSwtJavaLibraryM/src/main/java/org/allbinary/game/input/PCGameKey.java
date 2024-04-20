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
package org.allbinary.game.input;

import org.allbinary.logic.string.StringUtil;

public class PCGameKey extends Input
{
    PCGameKey(int keyCode, String name)
    {
        super(keyCode, name);

        int id = this.getId();
        if(id >= 0)
        {
            InputFactory.getInstance().add(id, this);
        }
        /*
        else
        {
            negativeInputIntegerArray[-id] = this;
        }
        */
    }
    
    public String toString()
    {
        return "PCGameKey: " + super.toString();
    }
        
    public static String getString(int keyCode)
    {
        Input input = InputFactory.getInstance().getInstance(keyCode);

        if(input != null)
        {
            return input.getName();
        }
        else
        {
            return StringUtil.getInstance().EMPTY_STRING;
        }
    }
}
