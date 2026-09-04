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
import java.util.Vector;

/**
 *
 * @author User
 */
//NoPlatform
public class StdUtil {
    
    private static final StdUtil instance = new StdUtil();

    /**
     * @return the instance
     */
    //ActualPlatform
    public static StdUtil getInstance() {
        return StdUtil.instance;
    }

    //ActualPlatform
    public final Vector<Object> EMPTY_VECTOR = this.createVector();
    
    //ActualPlatform
    public final Hashtable NULL_TABLE = this.createHashtable();
    
    public final Stack<Object> createStack() {
        return new Stack<Object>();
    }

    public final Vector<Object> createVector() {
        return new Vector<Object>();
    }
    
    public final Hashtable<Object, Object> createHashtable() {
        return new Hashtable<Object, Object>();
    }

    public final HashMap<Object, Object> createHashMap() {
        return new HashMap<Object, Object>();
    }

}
