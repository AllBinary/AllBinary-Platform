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

    public String ZERO = "0";
    public String ONE = "1";
    public String TWO = "2";
    public String THREE = "3";
    public String FOUR = "4";
    public String FIVE = "5";
    public String SIX = "6";
    public String SEVEN = "7";
    public String EIGHT = "8";
    public String NINE = "9";
    public String POUND = "#";
    public String STAR = "*";

    public static final CommonPhoneStrings getInstance()
    {
        return SINGLETON;
    }
}
