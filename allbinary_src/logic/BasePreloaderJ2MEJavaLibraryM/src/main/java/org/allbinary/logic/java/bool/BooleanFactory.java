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
package org.allbinary.logic.java.bool;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class BooleanFactory
{
    private static final BooleanFactory instance = new BooleanFactory();

    @JsMethod
    public static BooleanFactory getInstance()
    {
        return BooleanFactory.instance;
    }

    //public final Boolean TRUE = Boolean.TRUE;
    //public final Boolean FALSE = Boolean.FALSE;    
    @JsProperty
    public final Boolean TRUE = new Boolean(true); //Boolean.TRUE;
    @JsProperty
    public final Boolean FALSE = new Boolean(false); //Boolean.FALSE;

    @JsProperty
    public final String TRUE_STRING = this.TRUE.toString();
    @JsProperty
    public final String FALSE_STRING = this.FALSE.toString();

    @JsProperty
    public final String YES = "yes";
    @JsProperty
    public final String NO = "no";
    
    @JsMethod
    public String toStringb(boolean bool)
    {
        if(bool)
        {
            return this.TRUE_STRING;
        }
        else
        {
            return this.FALSE_STRING;
        }
    }
}
