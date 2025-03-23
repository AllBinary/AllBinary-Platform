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

import org.allbinary.string.CommonStrings;

/**
 *
 * @author user
 */
public class ComponentFactory
{
    private static final ComponentFactory instance = new ComponentFactory();

    /**
     * @return the instance
     */
    public static ComponentFactory getInstance()
    {
        return instance;
    }
    
    public final Component VGA = new Component("VGA");
    public final Component IDE = new Component("IDE");
    public final Component SCSI = new Component("SCSI");    
    public final Component BRIDGE = new Component("bridge");
    public final Component ETHERNET = new Component("Ethernet");
    public final Component USB = new Component("USB");
    public final Component MULTIMEDIA = new Component("Multimedia");
    public final Component PCI = new Component("PCI");
    
    //new not impelemented
    public final Component RAID = new Component("RAID");
    public final Component SMBUS = new Component("SMBus");
    public final Component PIC = new Component("PIC");
    public final Component Class = new Component("Class");
    
    public final Component UNKNOWN = new Component(CommonStrings.getInstance().UNKNOWN);
    
}
