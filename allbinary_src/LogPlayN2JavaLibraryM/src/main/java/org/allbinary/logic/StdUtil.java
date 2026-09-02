/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.logic;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Stack;
import jsinterop.annotations.JsType;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsMethod;
import org.allbinary.util.ABHashtable;

/**
 *
 * @author User
 */
//ActualPlatform
@JsType
public class StdUtil {
    
    private static final StdUtil instance = new StdUtil();

    /**
     * @return the instance
     */
    //ActualPlatform
    @JsMethod
    public static StdUtil getInstance() {
        return StdUtil.instance;
    }

    //ActualPlatform
    @JsProperty
    public final Hashtable NULL_TABLE = this.createHashtable();
    
    //ActualPlatform
    @SuppressWarnings("unusable-by-js")
    public final HashMap NULL_MAP = this.createHashMap();
    
    public final Stack createStack() {
        return new Stack();
    }
    
    public final Hashtable createHashtable() {
        return new ABHashtable();
    }

    public final HashMap createHashMap() {
        return new HashMap();
    }
    
}
