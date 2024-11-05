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
package org.allbinary.util;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.game.rand.MyRandomFactory;
import org.allbinary.logic.string.CommonLabels;

/**
 * 
 * @author user
 */
public class BasicArrayListUtil
{
    private static final BasicArrayListUtil instance = new BasicArrayListUtil();

    public static BasicArrayListUtil getInstance()
    {
        return instance;
    }

    private final MyRandomFactory myRandomFactory = MyRandomFactory.getInstance();

    // I should make immutable and throw on attempted edit
    private final BasicArrayList immutableInstance = new ImmutableBasicArrayList(0);

    public Object getRandom(BasicArrayList list)
    {
        //if(list.size() == 0)
        //{
          //  throw new Exception("List size was zero");
        //}
        
        int i_random = this.getRandomIndex(list);
        return list.objectArray[i_random];
    }

    public int getRandomIndex(BasicArrayList list)
    {
        return myRandomFactory.getAbsoluteNextInt(list.size());
    }

    public String log(BasicArrayList list)
    {
        int size = list.size();

        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append(CommonLabels.getInstance().TOTAL_LABEL);
        stringBuffer.append(size);

        for (int index = size - 1; index >= 0; index--)
        {
            BasicArrayList usedList = (BasicArrayList) list.objectArray[index];

            stringBuffer.append(" s: ");
            stringBuffer.append(usedList.size());
        }
        return stringBuffer.toString();
    }

    /**
     * @return the immutableInstance
     */
    public BasicArrayList getImmutableInstance()
    {
        return immutableInstance;
    }
}
