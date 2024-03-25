/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
package org.allbinary.math;

import org.allbinary.logic.math.MathUtil;

public class DistanceUtil
{
    private static final DistanceUtil instance = new DistanceUtil();

    /**
     * @return the instance
     */
    public static DistanceUtil getInstance() {
        return instance;
    }
    
    private final MathUtil mathUtil = MathUtil.getInstance();
    
    public int getDistance(final int x1, final int y1, final int x2, final int y2)
    {
        final int dx = x1 - x2;
        final int dy = y1 - y2;
        return (int) mathUtil.sqrt((dx * dx) + (dy * dy));
    }
}
