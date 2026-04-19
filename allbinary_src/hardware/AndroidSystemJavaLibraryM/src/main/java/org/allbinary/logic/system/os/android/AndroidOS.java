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
package org.allbinary.logic.system.os.android;

import org.allbinary.android.AndroidInfoFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.os.GenericOperatingSystem;
import org.allbinary.string.CommonSeps;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class AndroidOS extends GenericOperatingSystem
{
   private StringBuilder stringBuffer = new StringBuilder();
   
   private final String DEVICE_ID ="DeviceId";
   private final String DEVICE_SOFTWARE_VERSION ="DeviceSoftwareVersion";
   private final String LINE_1_NUMBER = "Line1Number";
   
   public AndroidOS() throws Exception
   {
        final int SDK_VERSION = AndroidInfoFactory.getInstance().getVersion();
        
        if(SDK_VERSION > 10)
        {
            this.scalable = true;
        }
               
       final AndroidSystemProperties properties = AndroidSystemProperties.getInstance();

       final CommonSeps commonStrings = CommonSeps.getInstance();
       
       this.stringBuffer.append(this.DEVICE_ID);
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getDeviceId());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append(this.DEVICE_SOFTWARE_VERSION);
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getDeviceSoftwareVersion());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append(this.LINE_1_NUMBER);
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getLine1Number());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append("NetworkCountryIso");
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getNetworkCountryIso());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append("NetworkOperator");
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getNetworkOperator());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append("NetworkOperatorName");
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getNetworkOperatorName());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append("NetworkType");
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getNetworkType());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append("PhoneType");
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getPhoneType());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append("SimCountryIso");
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getSimCountryIso());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append("SimOperator");
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getSimOperator());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append("SimOperatorName");
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getSimOperatorName());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append("SimSerialNumber");
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getSimSerialNumber());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append("SubscriberId");
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getSubscriberId());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append("VoiceMailAlphaTag");
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getVoiceMailAlphaTag());
       this.stringBuffer.append(commonStrings.SPACE);
       this.stringBuffer.append("VoiceMailNumber");
       this.stringBuffer.append(commonStrings.EQUALS);
       this.stringBuffer.append(properties.getVoiceMailNumber());
       this.stringBuffer.append(commonStrings.SPACE);
       
   }
   
   private final String OUYA = "ouya";
   
   @Override
   public boolean isOverScan()
   {
       AndroidSystemProperties properties = 
               AndroidSystemProperties.getInstance();
       
       if(properties.getDevice().toLowerCase().indexOf(this.OUYA) >= 0)
       {
    	   return true;
       }
       
       return false;
   }
   
   @Override
   public int getOverScanXPercent()
   {
       return 90;
   }

   @Override
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
