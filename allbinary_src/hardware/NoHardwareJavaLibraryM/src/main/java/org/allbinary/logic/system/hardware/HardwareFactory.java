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

import org.allbinary.logic.system.os.OperatingSystemInterface;

public class HardwareFactory
{
    private static final HardwareFactory instance = new HardwareFactory();

    /**
     * @return the instance
     */
    public static HardwareFactory getInstance()
    {
        return instance;
    }
    
    private final HardwareInterface SINGLETON = new NoHardware();

    private HardwareFactory()
    {
    }
    
    public HardwareInterface getInstance(OperatingSystemInterface os) throws Exception
    {
       try
       {
          //throw new Exception("NoSystemJavaLibrary - No Hardware Imp for: " + os.getName());
           return SINGLETON;
       }
       catch(Exception e)
       {
          throw e;
       }
    }
}
