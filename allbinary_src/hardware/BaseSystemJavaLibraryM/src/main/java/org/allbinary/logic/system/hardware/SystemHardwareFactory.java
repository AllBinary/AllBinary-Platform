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
package org.allbinary.logic.system.hardware;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.os.GenericOperatingSystem;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author user
 */
public class SystemHardwareFactory {
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final SystemHardwareFactory instance = new SystemHardwareFactory();

    public static SystemHardwareFactory getInstance() {
        return SystemHardwareFactory.instance;
    }
    
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private HardwareInterface hardwareInterface = NoHardware.getInstance();
    
    /** Creates a new instance of SystemHardware */
    private SystemHardwareFactory(){
   
    }
    
    public HardwareInterface getInstance(GenericOperatingSystem operatingSystemInterface)
    {
      try
      {
    	  if(this.hardwareInterface == NoHardware.getInstance())
    	  {
    	      this.hardwareInterface = HardwareFactory.getInstance().getInstance(operatingSystemInterface);
    	  }

         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().OS))
         //{
            //this.logUtil.putF(static_toString(), this, this.commonStrings.CONSTRUCTOR);
    	    this.logUtil.putF("Found Hardware", this, this.commonStrings.CONSTRUCTOR);
         //}
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().OS))
         //{
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.CONSTRUCTOR, e);
         //}
      }
      return this.hardwareInterface;
   }
   
   /*
   public HardwareInterface getHardware()
   {
      return this.hardwareInterface;
   }
   */

   public static String static_toString()
   {
       final SystemHardwareFactory systemHardwareFactory = SystemHardwareFactory.getInstance();
      final StringMaker osBuffer = new StringMaker();
      osBuffer.append("Hardware Info: \n");
      
      if(systemHardwareFactory.hardwareInterface != null)
      {
          osBuffer.append(systemHardwareFactory.hardwareInterface.toString());
      }
      else
      {
         osBuffer.append(StringUtil.getInstance().NULL_STRING);
      }

      return osBuffer.toString();
   }
    
}
