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
import java.util.Vector;
import jsinterop.annotations.JsType;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author User
 */
//ActualPlatform
@JsType
public class NullUtil {
    
    private static final NullUtil instance = new NullUtil();

    /**
     * @return the instance
     */
    //ActualPlatform
    @JsMethod
    public static NullUtil getInstance() {
        return NullUtil.instance;
    }
    
    //ActualPlatform
    @JsProperty
    public final Object NULL_OBJECT = new Object();
    //ActualPlatform
    @SuppressWarnings("unusable-by-js")
    public final Class NULL_CLASS = this.NULL_OBJECT.getClass();
    //ActualPlatform
    @JsProperty
    public final Object[] NULL_OBJECT_ARRAY = new Object[0];
    //ActualPlatform
    @JsProperty
    public final byte[] NULL_BYTE_ARRAY = new byte[0];
    //ActualPlatform
    @JsProperty
    public final char[] NULL_CHAR_ARRAY = new char[0];
    //ActualPlatform
    @JsProperty
    public final short[][] NULL_SHORT_ARRAY_ARRAY = new short[0][0];
    //ActualPlatform
    @JsProperty
    public final int[] NULL_INT_ARRAY = new int[0];
    //ActualPlatform
    @JsProperty
    public final int[][] NULL_INT_ARRAY_ARRAY = new int[0][0];
    //ActualPlatform
    @JsProperty
    public final int[][][] NULL_INT_ARRAY_ARRAY_ARRAY = new int[0][0][0];
    //ActualPlatform
    @JsProperty
    public final float[] NULL_FLOAT_ARRAY = new float[0];

    //ActualPlatform
    @JsProperty
    public final Vector<Object> EMPTY_VECTOR = new Vector<Object>();
    //ActualPlatform
    @JsProperty
    public final Hashtable NULL_TABLE = new Hashtable();
    
    //ActualPlatform
    @SuppressWarnings("unusable-by-js")
    public final HashMap NULL_MAP = new HashMap();
    
}
