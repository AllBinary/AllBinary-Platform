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
package org.allbinary.init;

import jsinterop.annotations.JsType;

import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class Init implements InitInterface
{
    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    @Override
    @JsMethod
    public void init()
    throws Exception
    {
        
    }
}
