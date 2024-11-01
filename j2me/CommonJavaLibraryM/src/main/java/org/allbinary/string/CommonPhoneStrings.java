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
package org.allbinary.string;

public class CommonPhoneStrings
{
    private final static CommonPhoneStrings SINGLETON = new CommonPhoneStrings();

    public final String UP = "UP";
    public final String DOWN = "DOWN";
    public final String LEFT = "LEFT";
    public final String RIGHT = "RIGHT";
    
    public final String ZERO = "0";
    public final String ONE = "1";
    public final String TWO = "2";
    public final String THREE = "3";
    public final String FOUR = "4";
    public final String FIVE = "5";
    public final String SIX = "6";
    public final String SEVEN = "7";
    public final String EIGHT = "8";
    public final String NINE = "9";
    public final String POUND = "#";
    public final String STAR = "*";

    public final String FIRE = "FIRE";
    
    public static final CommonPhoneStrings getInstance()
    {
        return SINGLETON;
    }
}
