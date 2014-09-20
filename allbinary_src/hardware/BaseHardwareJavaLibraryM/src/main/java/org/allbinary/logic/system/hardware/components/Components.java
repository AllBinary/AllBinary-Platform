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
package org.allbinary.logic.system.hardware.components;

import org.allbinary.logic.basic.Unknown;

public class Components
{
    private String componentName;
    
    public Components(String componentName)
    {
        this.componentName = componentName;
    }
    
    public final Components VGA = new Components("VGA");
    public final Components IDE = new Components("IDE");
    public final Components SCSI = new Components("SCSI");    
    public final Components BRIDGE = new Components("bridge");
    public final Components ETHERNET = new Components("Ethernet");
    public final Components USB = new Components("USB");
    public final Components MULTIMEDIA = new Components("Multimedia");
    public final Components PCI = new Components("PCI");
    
    //new not impelemented
    public final Components RAID = new Components("RAID");
    public final Components SMBUS = new Components("SMBus");
    public final Components PIC = new Components("PIC");
    public final Components Class = new Components("Class");
    
    public final Components UNKNOWN = new Components(Unknown.NAME);
    
    public String toString()
    {
        return componentName;
    }
}
