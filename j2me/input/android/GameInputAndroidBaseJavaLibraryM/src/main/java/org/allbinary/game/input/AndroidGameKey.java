package org.allbinary.game.input;

import org.allbinary.logic.basic.string.StringUtil;

public class AndroidGameKey extends Input
{
    AndroidGameKey(int keyCode, String name)
    {
        super(keyCode, name);
        
        this.inputFactory.inputIntegerArray[this.getId()] = this;
    }
    
    public String toString()
    {
        return "AndroidGameKey: " + super.toString();
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
