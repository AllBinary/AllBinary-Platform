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
package org.allbinary.logic.system.hardware.components.android;

import org.allbinary.logic.system.hardware.components.interfaces.HardwareComponentInterface;

public class UnknownHardware implements HardwareComponentInterface
{
    private String data;
    
    public UnknownHardware(String data)
    {
        this.data = data;
    }
    
    @Override
    public boolean compareTo(HardwareComponentInterface componentInterface)
    {
        return true;
    }
    
    public String toString()
    {
       return data;        
    }
}
