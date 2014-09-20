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
package org.allbinary.logic.system.hardware.components.linux;

import org.allbinary.logic.system.hardware.components.interfaces.HardwareComponentInterface;
import org.allbinary.logic.system.hardware.components.interfaces.MonitorInterface;

public class Monitor implements HardwareComponentInterface, MonitorInterface
{
    private String data;
    
    public Monitor(String data)
    {
        this.data = data;
    }
    
    public boolean compareTo(HardwareComponentInterface componentInterface)
    {
        return true;
    }
    
    public String toString()
    {
       return data;        
    }
    
}
