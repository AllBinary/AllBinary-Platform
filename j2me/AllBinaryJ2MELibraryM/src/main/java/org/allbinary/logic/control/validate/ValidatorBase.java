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

import java.util.Hashtable;

import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

public class ValidatorBase 
implements ValidatorBaseInterface
{
    private static final ValidatorBase instance = new ValidatorBase();

    public static ValidatorBase getInstance()
    {
        return instance;
    }    
    
    public ValidatorBase()
    {
        
    }
    
    /* (non-Javadoc)
     * @see allbinary.logic.control.validate.ValidatorBaseInterface#isValid()
     */
    @Override
    public Boolean isValid()
    {
       return BooleanFactory.getInstance().TRUE;
    }
    
    /* (non-Javadoc)
     * @see allbinary.logic.control.validate.ValidatorBaseInterface#validationInfo()
     */
    @Override
    public String validationInfo()
    {
       return StringUtil.getInstance().EMPTY_STRING;
    }

    /* (non-Javadoc)
     * @see allbinary.logic.control.validate.ValidatorBaseInterface#toHashMap()
     */
    @Override
    public Hashtable toHashtable()
    {
       return new Hashtable();
    }

    /* (non-Javadoc)
     * @see allbinary.logic.control.validate.ValidatorBaseInterface#toVector()
     */
    @Override
    public BasicArrayList toList()
    {
       return BasicArrayListUtil.getInstance().getImmutableInstance();
    }
}
