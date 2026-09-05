/*
 * AllBinary Open License Version 1
 * Copyright (c) 2026 AllBinary
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

import java.util.Hashtable;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsType;

/**
 *
 * @author User
 */
@JsType
public class ABHashtable<K,V> extends Hashtable<K,V> {
    
    //GWT does not have the other constructors.
//    public ABHashtable(final int initialCapacity) {
//        super();
//    }

    @JsConstructor
    public ABHashtable() {
        super();
    }
    
}
