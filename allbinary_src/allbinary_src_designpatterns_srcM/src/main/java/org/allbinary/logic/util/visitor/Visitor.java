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
package org.allbinary.logic.util.visitor;

import jsinterop.annotations.JsType;

import org.allbinary.logic.NullUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class Visitor implements VisitorInterface
{
    private static final Visitor instance = new Visitor();
    
    @JsProperty
    protected final NullUtil nullUtil = NullUtil.getInstance();
    
    @Override
    @JsMethod
    public Object visit(Object object)
    {
        return this.nullUtil.NULL_OBJECT;
    }

    @JsMethod
    public static Visitor getInstance()
    {
        return Visitor.instance;
    }
}
