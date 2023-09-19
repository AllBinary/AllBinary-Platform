/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.graphics.displayable.screen;

import org.allbinary.graphics.GPoint;

/**
 *
 * @author User
 */
public class DisplayPointScalar {
    
    private static final DisplayPointScalar instance = new DisplayPointScalar();

    /**
     * @return the instance
     */
    public static DisplayPointScalar getInstance() {
        return instance;
    }
    
    public int processX(final int value) {
        return value;
    }

    public int processY(final int value) {
        return value;
    }
    
    public GPoint process(final GPoint point) {
        return point;
    }
}
