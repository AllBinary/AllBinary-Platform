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
package org.allbinary.logic.util.cache;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.basic.string.StringMaker;

/**
 *
 * @author user
 */
public class BaseBasicArrayListCache {

    private final BasicArrayList list = new BasicArrayList();

    /**
     * @return the list
     */
    protected BasicArrayList getList()
    {
        return list;
    }

    public String log()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("S: ");
        stringBuffer.append(this.getList().size());

        return stringBuffer.toString();
    }
}
