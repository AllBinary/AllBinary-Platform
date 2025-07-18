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
        return instance;
    }
	
    private static HardwareInterface hardwareInterface = NoHardware.getInstance();
    
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    /** Creates a new instance of SystemHardware */
    private SystemHardwareFactory(){
   
    }
    
    public HardwareInterface getInstance(GenericOperatingSystem operatingSystemInterface)
    {
      try
      {
    	  if(hardwareInterface == NoHardware.getInstance())
    	  {
    	      hardwareInterface = HardwareFactory.getInstance().getInstance(operatingSystemInterface);
    	  }

         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().OS))
         //{
            //logUtil.put(static_toString(), this, commonStrings.CONSTRUCTOR);
    	    logUtil.put("Found Hardware", this, commonStrings.CONSTRUCTOR);
         //}
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().OS))
         //{
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
         //}
      }
      return hardwareInterface;
   }
   
   /*
   public HardwareInterface getHardware()
   {
      return hardwareInterface;
   }
   */

   public static String static_toString()
   {
      StringMaker osBuffer = new StringMaker();
      osBuffer.append("Hardware Info: \n");
      
      if(hardwareInterface != null)
      {
          osBuffer.append(hardwareInterface.toString());
      }
      else
      {
         osBuffer.append(StringUtil.getInstance().NULL_STRING);
      }

      return osBuffer.toString();
   }
    
}
