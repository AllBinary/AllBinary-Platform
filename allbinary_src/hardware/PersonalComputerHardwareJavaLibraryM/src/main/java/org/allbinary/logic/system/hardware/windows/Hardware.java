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

import java.util.Hashtable;
import java.util.Vector;
import org.allbinary.logic.NullUtil;

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

public class Hardware implements HardwareInterface
{
   private Vector componentInterfaceVector;
   private Vector videoInterfaceVector;
   private Vector hardDriveControllerInterfaceVector;
   private Vector cpuInterfaceVector;
   private Vector usbInterfaceVector;
   private Vector ethernetInterfaceVector;
   private Vector multimediaInterfaceVector;
   private Vector fireWireInterfaceVector;
   private Vector bridgeInterfaceVector;
   private Vector hardDriveInterfaceVector;
   private Vector macInterfaceVector;
   private Vector monitorInterfaceVector;
   
   private final String NAME = "Windows Hardware Profile";
   
   private final int MINHARDWARE = 0;
   
   private final ComponentFactory componentFactory = ComponentFactory.getInstance();
   
   public Hardware() throws Exception
   {
      try
      {
         componentInterfaceVector = new Vector();
         videoInterfaceVector = new Vector();
         hardDriveControllerInterfaceVector = new Vector();
         cpuInterfaceVector = new Vector();
         usbInterfaceVector = new Vector();
         ethernetInterfaceVector = new Vector();
         multimediaInterfaceVector = new Vector();
         fireWireInterfaceVector = new Vector();
         bridgeInterfaceVector = new Vector();
         hardDriveInterfaceVector = new Vector();
         macInterfaceVector = new Vector();
         monitorInterfaceVector = new Vector();
         
         if(componentInterfaceVector.size()<MINHARDWARE) 
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
      return (MediaInterface) multimediaInterfaceVector.get(index);
   }
   
   public BridgeInterface getBridge(int index)
   {
      return (BridgeInterface) bridgeInterfaceVector.get(index);
   }
   
   public CpuInterface getCpu(int index)
   {
      return (CpuInterface) cpuInterfaceVector.get(index);
   }
   
   public EthernetInterface getEthernet(int index)
   {
      return (EthernetInterface) ethernetInterfaceVector.get(index);
   }
   
   public FireWireInterface getFireWire(int index)
   {
      return (FireWireInterface) fireWireInterfaceVector.get(index);
   }
   
   public HardDriveControllerInterface getHardDriveController(int index)
   {
      return (HardDriveControllerInterface) hardDriveControllerInterfaceVector.get(index);
   }
   
   public HardDriveInterface getHardDrive(int index)
   {
      return (HardDriveInterface) hardDriveInterfaceVector.get(index);
   }
   
   public MachineAccessControlAddressInterface getMachineAccessControlAddress(int index)
   {
      return (MachineAccessControlAddressInterface) macInterfaceVector.get(index);
   }
   
   public MonitorInterface getMonitor(int index)
   {
      return (MonitorInterface) monitorInterfaceVector.get(index);
   }
   
   public UsbInterface getUsb(int index)
   {
      return (UsbInterface) usbInterfaceVector.get(index);
   }
   
   public VideoInterface getVideo(int index)
   {
      return (VideoInterface) videoInterfaceVector.get(index);
   }
   
   public HardwareComponentInterface getComponent(int index)
   {
      return (HardwareComponentInterface) componentInterfaceVector.get(index);
   }
   
   public String toString()
   {
      final StringMaker hardwareBuffer = new StringMaker();
      
      final int size = componentInterfaceVector.size();
      for (int index = 0; index < size; index++)
      {
         HardwareComponentInterface componentInterface = (HardwareComponentInterface) componentInterfaceVector.get(index);
         hardwareBuffer.append(componentInterface.toString());
         hardwareBuffer.append(CommonSeps.getInstance().NEW_LINE);
      }
      return hardwareBuffer.toString();
   }
   
   public boolean compareTo(HardwareInterface hardwareInterface)
   {
      return true;
   }
   
   public Hashtable difference(HardwareInterface hardwareInterface)
   {
      return NullUtil.getInstance().NULL_TABLE;
   }
}
