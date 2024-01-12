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

//Do not use in client code for money related things.
public class MyRandomFactory
{
    private static final MyRandomFactory instance = new MyRandomFactory();

    public static MyRandomFactory getInstance()
    {
        return instance;
    }

    private Random rand;

    private MyRandomFactory()
    {
        rand = new Random(System.currentTimeMillis());
    }

    public void setSeed(long seed)
    {
        rand = new Random(seed);
    }

    public int getNextInt(int range)
    {
        int div = (Integer.MAX_VALUE / range) + 1;
        return rand.nextInt() / div;
    }

    public int getAbsoluteNextInt(int range)
    {
        return Math.abs(this.getNextInt(range));
    }

    public int getAbsoluteNextIntAllowZero(int range)
    {
        if (range == 0)
        {
            return 0;
        }
        else
        {
            return Math.abs(this.getNextInt(range));
        }
    }
    
    public void shuffle(final int[] intArray) {
        this.shuffle(intArray, intArray.length * 7);
    }
    
    public void shuffle(final int[] intArray, final int shuffleTotal) {
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
    
    public static void main(final String[] args) {
        final MyRandomFactory randomFactory = MyRandomFactory.getInstance();
        final int[] intArray = new int[52];
        final int size = intArray.length;
        for(int index = 0; index < size; index++) {
            intArray[index] = index;
        }
        randomFactory.shuffle(intArray);
        
        final StringBuilder stringBuilder = new StringBuilder();
        for(int index = 0; index < size; index++) {
            stringBuilder.append(intArray[index]).append(',');
        }
        
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
        
        System.out.println(stringBuilder.toString());
    }
}