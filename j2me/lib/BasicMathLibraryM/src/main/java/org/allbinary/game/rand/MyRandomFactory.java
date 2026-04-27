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
package org.allbinary.game.rand;

import java.util.Random;

import org.allbinary.logic.math.MathUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;

//Do not use in client code for money related things.
public class MyRandomFactory
{
    private static final MyRandomFactory instance = new MyRandomFactory();

    public static MyRandomFactory getInstance()
    {
        return MyRandomFactory.instance;
    }

    private final MathUtil mathUtil = MathUtil.getInstance();
    
    private Random rand;

    private MyRandomFactory()
    {
        this.rand = new Random(System.currentTimeMillis());
    }

    public void setSeed(long seed)
    {
        this.rand = new Random(seed);
    }

    //Example: 3 would result in the possible results 0, 1, 2, -1, -2.
    public int getNextInt(int range)
    {
        int div = (Integer.MAX_VALUE / range) + 1;
        return this.rand.nextInt() / div;
    }

    public int getAbsoluteNextInt(int range)
    {
        return this.mathUtil.abs(this.getNextInt(range));
    }

    public int getAbsoluteNextIntAllowZero(int range)
    {
        if (range == 0)
        {
            return 0;
        }
        else
        {
            return this.mathUtil.abs(this.getNextInt(range));
        }
    }
    
    public void shuffle(final int[] intArray) {
        this.shuffleTotal(intArray, intArray.length * 7);
    }
    
    public void shuffleTotal(final int[] intArray, final int shuffleTotal) {
        final int size = intArray.length;
        int randomIndex;
        int randomIndex2;
        int value;
        for(int index = 0; index < shuffleTotal; index++) {
            randomIndex = this.getAbsoluteNextIntAllowZero(size);
            randomIndex2 = this.getAbsoluteNextIntAllowZero(size);
            value = intArray[randomIndex];
            intArray[randomIndex] = intArray[randomIndex2];
            intArray[randomIndex2] = value;
        }
    }

    public void shuffle2(final int[] intArray, final int[] intArray2) {
        this.shuffle2Total(intArray, intArray2, intArray.length * 7);
    }
    
    public void shuffle2Total(final int[] intArray, final int[] intArray2, final int shuffleTotal) {
        final int size = intArray.length;
        int randomIndex;
        int randomIndex2;
        int value;
        int value2;
        for(int index = 0; index < shuffleTotal; index++) {
            randomIndex = this.getAbsoluteNextIntAllowZero(size);
            randomIndex2 = this.getAbsoluteNextIntAllowZero(size);
            value = intArray[randomIndex];
            value2 = intArray2[randomIndex];
            intArray[randomIndex] = intArray[randomIndex2];
            intArray[randomIndex2] = value;
            intArray2[randomIndex] = intArray2[randomIndex2];
            intArray2[randomIndex2] = value2;
        }
    }
    
    public static void main(final String[] args) {
        final MyRandomFactory randomFactory = MyRandomFactory.getInstance();
        
        final StringMaker stringBuilder = new StringMaker();
        final CommonSeps commonSeps = CommonSeps.getInstance();
        
        final int size2 = 100;
        for(int index = 0; index < size2; index++) {
            stringBuilder.appendint(randomFactory.getAbsoluteNextInt(3)).append(commonSeps.COMMA);
        }
        
        System.out.println(stringBuilder.toString());
        
        stringBuilder.delete(0, stringBuilder.length());
        
        final int[] intArray = new int[52];
        final int[] intArray2 = new int[52];
        final int size = intArray.length;
        for(int index = 0; index < size; index++) {
            intArray[index] = index;
            intArray2[index] = index;
        }
        randomFactory.shuffle2(intArray, intArray2);
        
        for(int index = 0; index < size; index++) {
            stringBuilder.appendint(intArray[index]).append(commonSeps.COMMA);
        }
        
        System.out.println(stringBuilder.toString());
        
        stringBuilder.delete(0, stringBuilder.length());
        for(int index = 0; index < size; index++) {
            stringBuilder.appendint(intArray2[index]).append(commonSeps.COMMA);
        }
        
        System.out.println(stringBuilder.toString());
        
        //Validation
        boolean found;
        for(int index = 0; index < size; index++) {
            found = false;
            for(int index2 = 0; index2 < size; index2++) {
                if(intArray[index2] == index) {
                    found = true;
                }
            }
            if(!found) throw new RuntimeException();
        }
        
    }
}