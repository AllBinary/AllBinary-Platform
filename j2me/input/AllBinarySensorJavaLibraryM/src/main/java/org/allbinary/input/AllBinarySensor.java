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


import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class AllBinarySensor
implements AllBinarySensorListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
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
        logUtil.put(commonStrings.START, this, "shutdown");
    }

    @Override    
    public void onSensorChange()
    {
        ForcedLogUtil.log(commonStrings.NOT_IMPLEMENTED, this);
    }
}
