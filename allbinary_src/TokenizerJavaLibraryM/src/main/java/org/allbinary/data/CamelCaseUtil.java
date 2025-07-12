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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.string.tokens.Tokenizer;
import org.allbinary.string.CommonSeps;
import org.allbinary.util.BasicArrayList;

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

    private final CommonSeps commonSeps = CommonSeps.getInstance();
    //private final String FORMAT = "[\\W_]+";

    public String getAsCamelCase(final String string, final StringMaker stringBuilder) throws Exception {

        if(string == null) {
            return StringUtil.getInstance().EMPTY_STRING;
        }

        final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();    
        
        stringBuilder.delete(0, stringBuilder.length());
        
        final BasicArrayList list = new BasicArrayList();
        final Tokenizer tokenizer = new Tokenizer(commonSeps.UNDERSCORE);
        tokenizer.getTokens(string, list);
        //final String[] words = string.split(FORMAT);
        //final int size = words.length;
        
        final int size = list.size();
        String word;
        for (int i = 0; i < size; i++)
        {
            //word = words[i];
            word = (String) list.get(i);
            
            //if (i == 0)
            //{
                //word = word.isEmpty() ? word : word.toLowerCase();
            //} else
            //{
                word = stringValidationUtil.isEmpty(word) ? word : new StringMaker().append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).toString();
            //}
            stringBuilder.append(word);
        }
        return stringBuilder.toString();
    }
}
