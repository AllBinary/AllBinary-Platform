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
package org.allbinary.logic.math;

import org.allbinary.logic.string.StringMaker;

public class PrimitiveLongSingleton
{
    private static final PrimitiveLongSingleton instance = new PrimitiveLongSingleton();

    public static PrimitiveLongSingleton getInstance()
    {
        return instance;
    }
    
    public final char[] UNK = {'U', 'n', 'k'};
    
    //public final String[] NUMBER_STRING_ARRAY = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public final byte[] NUMBER_ARRAY = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    public final char[] NUMBER_CHAR_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public final char[][] NUMBER_CHAR_ARRAYS = {{'0'}, {'1'}, {'2'}, {'3'}, {'4'}, {'5'}, {'6'}, {'7'}, {'8'}, {'9'}};
    
    public final char[] ZERO = new char[]{this.NUMBER_CHAR_ARRAY[0]};

    public byte getNumberFromChar(char numberAsChar) throws Exception {
        final int size = NUMBER_CHAR_ARRAY.length;
        for(int index = 0; index < size; index++) {
            if(NUMBER_CHAR_ARRAY[index] == numberAsChar) {
                return this.NUMBER_ARRAY[index];
            }
        }
        throw new Exception(new StringMaker().append("numberAsChar was not in array: ").append(numberAsChar).toString());
    }
}
