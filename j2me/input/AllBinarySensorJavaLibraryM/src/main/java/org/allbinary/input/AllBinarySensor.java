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
package org.allbinary.input;


import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class AllBinarySensor
implements AllBinarySensorListener
{
    private static int max = 100;
    
    protected AllBinarySensor()
    {
        
    }
    
    public void init()
    {
    }

    public void update()
    throws Exception
    {
        
    }
    
    public static void setMax(int max)
    {
        AllBinarySensor.max = max;
    }
    
    public static int getMax()
    {
        return max;
    }
    
    public int getId()
    {
        return AllBinarySensor.getMax();
    }
    
    public void shutdown()
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "shutdown"));
    }
    
    public void onSensorChange()
    {
        ForcedLogUtil.log(CommonStrings.getInstance().NOT_IMPLEMENTED, this);
    }
}
