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

import jsinterop.annotations.JsType;

import org.allbinary.logic.string.StringMaker;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class Direction
{
    private static Direction[] directionArray = new Direction[13];

    private String name;
    private String otherName;
    private int value;
    private int frameFactor;

    @JsConstructor
    Direction(String name, String otherName, int value, int frameFactor)
    {
        this.name = name;
        this.otherName = otherName;
        this.value = value;
        this.frameFactor = frameFactor;

        Direction.directionArray[value] = this;
    }

    @JsMethod
    public static Direction getInstance(int direction)
    {
        // return (GeographicMapDirectionData)
        // hashtable.get(Integer.valueOf(direction));
        return Direction.directionArray[direction];
    }

    @JsMethod
    public int getValue()
    {
        return this.value;
    }

    @JsMethod
    public int getFrameFactor()
    {
        return this.frameFactor;
    }

    @JsMethod
    public String getOtherName()
    {
        return this.otherName;
    }

    @JsMethod
    public String getName()
    {
        return this.name;
    }

    @JsMethod
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append("Direction: ");
        stringBuffer.append(this.getName());
        stringBuffer.append(" Or: ");
        stringBuffer.append(this.getOtherName());
        stringBuffer.append(" Value: ");
        stringBuffer.appendint(this.value);

        return stringBuffer.toString();
    }

}
