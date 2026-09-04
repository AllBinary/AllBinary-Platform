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

import org.allbinary.logic.StdUtil;

import org.allbinary.logic.system.hardware.components.interfaces.HardwareComponentInterface;
import org.allbinary.logic.system.hardware.components.interfaces.NoHardwareComponent;
import org.allbinary.util.ABHashtable;

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
        return NoHardware.instance;
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
    public ABHashtable difference(HardwareInterface hardwareInterface)
    {
        return StdUtil.getInstance().NULL_TABLE;
    }    
}
