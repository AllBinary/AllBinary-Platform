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
package org.allbinary.data.tables;

import org.allbinary.string.CommonPhoneStrings;

/**
 *
 * @author user
 */
public class TableDataFactory {

    private static final TableDataFactory instance = new TableDataFactory();

    /**
     * @return the instance
     */
    public static TableDataFactory getInstance()
    {
        return instance;
    }


    public final String INTEGER_MAX_VALUE_STRING = Integer.toString(Integer.MAX_VALUE);
    public final String ZERO_STRING = CommonPhoneStrings.getInstance().ZERO;
}
