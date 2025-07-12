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
package org.allbinary.logic.string;

public class StringValidationUtil
{
    private static final StringValidationUtil instance = new StringValidationUtil();

    public static StringValidationUtil getInstance()
    {
        return instance;
    }

    private final StringUtil stringUtil = StringUtil.getInstance();
    
    private StringValidationUtil()
    {
    }
    
    public boolean containsSpaces(String value)
    {
        if (value.indexOf(' ') >= 0)
        {
            return true;
        }

        return false;
    }

    public boolean isNumber(String value)
    {
        int numberOfDecimalPoints = 0;
        for (int index = 0; index < value.length(); index++)
        {
            char digit = value.charAt(index);

            if(!isNumber(digit) && digit != '.')
            {
                return false;
            }
            
            if (digit == '.')
            {
                numberOfDecimalPoints++;
                if (numberOfDecimalPoints > 1)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isNumber(char digit)
    {
        if (digit != '0'
            && digit != '1'
            && digit != '2'
            && digit != '3'
            && digit != '4'
            && digit != '5'
            && digit != '6'
            && digit != '7'
            && digit != '8'
            && digit != '9')
        {
            return false;
        }

        return true;
    }

    public boolean isValidRequired(String value, int min, int max)
    {
        //value.compareTo(stringUtil.EMPTY_STRING)==0 ||
        if (value == null || value.length() < min || value.length() > max)
        {
            return false;
        }
        return true;
    }

    public boolean isValidRequiredNumber(String value, int min, int max)
    {
        if (this.isEmpty(value)
                || value.length() < min || value.length() > max)
        {
            return false;
        }

        if (!this.isNumber(value))
        {
            return false;
        }
        return true;
    }

    public boolean isValidNotRequired(String value, int min, int max)
    {
        //value.compareTo(stringUtil.EMPTY_STRING)==0 ||
        if (value != null)
        {
            if (value.length() < min || value.length() > max)
            {
                return false;
            }
        }
        return true;
    }

    public boolean isValidNotRequiredNumber(String value, int min, int max)
    {
        if (value != null)
        {
            if (value.compareTo(stringUtil.NULL_STRING) == 0 || value.length() < min || value.length() > max)
            {
                return false;
            }

            if (!this.isNumber(value))
            {
                return false;
            }

        }
        return true;
    }

    public boolean isEmpty(String string)
    {
        if (string != null
                && string.compareTo(stringUtil.NULL_STRING) != 0
                && string.compareTo(stringUtil.EMPTY_STRING) != 0)
        {
            return false;
        } else
        {
            return true;
        }
    }

}
