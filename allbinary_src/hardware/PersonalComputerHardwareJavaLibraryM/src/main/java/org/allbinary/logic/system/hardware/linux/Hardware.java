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

import java.io.FileReader;
import java.io.LineNumberReader;

import java.util.Vector;
import java.util.Hashtable;
import java.util.Iterator;
import org.allbinary.logic.basic.io.file.AbFile;

import org.allbinary.logic.basic.io.file.FilePathData;
import org.allbinary.logic.basic.io.file.directory.SubDirectory;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.logic.system.hardware.components.linux.Cpu;

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

import org.allbinary.logic.system.hardware.HardwareInterface;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;

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
   
   private final static String PCIFILE = "/proc/pci";
   private final String NAME = "Linux Hardware Profile";
   
   private final int MINHARDWARE = 5;
   
   //Testing
   public Hardware(String path) throws Exception
   {
      this.init(path);
   }
   
   public Hardware() throws Exception
   {
      this.init(PCIFILE);
      
      if(componentInterfaceVector.size() < MINHARDWARE)
      {
          throw new Exception("Not Enough Data For A Valid License On Linux"); 
      }
      
      Cpu cpu = new Cpu();
      if(cpu!=null)
      {
         cpuInterfaceVector.add(cpu);
         componentInterfaceVector.add(cpu);
      }
      
      if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().OS))
      {
         LogUtil.put(LogFactory.getInstance("Hardware Data: " + this.toString(), this, "Constructor()"));
      }
   }
   
   private void init(String filePath) throws Exception
   {
      LineNumberReader lineNumberReader = null;
      try
      {
         init(lineNumberReader, filePath);
      }
      catch(Exception e)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().OS))
         {
            LogUtil.put(LogFactory.getInstance("Hardware Data: " + this.toString(), this, "Constructor()",e));
         }
         throw e;
      }
   }
   
   private void init(LineNumberReader lineNumberReader, String filePath) throws Exception
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
         
         FileReader pciFile = new FileReader(filePath);
         lineNumberReader = new LineNumberReader(pciFile);
         
         if(lineNumberReader == null)
         {
            //Find file
            Vector fileVector = new SubDirectory().search(filePath, new AbFile(FilePathData.SEPARATOR));
            
            //if(fileVector.size() > 0)
            if(!fileVector.isEmpty())
            {
               AbFile file = (AbFile) fileVector.get(0);
               lineNumberReader = new LineNumberReader(new FileReader(file.getPath()));
            }
            
            if(lineNumberReader == null)
            {
               throw new Exception("No Linux File Data");
            }
         }
         
         if(lineNumberReader != null)
         {
            if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().OS))
            {
               LogUtil.put(LogFactory.getInstance("PCI File Found", this, "Constructor()"));
            }
            
            String nextLine = lineNumberReader.readLine();
            
            //Get to the first line with Bus info
            while(lineNumberReader != null && nextLine != null)
            {
               //System.out.println(nextLine);
               if(this.isNextHardware(nextLine))
               {
                  break;
               }
               nextLine = lineNumberReader.readLine();
            }
            
            while(this.isNextHardware(nextLine))
            {
               StringBuffer componentData = new StringBuffer();

               if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().OS))
               {
                  LogUtil.put(LogFactory.getInstance("Found Hardware Device: " + componentInterfaceVector.size(), this, "Constructor()"));
               }
               
               componentData.append(nextLine);
               componentData.append("\n");
               //System.out.println(nextLine);
               nextLine = lineNumberReader.readLine();
               String componentType = PCComponentFactory.getInstance().getComponentType(nextLine);
               
               while(lineNumberReader != null)
               {
                  componentData.append(nextLine);
                  componentData.append("\n");
                  nextLine = lineNumberReader.readLine();
                  if(nextLine == null || this.isNextHardware(nextLine))
                  {
                     break;
                  }
               }
               //System.out.println(componentType);
               HardwareComponentInterface componentInterface = 
                       PCComponentFactory.getInstance().getInstance(
                          componentType,componentData.toString());
               if(componentInterface != null)
               {
                  componentInterfaceVector.add(componentInterface);
               }
            }
         }
         else
         {
            if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().OS))
            {
               LogUtil.put(LogFactory.getInstance("Could not load PCI File", this, "Constructor()"));
            }
         }
         
         lineNumberReader.close();
      }
      finally
      {
         if(lineNumberReader != null)
            lineNumberReader.close();
      }
      
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
      int index = 0;
      while(componentIter.hasNext())
      {
         HardwareComponentInterface componentInterface = (HardwareComponentInterface) componentIter.next();
         hardwareBuffer.append("Component ");
         hardwareBuffer.append(index);
         hardwareBuffer.append(": \n");
         hardwareBuffer.append(componentInterface.toString());
         hardwareBuffer.append("\n");
         index++;
      }
      return hardwareBuffer.toString();
   }
   
   public boolean compareTo(HardwareInterface hardwareInterface)
   {
      return true;
   }
   
   public Hashtable difference(HardwareInterface hardwareInterface)
   {
      return new Hashtable();
   }
   
   public boolean isNextHardware(String nextLine)
   {
      if(nextLine != null)
      {
         int index = nextLine.indexOf("Bus");
         if(index >= 0 && index < 4)
         {
            return true;
         }
      }
      return false;
   }
   
}
