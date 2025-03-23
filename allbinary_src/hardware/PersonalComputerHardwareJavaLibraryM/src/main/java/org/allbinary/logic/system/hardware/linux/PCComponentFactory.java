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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.system.hardware.components.ComponentFactory;
import org.allbinary.logic.system.hardware.components.interfaces.HardwareComponentInterface;

import org.allbinary.logic.system.hardware.components.linux.Bridge;
import org.allbinary.logic.system.hardware.components.linux.Ethernet;
import org.allbinary.logic.system.hardware.components.linux.HardDriveController;
import org.allbinary.logic.system.hardware.components.linux.Media;
import org.allbinary.logic.system.hardware.components.linux.PCI;
import org.allbinary.logic.system.hardware.components.linux.Unknown;
import org.allbinary.logic.system.hardware.components.linux.Usb;
import org.allbinary.logic.system.hardware.components.linux.Video;

public class PCComponentFactory
{
   private static final PCComponentFactory instance = new PCComponentFactory();

    /**
     * @return the instance
     */
    public static PCComponentFactory getInstance()
    {
        return instance;
    }
    
    private final ComponentFactory componentFactory = ComponentFactory.getInstance();
    
   /** Creates a new instance of ComponentFactory */
   private PCComponentFactory()
   {
   }
   
   public HardwareComponentInterface getInstance(String component, String componentData)
   {
      try
      {
         if(component!=null)
         {            
            if(component.compareTo(this.componentFactory.ETHERNET.toString())==0)
            {
               return (HardwareComponentInterface) new Ethernet(componentData);
            }
            else if(component.compareTo(this.componentFactory.BRIDGE.toString())==0)
            {
               return (HardwareComponentInterface) new Bridge(componentData);
            }
            else if(component.compareTo(this.componentFactory.IDE.toString())==0)
            {
               return (HardwareComponentInterface) new HardDriveController(componentData);
            }
            else if(component.compareTo(this.componentFactory.MULTIMEDIA.toString())==0)
            {
               return (HardwareComponentInterface) new Media(componentData);
            }
            else if(component.compareTo(this.componentFactory.SCSI.toString())==0)
            {
               return (HardwareComponentInterface) new HardDriveController(componentData);
            }
            else if(component.compareTo(this.componentFactory.USB.toString())==0)
            {
               return (HardwareComponentInterface) new Usb(componentData);
            }
            else if(component.compareTo(this.componentFactory.VGA.toString())==0)
            {
               return (HardwareComponentInterface) new Video(componentData);
            }
            else if(component.compareTo(this.componentFactory.PCI.toString())==0)
            {
               return (HardwareComponentInterface) new PCI(componentData);
            }
         }
         return (HardwareComponentInterface) new Unknown(componentData);
      }
      catch(Exception e)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().FACTORYERROR))
         {
             String error = "Failed to getInstance for: " + component;
            LogUtil.put(LogFactory.getInstance(error, this,"getInstance()",e));
         }
         return new Unknown(CommonStrings.getInstance().EXCEPTION);
      }
      
   }
   
   public String getComponentType(String component)
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
      else if(component.compareTo(this.componentFactory.PCI.toString())==0)
      {
         return this.componentFactory.PCI.toString();
      }
      return this.componentFactory.UNKNOWN.toString();
   }
}
