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
package org.allbinary.direction;

import org.allbinary.logic.string.StringMaker;

public class Direction
{
    private static Direction[] directionArray = new Direction[13];

    private String name;
    private String otherName;
    private int value;
    private int frameFactor;

    Direction(String name, String otherName, int value, int frameFactor)
    {
        this.name = name;
        this.otherName = otherName;
        this.value = value;
        this.frameFactor = frameFactor;

        directionArray[value] = this;
    }

    Direction(String name, String otherName, int value)
    {
        this.name = name;
        this.otherName = otherName;
        this.value = value;
        this.frameFactor = 0;

        directionArray[value] = this;
    }

    public static Direction getInstance(int direction)
    {
        // return (GeographicMapDirectionData)
        // hashtable.get(Integer.valueOf(direction));
        return directionArray[direction];
    }

    public int getValue()
    {
        return this.value;
    }

    public int getFrameFactor()
    {
        return this.frameFactor;
    }

    public String getOtherName()
    {
        return otherName;
    }

    public String getName()
    {
        return name;
    }

    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append("Direction: ");
        stringBuffer.append(this.getName());
        stringBuffer.append(" Or: ");
        stringBuffer.append(this.getOtherName());
        stringBuffer.append(" Value: ");
        stringBuffer.append(this.value);

        return stringBuffer.toString();
    }

}
