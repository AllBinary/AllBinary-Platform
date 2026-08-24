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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class StringUtil {

    private static final StringUtil instance = new StringUtil();

    @JsMethod
    public static StringUtil getInstance() {
        return StringUtil.instance;
    }

    @JsProperty
    public final String INIT_STRING = new String("");
    @JsProperty
    public final String NULL_STRING = "null";
    @JsProperty
    public final String EMPTY_STRING = "";
    @JsProperty
    public String[] ONE_EMPTY_STRING_ARRAY = {this.EMPTY_STRING};
    private final String[] stringArray = new String[0];

    @JsConstructor
    private StringUtil() {
    }

    //TWB - Could return a NULL_STRING
    @JsMethod
    public String getNonNull(String string) {
        if (string == null) {
            return this.EMPTY_STRING;
        } else {
            return string;
        }
    }

    @JsMethod
    public String[] getArrayInstance() {
        return this.stringArray;
    }

    @JsMethod
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
    
    @JsMethod
    public String toString(final Object object) {
        if(object != null) {
            return object.toString();
        } else {
            return this.NULL_STRING;
        }
    }

}
