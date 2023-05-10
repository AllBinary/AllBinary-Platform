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

import org.allbinary.logic.basic.string.StringValidationUtil;

public class BooleanUtil
{
    private static final BooleanUtil instance = new BooleanUtil();

    /**
     * @return the instance
     */
    public static BooleanUtil getInstance()
    {
        return instance;
    }
    
    private BooleanUtil()
    {
    }

    public boolean getFromString(final String booleanString) throws Exception
    {
        final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        final BooleanFactory booleanFactory = BooleanFactory.getInstance();

        if (stringValidationUtil.isEmpty(booleanString)) {
            return false;
        }

        final String booleanStringLowerCase = booleanString.toLowerCase();

        //compareToIgnoreCase is probably faster
        if (booleanStringLowerCase.compareTo(booleanFactory.TRUE_STRING) == 0)
        {
            return true;
        } else if(booleanStringLowerCase.compareTo(booleanFactory.FALSE_STRING) == 0) {
            return false;
        } else { 
            throw new Exception("Invalid booleanString - Must be true or false"); 
        }

        //final Boolean bool = Boolean.valueOf(booleanString);
        //return bool.booleanValue();
    }

    public boolean isStringBoolean(final String booleanString)
            throws Exception
    {
        final BooleanFactory booleanFactory = BooleanFactory.getInstance();
        
        final String booleanStringLowerCase = booleanString.toLowerCase();
        
        //compareToIgnoreCase is probably faster
        if (booleanStringLowerCase.compareTo(booleanFactory.TRUE_STRING) == 0
                || booleanStringLowerCase.compareTo(booleanFactory.FALSE_STRING) == 0)
        {
            return true;
        }
        return false;
    }
}
