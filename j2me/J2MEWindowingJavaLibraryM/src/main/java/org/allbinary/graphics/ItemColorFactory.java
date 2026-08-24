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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class ItemColorFactory
{
    private static final ItemColorFactory instance = new ItemColorFactory();

    @JsMethod
    public static ItemColorFactory getInstance()
    {
        return ItemColorFactory.instance;
    }
    
    @JsProperty
    public int INVERT_PAINT = (int) 0xFF000000;
    @JsProperty
    public int PAINT = (int) 0xFFe07718;
    
    @JsProperty
    public int TEXT_FIELD_NO_FOCUS = 0x7F7F7F;
    
}
