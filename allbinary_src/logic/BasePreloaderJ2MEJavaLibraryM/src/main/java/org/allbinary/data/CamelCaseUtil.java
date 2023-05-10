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
package org.allbinary.data;

import org.allbinary.logic.basic.string.StringMaker;

/**
 *
 * @author User
 */
public class CamelCaseUtil
{
    private static final CamelCaseUtil instance = new CamelCaseUtil();

    /**
     * @return the instance
     */
    public static CamelCaseUtil getInstance()
    {
        return instance;
    }

    private final String FORMAT = "[\\W_]+";

    public String getAsCamelCase(final String string, final StringMaker stringBuilder) {

        stringBuilder.delete(0, stringBuilder.length());
        
        final String[] words = string.split(FORMAT);
        
        for (int i = 0; i < words.length; i++)
        {
            String word = words[i];
            //if (i == 0)
            //{
                //word = word.isEmpty() ? word : word.toLowerCase();
            //} else
            //{
                word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
            //}
            stringBuilder.append(word);
        }
        return stringBuilder.toString();
    }

}
