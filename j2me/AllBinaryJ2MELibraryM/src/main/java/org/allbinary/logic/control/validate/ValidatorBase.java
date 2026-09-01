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
package org.allbinary.logic.control.validate;

import jsinterop.annotations.JsType;

import java.util.Hashtable;
import org.allbinary.logic.StdUtil;

import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class ValidatorBase 
implements ValidatorBaseInterface
{
    private static final ValidatorBase instance = new ValidatorBase();

    @JsMethod
    public static ValidatorBase getInstance()
    {
        return ValidatorBase.instance;
    }    
    
    @JsConstructor
    public ValidatorBase()
    {
        
    }
    
    /* (non-Javadoc)
     * @see allbinary.logic.control.validate.ValidatorBaseInterface#isValid()
     */
    @Override
    @JsMethod
    public Boolean isValid()
    {
       return BooleanFactory.getInstance().TRUE;
    }
    
    /* (non-Javadoc)
     * @see allbinary.logic.control.validate.ValidatorBaseInterface#validationInfo()
     */
    @Override
    @JsMethod
    public String validationInfo()
    {
       return StringUtil.getInstance().EMPTY_STRING;
    }

    /* (non-Javadoc)
     * @see allbinary.logic.control.validate.ValidatorBaseInterface#toHashMap()
     */
    @Override
    @JsMethod
    public Hashtable toHashtable()
    {
       return StdUtil.getInstance().NULL_TABLE;
    }

    /* (non-Javadoc)
     * @see allbinary.logic.control.validate.ValidatorBaseInterface#toVector()
     */
    @Override
    @JsMethod
    public BasicArrayList toList()
    {
       return BasicArrayListUtil.getInstance().getImmutableInstance();
    }
}
