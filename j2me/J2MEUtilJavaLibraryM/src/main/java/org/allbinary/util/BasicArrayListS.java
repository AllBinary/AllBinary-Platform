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
package org.allbinary.util;

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringMaker;

/**
 *
 * @author User
 */
public class BasicArrayListS extends BasicArrayList {
    
    public BasicArrayListS(final int size)
    {
        super(NullUtil.getInstance().NULL_OBJECT_ARRAY);

        //currentIndex = 0;

        if (size < 0) {
            throw new IllegalArgumentException(new StringMaker().append("Init Size Exception: ").appendint(size).toString());
        }

        this.objectArray = new Object[size];
    }
    
    public BasicArrayListS()
    {
        this(7);
        
        //currentIndex = 0;
    }
    
}
