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
package org.allbinary.logic.math;

import org.allbinary.logic.NullUtil;

public class PrimitiveIntUtil
{
    private PrimitiveIntUtil()
    {
        
    }
    
    public static int[] getArrayInstance()
    {
        return NullUtil.getInstance().NULL_INT_ARRAY;
    }

    public static int[][] getTwoDimensionalArrayInstance()
    {
        return NullUtil.getInstance().NULL_INT_ARRAY_ARRAY;
    }
}
