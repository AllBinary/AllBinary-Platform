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
package allbinary.logic.math.vector;

import allbinary.math.NoDecimalTrigTable;

public class AxisMathVectorUtil
{
    private static final AxisMathVectorUtil instance = new AxisMathVectorUtil();

    public static AxisMathVectorUtil getInstance()
    {
        return instance;
    }
    
    private final NoDecimalTrigTable noDecimalTrigTable = NoDecimalTrigTable.getInstance();
    
    public long calculateX(long magnitude, short angle)
    {
        return magnitude * noDecimalTrigTable.cos(angle) / 10;
    }
    
    public long calculateY(long magnitude, short angle)
    {
       return magnitude * noDecimalTrigTable.sin(angle) / 10;
    }    
}
