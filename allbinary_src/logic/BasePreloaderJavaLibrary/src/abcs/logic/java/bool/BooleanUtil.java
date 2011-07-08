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
package abcs.logic.java.bool;

import abcs.logic.basic.string.StringValidationUtil;

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

    public boolean getFromString(String booleanString) throws Exception
    {
        final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();

        if (stringValidationUtil.isEmpty(booleanString))
            return false;

        Boolean bool = Boolean.valueOf(booleanString);
        return bool.booleanValue();

        /*
         * if(booleanString.compareToIgnoreCase("true") == 0) { return true; }
         * else if(booleanString.compareToIgnoreCase("false") == 0) { return
         * false; } else { throw new
         * Exception("Invalid booleanString - Must be true or false"); }
         */
    }

    public boolean isStringBoolean(String booleanString)
            throws Exception
    {
        BooleanFactory booleanFactory = BooleanFactory.getInstance();
        
        if (booleanString.compareToIgnoreCase(booleanFactory.TRUE_STRING) == 0
                || booleanString.compareToIgnoreCase(booleanFactory.FALSE_STRING) == 0)
        {
            return true;
        }
        return false;
    }
}
