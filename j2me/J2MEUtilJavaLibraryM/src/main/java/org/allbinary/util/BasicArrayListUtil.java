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

import org.allbinary.game.rand.MyRandomFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonPhoneStrings;

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

    public final BasicArrayList[][] NULL_ARRAY_OF_ARRAY = new BasicArrayList[0][0];

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

    public void reverse(final BasicArrayList list) {
        final int lastMinusOneIndex = list.size() - 2;
        Object temp;
        final int size = lastMinusOneIndex / 2;
        int secondSwapIndex;
        for (int index = 0; index <= size; index++) {
            secondSwapIndex = lastMinusOneIndex - index;
            temp = list.remove(index);
            list.add(index, list.remove(secondSwapIndex));
            list.add(secondSwapIndex + 1, temp);
            //System.out.println(list);
        }
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
    
    public static void main(String args[]) {
        final CommonPhoneStrings commonPhoneStrings = CommonPhoneStrings.getInstance();
        final BasicArrayList list = new BasicArrayList();
        list.add(commonPhoneStrings.ONE);
        list.add(commonPhoneStrings.TWO);
        list.add(commonPhoneStrings.THREE);
        list.add(commonPhoneStrings.FOUR);
        list.add(commonPhoneStrings.FIVE);
        list.add(commonPhoneStrings.SIX);
        list.add(commonPhoneStrings.SEVEN);
        list.add(commonPhoneStrings.EIGHT);
        list.add(commonPhoneStrings.NINE);
        
        BasicArrayListUtil.getInstance().reverse(list);
        
        System.out.println(list);
    }
    
}
