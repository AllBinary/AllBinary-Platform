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

import org.allbinary.logic.NullUtil;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

public class AllBinarySensorManager
{

    private static Object instance = NullUtil.getInstance().NULL_OBJECT;

    public static AllBinarySensorManager getInstance()
    {
        if(AllBinarySensorManager.instance == NullUtil.getInstance().NULL_OBJECT) {
            AllBinarySensorManager.instance = new AllBinarySensorManager();
        }
        
        return (AllBinarySensorManager) AllBinarySensorManager.instance;
    }

    public final String ORIENTATION_SENSOR_INPUT = "Orientation Sensor Input";
    public final String ORIENTATION_TYPE = "Orientation Type";
    
    private AllBinarySensorManager()
    {
    }
    
    //private final AllBinarySensor[] allBinarySensorArray = new AllBinarySensor[0];
    //public AllBinarySensor[] init()
    public void init()
    {
    }
    
    private final BasicArrayList list = new BasicArrayListD();
    public BasicArrayList getSensorNamesList()
    {
        return this.list;
    }

    public void shutdown()
    {
    }
}
