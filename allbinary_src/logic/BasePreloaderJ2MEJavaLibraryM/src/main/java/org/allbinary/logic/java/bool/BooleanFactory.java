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

public class BooleanFactory
{
    private static final BooleanFactory instance = new BooleanFactory();

    public static BooleanFactory getInstance()
    {
        return instance;
    }

    //public final Boolean TRUE = Boolean.TRUE;
    //public final Boolean FALSE = Boolean.FALSE;    
    public final Boolean TRUE = new Boolean(true); //Boolean.TRUE;
    public final Boolean FALSE = new Boolean(false); //Boolean.FALSE;

    public final String TRUE_STRING = TRUE.toString();
    public final String FALSE_STRING = FALSE.toString();

    public String toString(boolean bool)
    {
        if(bool)
        {
            return TRUE_STRING;
        }
        else
        {
            return FALSE_STRING;
        }
    }
}
