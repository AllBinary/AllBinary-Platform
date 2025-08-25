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

public class AngleIncrementInfoFactory
{
    private static final AngleIncrementInfoFactory instance = new AngleIncrementInfoFactory();

    public static AngleIncrementInfoFactory getInstance()
    {
        return instance;
    }
    
    private final AngleIncrementInfo[] angleIncrementInfo = new AngleIncrementInfo[(int) AngleFactory.getInstance().TOTAL_ANGLE];
    
    public AngleIncrementInfo getInstance(short angleIncrement)
    {
       //Integer integer = SmallIntegerSingletonFactory.getInstance(angleIncrement);
        
        final int halfAngleIncrement = ((int) angleIncrement >> 1);
        
       AngleIncrementInfo angleIncrementInfoCanBeNull = angleIncrementInfo[halfAngleIncrement];
       //hashtable.get(integer);
       
       if(angleIncrementInfoCanBeNull == null)
       {
           //Integer integer = SmallIntegerSingletonFactory.getInstance(angleIncrement);
           angleIncrementInfoCanBeNull =
                   //new AngleIncrementInfo(integer);
                   new AngleIncrementInfo(angleIncrement);
          //hashtable.put(integer, angleIncrementInfoCanBeNull);
          angleIncrementInfo[halfAngleIncrement] = angleIncrementInfoCanBeNull;
       }
       
       return angleIncrementInfoCanBeNull;
    }
}
