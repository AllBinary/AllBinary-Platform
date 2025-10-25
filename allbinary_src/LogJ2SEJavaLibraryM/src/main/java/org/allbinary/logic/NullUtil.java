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

/**
 *
 * @author User
 */
//ActualPlatform
public class NullUtil {
    
    private static final NullUtil instance = new NullUtil();

    /**
     * @return the instance
     */
    //ActualPlatform
    public static NullUtil getInstance() {
        return instance;
    }
    
    //ActualPlatform
    public final Object NULL_OBJECT = new Object();
    //ActualPlatform
    public final Class NULL_CLASS = NULL_OBJECT.getClass();
    //ActualPlatform
    public final Object[] NULL_OBJECT_ARRAY = new Object[0];
    //ActualPlatform
    public final byte[] NULL_BYTE_ARRAY = new byte[0];
    //ActualPlatform
    public final char[] NULL_CHAR_ARRAY = new char[0];
    //ActualPlatform
    public final short[][] NULL_SHORT_ARRAY_ARRAY = new short[0][0];
    //ActualPlatform
    public final int[] NULL_INT_ARRAY = new int[0];
    //ActualPlatform
    public final int[][] NULL_INT_ARRAY_ARRAY = new int[0][0];
    //ActualPlatform
    public final int[][][] NULL_INT_ARRAY_ARRAY_ARRAY = new int[0][0][0];
    //ActualPlatform
    public final float[] NULL_FLOAT_ARRAY = new float[0];

    //ActualPlatform
    public final Vector<Object> EMPTY_VECTOR = new Vector<Object>();
    //ActualPlatform
    public final Hashtable NULL_TABLE = new Hashtable();
    
    //ActualPlatform
    public final HashMap NULL_MAP = new HashMap();
    
}
