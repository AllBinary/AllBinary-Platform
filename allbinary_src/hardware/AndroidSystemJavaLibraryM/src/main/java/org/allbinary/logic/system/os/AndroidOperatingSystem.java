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
package org.allbinary.logic.system.os;

import org.allbinary.android.AndroidInfoFactory;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class AndroidOperatingSystem extends GenericOperatingSystem
{
   private StringBuilder stringBuffer = new StringBuilder();
   
   private final String DEVICE_ID ="DeviceId";
   private final String DEVICE_SOFTWARE_VERSION ="DeviceSoftwareVersion";
   private final String LINE_1_NUMBER = "Line1Number";
   
   public AndroidOperatingSystem() throws Exception
   {
        int SDK_VERSION = AndroidInfoFactory.getInstance().getVersion();
        
        if(SDK_VERSION > 10)
        {
            this.scalable = true;
        }
               
       AndroidSystemProperties properties = 
               AndroidSystemProperties.getInstance();

       CommonSeps commonStrings = 
           CommonSeps.getInstance();
       
       stringBuffer.append(DEVICE_ID);
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getDeviceId());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append(DEVICE_SOFTWARE_VERSION);
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getDeviceSoftwareVersion());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append(LINE_1_NUMBER);
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getLine1Number());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append("NetworkCountryIso");
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getNetworkCountryIso());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append("NetworkOperator");
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getNetworkOperator());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append("NetworkOperatorName");
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getNetworkOperatorName());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append("NetworkType");
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getNetworkType());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append("PhoneType");
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getPhoneType());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append("SimCountryIso");
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getSimCountryIso());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append("SimOperator");
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getSimOperator());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append("SimOperatorName");
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getSimOperatorName());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append("SimSerialNumber");
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getSimSerialNumber());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append("SubscriberId");
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getSubscriberId());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append("VoiceMailAlphaTag");
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getVoiceMailAlphaTag());
       stringBuffer.append(commonStrings.SPACE);
       stringBuffer.append("VoiceMailNumber");
       stringBuffer.append(commonStrings.EQUALS);
       stringBuffer.append(properties.getVoiceMailNumber());
       stringBuffer.append(commonStrings.SPACE);
       
   }
   
   private final String OUYA = "ouya";
   
   public boolean isOverScan()
   {
       AndroidSystemProperties properties = 
               AndroidSystemProperties.getInstance();
       
       if(properties.getDevice().toLowerCase().indexOf(OUYA) >= 0)
       {
    	   return true;
       }
       
       return false;
   }
   
   public int getOverScanXPercent()
   {
       return 90;
   }

   public int getOverScanYPercent()
   {
       return 90;
   }
   
   public String toString()
   {
      StringMaker osBuffer = new StringMaker();
      
      osBuffer.append(super.toString());
      osBuffer.append(CommonSeps.getInstance().SPACE);
      osBuffer.append("Other System Info: \n");
      osBuffer.append(this.stringBuffer.toString());

      return osBuffer.toString();
   }   
}
