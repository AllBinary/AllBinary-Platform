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

/**
 *
 * @author User
 */
public class ABHashtable<K,V> extends Hashtable {
    
    public ABHashtable(final int initialCapacity) {
        super(initialCapacity);
    }

    public ABHashtable() {
        super();
    }
    
}
