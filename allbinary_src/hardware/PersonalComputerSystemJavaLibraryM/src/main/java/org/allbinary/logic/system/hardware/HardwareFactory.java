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

import org.allbinary.logic.system.os.GenericOperatingSystem;
import org.allbinary.logic.system.os.OperatingSystems;

public class HardwareFactory
{

    private HardwareFactory()
    {
    }

    public static HardwareInterface getInstance(GenericOperatingSystem os) throws Exception
    {
        try
        {
            final OperatingSystems operatingSystems = OperatingSystems.getInstance();
            if(os.getName().compareTo(operatingSystems.LINUX) == 0)
            {
                return (HardwareInterface) new org.allbinary.logic.system.hardware.linux.Hardware();
            }else if(os.getName().indexOf(operatingSystems.WINDOWS) >= 0)
            {
                return (HardwareInterface) new org.allbinary.logic.system.hardware.windows.Hardware();
            }
            /*
             else
             if(os.getName().compareTo(OperatingSystems.SOLARIS)==0)
             {
          
             }*/
            throw new Exception("No Hardware Imp for: " + os.getName());
        }catch(Exception e)
        {
            throw e;
        }
    }
}
