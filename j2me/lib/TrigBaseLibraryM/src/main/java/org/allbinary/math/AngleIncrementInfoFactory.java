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
    
    private final AngleIncrementInfo[] angleIncrementInfoArray = new AngleIncrementInfo[(int) AngleFactory.getInstance().TOTAL_ANGLE];
    
    public AngleIncrementInfo getInstance(short angleIncrement)
    {
       //Integer integer = SmallIntegerSingletonFactory.getInstance(angleIncrement);
        
        final int halfAngleIncrement = ((int) angleIncrement >> 1);
        
       AngleIncrementInfo angleIncrementInfo = angleIncrementInfoArray[halfAngleIncrement];
       //hashtable.get(integer);
       
       if(angleIncrementInfo == null)
       {
           //Integer integer = SmallIntegerSingletonFactory.getInstance(angleIncrement);
           angleIncrementInfo =
                   //new AngleIncrementInfo(integer);
                   new AngleIncrementInfo(angleIncrement);
          //hashtable.put(integer, angleIncrementInfo);
          angleIncrementInfoArray[halfAngleIncrement] = angleIncrementInfo;
       }
       
       return angleIncrementInfo;
    }
}
