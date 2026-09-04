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

import java.util.Vector;
import jsinterop.annotations.JsType;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsMethod;
import org.allbinary.util.ABHashMap;

import org.allbinary.util.ABHashtable;
import org.allbinary.util.ABStack;
import org.allbinary.util.ABVector;

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
    public final ABVector<Object> EMPTY_VECTOR = this.createVector();
    
    //ActualPlatform
    @JsProperty
    public final ABHashtable NULL_TABLE = this.createHashtable();
    
    //ActualPlatform
    @SuppressWarnings("unusable-by-js")
    public final ABHashMap NULL_MAP = this.createHashMap();
    
    public final ABStack<Object> createStack() {
        return new ABStack<Object>();
    }

    public final ABVector<Object> createVector() {
        return new ABVector<Object>();
    }
    
    public final ABHashtable<Object, Object> createHashtable() {
        return new ABHashtable<Object, Object>();
    }

    public final ABHashMap<Object, Object> createHashMap() {
        return new ABHashMap<Object, Object>();
    }
    
}
