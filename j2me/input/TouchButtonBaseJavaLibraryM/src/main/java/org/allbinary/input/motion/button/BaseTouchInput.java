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
package org.allbinary.input.motion.button;

import jsinterop.annotations.JsType;

import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author user
 */

@JsType
public class BaseTouchInput 
{
    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();
    
    @JsConstructor
    BaseTouchInput()
    {
        
    }
        
    /**
     * @return the list
     */
    @JsMethod
    public BasicArrayList getList()
    {
        return this.basicArrayListUtil.getImmutableInstance();
    }
    
    @JsMethod
    public String toString()
    {
        return this.getClass().getName();
    }
}
