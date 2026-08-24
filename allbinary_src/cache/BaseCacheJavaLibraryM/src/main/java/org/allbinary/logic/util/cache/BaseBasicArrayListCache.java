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

import jsinterop.annotations.JsType;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author user
 */

@JsType
public class BaseBasicArrayListCache {

    @JsProperty
    protected final BasicArrayList list = new BasicArrayListD();

    @JsMethod
    public String log()
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("S: ");
        stringBuffer.appendint(this.list.size());

        return stringBuffer.toString();
    }
}
