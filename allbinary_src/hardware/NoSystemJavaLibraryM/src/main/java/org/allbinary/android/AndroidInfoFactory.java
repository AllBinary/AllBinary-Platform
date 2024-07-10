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
package org.allbinary.android;

public class AndroidInfoFactory
{
    private static final AndroidInfoFactory instance = new AndroidInfoFactory();

    public static AndroidInfoFactory getInstance()
    {
        return instance;
    }
    
    private final int version = 8;
    //int SDK_VERSION = Build.VERSION.SDK_INT;
    //https://source.android.com/source/build-numbers
    
    public int getVersion()
    {
        return version;
    }    
}
