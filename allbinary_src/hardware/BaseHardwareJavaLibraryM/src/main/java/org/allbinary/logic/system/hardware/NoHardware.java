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
package org.allbinary.logic.system.hardware;

import java.util.Hashtable;

import org.allbinary.logic.system.hardware.components.interfaces.HardwareComponentInterface;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class NoHardware implements HardwareInterface
{
    public HardwareComponentInterface getComponent(int index)
    {
        return null;
    }

    public String toString()
    {
        return "No Hardware";
    }

    public boolean compareTo(HardwareInterface hardwareInterface)
    {
        return true;
    }

    public Hashtable difference(HardwareInterface hardwareInterface)
    {
        return new Hashtable();
    }    
}
