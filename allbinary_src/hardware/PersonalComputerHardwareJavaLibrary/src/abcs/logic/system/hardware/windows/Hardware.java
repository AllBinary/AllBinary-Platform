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
package abcs.logic.system.hardware.windows;

import java.util.Vector;
import java.util.HashMap;
import java.util.Iterator;

import abcs.logic.system.hardware.components.Components;

import abcs.logic.system.hardware.components.interfaces.BridgeInterface;
import abcs.logic.system.hardware.components.interfaces.CpuInterface;
import abcs.logic.system.hardware.components.interfaces.EthernetInterface;
import abcs.logic.system.hardware.components.interfaces.FireWireInterface;
import abcs.logic.system.hardware.components.interfaces.HardDriveControllerInterface;
import abcs.logic.system.hardware.components.interfaces.HardDriveInterface;
import abcs.logic.system.hardware.components.interfaces.HardwareComponentInterface;
import abcs.logic.system.hardware.components.interfaces.MachineAccessControlAddressInterface;
import abcs.logic.system.hardware.components.interfaces.MediaInterface;
import abcs.logic.system.hardware.components.interfaces.MonitorInterface;
import abcs.logic.system.hardware.components.interfaces.UsbInterface;
import abcs.logic.system.hardware.components.interfaces.VideoInterface;

import abcs.logic.system.hardware.HardwareInterface;

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
      if(component.indexOf(Components.ETHERNET.toString())>=0)
      {
         return Components.ETHERNET.toString();
      }
      else if(component.indexOf(Components.BRIDGE.toString())>=0)
      {
         return Components.BRIDGE.toString();
      }
      else if(component.indexOf(Components.IDE.toString())>=0)
      {
         return Components.IDE.toString();
      }
      else if(component.indexOf(Components.MULTIMEDIA.toString())>=0)
      {
         return Components.MULTIMEDIA.toString();
      }
      else if(component.indexOf(Components.SCSI.toString())>=0)
      {
         return Components.SCSI.toString();
      }
      else if(component.indexOf(Components.USB.toString())>=0)
      {
         return Components.USB.toString();
      }
      else if(component.indexOf(Components.VGA.toString())>=0)
      {
         return Components.VGA.toString();
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
      StringBuffer hardwareBuffer = new StringBuffer();
      Iterator componentIter = componentInterfaceVector.iterator();
      while(componentIter.hasNext())
      {
         HardwareComponentInterface componentInterface = (HardwareComponentInterface) componentIter.next();
         hardwareBuffer.append(componentInterface.toString());
         hardwareBuffer.append(CommonSeps.getInstance().NEW_LINE);
      }
      return hardwareBuffer.toString();
   }
   
   public boolean compareTo(HardwareInterface hardwareInterface)
   {
      return true;
   }
   
   public HashMap difference(HardwareInterface hardwareInterface)
   {
      return new HashMap();
   }
}
