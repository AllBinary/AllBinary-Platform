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
package abcs.logic.basic.string;

public class StringValidationJSEUtil
{
    private StringValidationJSEUtil()
    {
    }

    public static boolean isBlank(String string)
    {
        int len = string.length();
        for (int i = 0; i < len; i++)
        {
            char c = string.charAt(i);
            if (Character.isWhitespace(c) == false)
            {
                return false;
            }
        }
        return true;
    }
}
