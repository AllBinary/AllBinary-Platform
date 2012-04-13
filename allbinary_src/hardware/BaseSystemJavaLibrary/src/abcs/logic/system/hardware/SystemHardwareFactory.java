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
package abcs.logic.system.hardware;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringMaker;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.os.OperatingSystemInterface;

/**
 *
 * @author user
 */
public class SystemHardwareFactory {
    
	private static final SystemHardwareFactory instance = new SystemHardwareFactory();
	
    private static HardwareInterface hardwareInterface;
    
    /** Creates a new instance of SystemHardware */
    private SystemHardwareFactory(){
   
    }
    
    public HardwareInterface getInstance(OperatingSystemInterface operatingSystemInterface)
    {
      try
      {
    	  if(hardwareInterface == null)
    	  {
    	      hardwareInterface = HardwareFactory.getInstance().getInstance(operatingSystemInterface);
    	  }

         //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.OS))
         //{
            //LogUtil.put(LogFactory.getInstance(static_toString(), instance, CommonStrings.getInstance().CONSTRUCTOR));
    	    LogUtil.put(LogFactory.getInstance("Found Hardware", this, CommonStrings.getInstance().CONSTRUCTOR));
         //}
      }
      catch(Exception e)
      {
         //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.OS))
         //{
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().CONSTRUCTOR, e));
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

public static SystemHardwareFactory getInstance()
{
    return instance;
}
    
}
