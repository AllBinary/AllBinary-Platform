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
package org.allbinary.logic.system.hardware.components.interfaces;

public interface CpuInterface
{
    public String getProcessor();

    public String getVendorId();

    public String getCpuFamily();
    
    public String getModel();

    public String getModelName();

    public String getStepping();

    public String getCpuSpeed();
         
    public String getCacheSize();
    
    public boolean compareTo(CpuInterface cpuInterface);
}
