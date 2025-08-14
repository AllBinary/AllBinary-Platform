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
public class NullUtil {
    
    private static final NullUtil instance = new NullUtil();

    /**
     * @return the instance
     */
    public static NullUtil getInstance() {
        return instance;
    }
    
    public final Object NULL_OBJECT = new Object();
    public final Object[] NULL_OBJECT_ARRAY = new Object[0];
    public final byte[] NULL_BYTE_ARRAY = new byte[0];
    public final char[] NULL_CHAR_ARRAY = new char[0];
    public final int[] NULL_INT_ARRAY = new int[0];
    public final int[][] NULL_INT_ARRAY_ARRAY = new int[0][0];
    public final int[][][] NULL_INT_ARRAY_ARRAY_ARRAY = new int[0][0][0];
    public final float[] NULL_FLOAT_ARRAY = new float[0];
    
    public final Vector<Object> EMPTY_VECTOR = new Vector<Object>();
    public final Hashtable NULL_TABLE = new Hashtable();
    
    public final HashMap NULL_MAP = new HashMap();
    
}
