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

import org.allbinary.util.ABHashMap;
import org.allbinary.util.ABHashtable;
import org.allbinary.util.ABStack;
import org.allbinary.util.ABVector;

/**
 *
 * @author User
 */
//ActualPlatform
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
    public final ABVector<Object> EMPTY_VECTOR = this.createVector();
    
    //ActualPlatform
    public final ABHashtable NULL_TABLE = this.createHashtable();
    
    //ActualPlatform
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
