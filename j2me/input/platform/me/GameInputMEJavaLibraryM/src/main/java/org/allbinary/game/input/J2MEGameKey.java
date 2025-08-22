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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class J2MEGameKey extends Input
{
    
    protected J2MEGameKey(int key, String keyName)
    {
        super(key, keyName);

        int id = this.getId();
        if(id >= 0)
        {
            final InputFactory inputFactory = InputFactory.getInstance();
            inputFactory.add(id, this);
        }
    }

    public String toString()
    {
        return new StringMaker().append("J2MEGameKey: ").append(super.toString()).toString();
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
