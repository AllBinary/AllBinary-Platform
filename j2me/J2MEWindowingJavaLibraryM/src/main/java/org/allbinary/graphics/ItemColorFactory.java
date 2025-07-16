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
package org.allbinary.graphics;

public class ItemColorFactory
{
    private static final ItemColorFactory instance = new ItemColorFactory();

    public static ItemColorFactory getInstance()
    {
        return instance;
    }
    
    public int INVERT_PAINT = (int) 0xFF000000;
    public int PAINT = 0xFFe07718;
    
    public int TEXT_FIELD_NO_FOCUS = 0x7F7F7F;
    
}
