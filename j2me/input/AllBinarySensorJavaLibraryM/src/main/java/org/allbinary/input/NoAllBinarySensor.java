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
package org.allbinary.input;

public class NoAllBinarySensor 
    extends AllBinarySensor
{
    private static final NoAllBinarySensor instance = new NoAllBinarySensor();

    public static NoAllBinarySensor getInstance()
    {
        return instance;
    }

    private NoAllBinarySensor()
    {
        
    }
}
