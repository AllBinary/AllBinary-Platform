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
package org.allbinary.logic.system.hardware.linux;

import java.io.LineNumberReader;
import java.util.Hashtable;

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.hardware.HardwareInterface;
import org.allbinary.logic.system.hardware.components.interfaces.BridgeInterface;
import org.allbinary.logic.system.hardware.components.interfaces.CpuInterface;
import org.allbinary.logic.system.hardware.components.interfaces.EthernetInterface;
import org.allbinary.logic.system.hardware.components.interfaces.FireWireInterface;
import org.allbinary.logic.system.hardware.components.interfaces.HardDriveControllerInterface;
import org.allbinary.logic.system.hardware.components.interfaces.HardDriveInterface;
import org.allbinary.logic.system.hardware.components.interfaces.HardwareComponentInterface;
import org.allbinary.logic.system.hardware.components.interfaces.MachineAccessControlAddressInterface;
import org.allbinary.logic.system.hardware.components.interfaces.MediaInterface;
import org.allbinary.logic.system.hardware.components.interfaces.MonitorInterface;
import org.allbinary.logic.system.hardware.components.interfaces.UsbInterface;
import org.allbinary.logic.system.hardware.components.interfaces.VideoInterface;

public class LinuxHardware implements HardwareInterface
{
   
   public LinuxHardware() throws Exception
   {
   }
         
   public MediaInterface getMultimedia(int index)
   {
      throw new RuntimeException();
   }
   
   public BridgeInterface getBridge(int index)
   {
      throw new RuntimeException();
   }
   
   public CpuInterface getCpu(int index)
   {
      throw new RuntimeException();
   }
   
   public EthernetInterface getEthernet(int index)
   {
      throw new RuntimeException();
   }
   
   public FireWireInterface getFireWire(int index)
   {
      throw new RuntimeException();
   }
   
   public HardDriveControllerInterface getHardDriveController(int index)
   {
      throw new RuntimeException();
   }
   
   public HardDriveInterface getHardDrive(int index)
   {
      throw new RuntimeException();
   }
   
   public MachineAccessControlAddressInterface getMachineAccessControlAddress(int index)
   {
      throw new RuntimeException();
   }
   
   public MonitorInterface getMonitor(int index)
   {
      throw new RuntimeException();
   }
   
   public UsbInterface getUsb(int index)
   {
      throw new RuntimeException();
   }
   
   public VideoInterface getVideo(int index)
   {
      throw new RuntimeException();
   }
   
   @Override
   public HardwareComponentInterface getComponent(int index)
   {
      throw new RuntimeException();
   }
   
   public String toString()
   {
      return StringUtil.getInstance().EMPTY_STRING;
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
   
   public boolean isNextHardware(String nextLine)
   {
      return false;
   }
   
}
