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

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.system.hardware.components.Components;
import org.allbinary.logic.system.hardware.components.interfaces.HardwareComponentInterface;

import org.allbinary.logic.system.hardware.components.linux.Bridge;
import org.allbinary.logic.system.hardware.components.linux.Ethernet;
import org.allbinary.logic.system.hardware.components.linux.HardDriveController;
import org.allbinary.logic.system.hardware.components.linux.Media;
import org.allbinary.logic.system.hardware.components.linux.PCI;
import org.allbinary.logic.system.hardware.components.linux.Unknown;
import org.allbinary.logic.system.hardware.components.linux.Usb;
import org.allbinary.logic.system.hardware.components.linux.Video;

public class ComponentFactory
{
   
   /** Creates a new instance of ComponentFactory */
   private ComponentFactory()
   {
   }
   
   public static HardwareComponentInterface getInstance(String component, String componentData)
   {
      try
      {
         if(component!=null)
         {            
            if(component.compareTo(Components.ETHERNET.toString())==0)
            {
               return (HardwareComponentInterface) new Ethernet(componentData);
            }
            else if(component.compareTo(Components.BRIDGE.toString())==0)
            {
               return (HardwareComponentInterface) new Bridge(componentData);
            }
            else if(component.compareTo(Components.IDE.toString())==0)
            {
               return (HardwareComponentInterface) new HardDriveController(componentData);
            }
            else if(component.compareTo(Components.MULTIMEDIA.toString())==0)
            {
               return (HardwareComponentInterface) new Media(componentData);
            }
            else if(component.compareTo(Components.SCSI.toString())==0)
            {
               return (HardwareComponentInterface) new HardDriveController(componentData);
            }
            else if(component.compareTo(Components.USB.toString())==0)
            {
               return (HardwareComponentInterface) new Usb(componentData);
            }
            else if(component.compareTo(Components.VGA.toString())==0)
            {
               return (HardwareComponentInterface) new Video(componentData);
            }
            else if(component.compareTo(Components.PCI.toString())==0)
            {
               return (HardwareComponentInterface) new PCI(componentData);
            }
         }
         return (HardwareComponentInterface) new Unknown(componentData);
      }
      catch(Exception e)
      {
         String error = "Failed to getInstance for: " + component;
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
         {
            LogUtil.put(error,"ComponentFactory","getInstance()",e);
         }
         return null;
      }
      
   }
   
   public static String getComponentType(String component)
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
      else if(component.compareTo(Components.PCI.toString())==0)
      {
         return Components.PCI.toString();
      }
      return Components.UNKNOWN.toString();
   }
}
