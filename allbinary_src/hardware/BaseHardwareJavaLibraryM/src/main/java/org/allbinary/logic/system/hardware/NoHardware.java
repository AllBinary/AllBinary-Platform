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
import org.allbinary.logic.NullUtil;

import org.allbinary.logic.system.hardware.components.interfaces.HardwareComponentInterface;
import org.allbinary.logic.system.hardware.components.interfaces.NoHardwareComponent;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class NoHardware implements HardwareInterface
{
    private static final HardwareInterface instance = new NoHardware();

    /**
     * @return the instance
     */
    public static HardwareInterface getInstance() {
        return instance;
    }
    
    @Override
    public HardwareComponentInterface getComponent(int index)
    {
        return NoHardwareComponent.getInstance();
    }

    public String toString()
    {
        return "No Hardware";
    }

    @Override
    public boolean compareTo(HardwareInterface hardwareInterface)
    {
        return true;
    }

    @Override
    public Hashtable difference(HardwareInterface hardwareInterface)
    {
        return NullUtil.getInstance().NULL_TABLE;
    }    
}
