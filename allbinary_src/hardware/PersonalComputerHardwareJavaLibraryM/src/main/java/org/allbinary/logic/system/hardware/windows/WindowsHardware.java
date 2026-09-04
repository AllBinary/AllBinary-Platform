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
package org.allbinary.logic.system.hardware.windows;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import org.allbinary.logic.StdUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.hardware.HardwareInterface;
import org.allbinary.logic.system.hardware.components.ComponentFactory;
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
import org.allbinary.string.CommonSeps;
import org.allbinary.util.ABHashtable;

public class WindowsHardware implements HardwareInterface
{
   private BasicArrayList componentInterfaceVector;
   private BasicArrayList videoInterfaceVector;
   private BasicArrayList hardDriveControllerInterfaceVector;
   private BasicArrayList cpuInterfaceVector;
   private BasicArrayList usbInterfaceVector;
   private BasicArrayList ethernetInterfaceVector;
   private BasicArrayList multimediaInterfaceVector;
   private BasicArrayList fireWireInterfaceVector;
   private BasicArrayList bridgeInterfaceVector;
   private BasicArrayList hardDriveInterfaceVector;
   private BasicArrayList macInterfaceVector;
   private BasicArrayList monitorInterfaceVector;
   
   private final String NAME = "Windows Hardware Profile";
   
   private final int MINHARDWARE = 0;
   
   private final ComponentFactory componentFactory = ComponentFactory.getInstance();
   
   public WindowsHardware() throws Exception
   {
      try
      {
         this.componentInterfaceVector = new BasicArrayListD();
         this.videoInterfaceVector = new BasicArrayListD();
         this.hardDriveControllerInterfaceVector = new BasicArrayListD();
         this.cpuInterfaceVector = new BasicArrayListD();
         this.usbInterfaceVector = new BasicArrayListD();
         this.ethernetInterfaceVector = new BasicArrayListD();
         this.multimediaInterfaceVector = new BasicArrayListD();
         this.fireWireInterfaceVector = new BasicArrayListD();
         this.bridgeInterfaceVector = new BasicArrayListD();
         this.hardDriveInterfaceVector = new BasicArrayListD();
         this.macInterfaceVector = new BasicArrayListD();
         this.monitorInterfaceVector = new BasicArrayListD();
         
         if(this.componentInterfaceVector.size()<this.MINHARDWARE) 
               throw new Exception("Not Enough Data For A Valid License On Windows");
      }
      catch(Exception e)
      {
         throw e;
      }
   }
   
   private String getComponentType(String component)
   {
      if(component.indexOf(this.componentFactory.ETHERNET.toString())>=0)
      {
         return this.componentFactory.ETHERNET.toString();
      }
      else if(component.indexOf(this.componentFactory.BRIDGE.toString())>=0)
      {
         return this.componentFactory.BRIDGE.toString();
      }
      else if(component.indexOf(this.componentFactory.IDE.toString())>=0)
      {
         return this.componentFactory.IDE.toString();
      }
      else if(component.indexOf(this.componentFactory.MULTIMEDIA.toString())>=0)
      {
         return this.componentFactory.MULTIMEDIA.toString();
      }
      else if(component.indexOf(this.componentFactory.SCSI.toString())>=0)
      {
         return this.componentFactory.SCSI.toString();
      }
      else if(component.indexOf(this.componentFactory.USB.toString())>=0)
      {
         return this.componentFactory.USB.toString();
      }
      else if(component.indexOf(this.componentFactory.VGA.toString())>=0)
      {
         return this.componentFactory.VGA.toString();
      }
      else return null;
   }
   
   public MediaInterface getMultimedia(int index)
   {
      return (MediaInterface) this.multimediaInterfaceVector.get(index);
   }
   
   public BridgeInterface getBridge(int index)
   {
      return (BridgeInterface) this.bridgeInterfaceVector.get(index);
   }
   
   public CpuInterface getCpu(int index)
   {
      return (CpuInterface) this.cpuInterfaceVector.get(index);
   }
   
   public EthernetInterface getEthernet(int index)
   {
      return (EthernetInterface) this.ethernetInterfaceVector.get(index);
   }
   
   public FireWireInterface getFireWire(int index)
   {
      return (FireWireInterface) this.fireWireInterfaceVector.get(index);
   }
   
   public HardDriveControllerInterface getHardDriveController(int index)
   {
      return (HardDriveControllerInterface) this.hardDriveControllerInterfaceVector.get(index);
   }
   
   public HardDriveInterface getHardDrive(int index)
   {
      return (HardDriveInterface) this.hardDriveInterfaceVector.get(index);
   }
   
   public MachineAccessControlAddressInterface getMachineAccessControlAddress(int index)
   {
      return (MachineAccessControlAddressInterface) this.macInterfaceVector.get(index);
   }
   
   public MonitorInterface getMonitor(int index)
   {
      return (MonitorInterface) this.monitorInterfaceVector.get(index);
   }
   
   public UsbInterface getUsb(int index)
   {
      return (UsbInterface) this.usbInterfaceVector.get(index);
   }
   
   public VideoInterface getVideo(int index)
   {
      return (VideoInterface) this.videoInterfaceVector.get(index);
   }
   
   public HardwareComponentInterface getComponent(int index)
   {
      return (HardwareComponentInterface) this.componentInterfaceVector.get(index);
   }
   
   public String toString()
   {
      final StringMaker hardwareBuffer = new StringMaker();
      
      final int size = this.componentInterfaceVector.size();
      for (int index = 0; index < size; index++)
      {
         HardwareComponentInterface componentInterface = (HardwareComponentInterface) this.componentInterfaceVector.get(index);
         hardwareBuffer.append(componentInterface.toString());
         hardwareBuffer.append(CommonSeps.getInstance().NEW_LINE);
      }
      return hardwareBuffer.toString();
   }
   
   public boolean compareTo(HardwareInterface hardwareInterface)
   {
      return true;
   }
   
   public ABHashtable difference(HardwareInterface hardwareInterface)
   {
      return StdUtil.getInstance().NULL_TABLE;
   }
}
