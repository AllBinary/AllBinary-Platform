/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 */
package org.allbinary.game.layer.capital.event;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;

public class CapitalEvent extends AllBinaryEventObject
{
    private int value;

    public CapitalEvent(Object object)
    {
        super(object);
    }

    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("CapitalEvent: Value: \n");
        stringBuffer.append(this.getValue());

        return stringBuffer.toString();
    }

    /**
     * @return the value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value)
    {
        this.value = value;
    }

}
