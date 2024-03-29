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

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.hardware.components.interfaces.HardwareComponentInterface;
import org.allbinary.logic.system.hardware.components.interfaces.MachineAccessControlAddressInterface;

public class MachineAccessControlAddress implements HardwareComponentInterface, MachineAccessControlAddressInterface
{        
    public MachineAccessControlAddress()
    {        
    }
    
    public boolean compareTo(HardwareComponentInterface componentInterface)
    {
        return true;
    }
    
    public String toString()
    {
       return StringUtil.getInstance().EMPTY_STRING;
    }
}
