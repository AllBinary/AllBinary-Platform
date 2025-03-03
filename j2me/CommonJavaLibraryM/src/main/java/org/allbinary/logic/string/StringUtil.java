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
package org.allbinary.logic.string;

public class StringUtil {

    private static final StringUtil instance = new StringUtil();

    public static StringUtil getInstance() {
        return instance;
    }

    public final String NULL_STRING = "null";
    public final String EMPTY_STRING = "";
    public String[] ONE_EMPTY_STRING_ARRAY = {this.EMPTY_STRING};
    private final String[] stringArray = new String[0];

    private StringUtil() {
    }

    //Could Make a NulString
    public String getInstance(String string) {
        if (string == null) {
            return EMPTY_STRING;
        } else {
            return string;
        }
    }

    public String[] getArrayInstance() {
        return stringArray;
    }

    public int count(final String string, final char aChar) {
        int count = 0;

        final int size = string.length();
        for(int index = 0; index < size; index++) {
            if(string.charAt(index) == aChar) {
                count++;
            }
        }
        return count;
    }
    
    public String toString(final Object object) {
        if(object != null) {
            return object.toString();
        } else {
            return this.NULL_STRING;
        }
    }

}
