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
package abcs.logic.system.os;

import allbinary.data.resource.ResourceUtil;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class AndroidSystemProperties
{
    //private static AndroidSystemProperties SINGLETON = null;
	private static final AndroidSystemProperties SINGLETON = 
		new AndroidSystemProperties(ResourceUtil.getInstance().getContext());
    
    private TelephonyManager telephonyManager;

    public static AndroidSystemProperties getInstance()
    {
        return SINGLETON;
    }

    public AndroidSystemProperties(Context context)
    {
    	//TWB -  Need to be in manifest
        //<uses-permission android:name="android.permission.READ_PHONE_STATE" ></uses-permission>
        telephonyManager = (TelephonyManager)
            context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    //IMEI
    public String getDeviceId()
    {
        return telephonyManager.getDeviceId();
    }
    
    public String getDeviceSoftwareVersion()
    {
        return telephonyManager.getDeviceSoftwareVersion();
    }

    public String getLine1Number()
    {
        return telephonyManager.getLine1Number();
    }

    public String getNetworkCountryIso()
    {
        return telephonyManager.getNetworkCountryIso();
    }

    public String getNetworkOperator()
    {
        return telephonyManager.getNetworkOperator();
    }

    public String getNetworkOperatorName()
    {
        return telephonyManager.getNetworkOperatorName();
    }

    public String getSimCountryIso()
    {
        return telephonyManager.getSimCountryIso();
    }

    public String getSimOperator()
    {
        return telephonyManager.getSimOperator();
    }

    public String getSimOperatorName()
    {
        return telephonyManager.getSimOperatorName();
    }

    public String getSimSerialNumber()
    {
        return telephonyManager.getSimSerialNumber();
    }

    public String getSubscriberId()
    {
        return telephonyManager.getSubscriberId();
    }

    public String getVoiceMailAlphaTag()
    {
        return telephonyManager.getVoiceMailAlphaTag();
    }

    public String getVoiceMailNumber()
    {
        return telephonyManager.getVoiceMailNumber();
    }

    public int getNetworkType()
    {
        return telephonyManager.getNetworkType();
    }

    public int getPhoneType()
    {
        return telephonyManager.getPhoneType();
    }
    
    //Build Properties
    public String getBoard()
    {
    	return Build.BOARD;
    }
    
    public String getBrand()
    {
    	return Build.BRAND;
    }
    
    /*
    public String getCpuAbi()
    {
    	return Build.CPU_ABI;
    }
    */
    
    public String getDevice()
    {
    	return Build.DEVICE;
    }
    
    /*
    public String getDisplay()
    {
    	return Build.DISPLAY;
    }
    */
    
    public String getFingerprint()
    {
    	return Build.FINGERPRINT;
    }

    public String getHost()
    {
    	return Build.HOST;
    }

    public String getId()
    {
    	return Build.ID;
    }

    public String getModel()
    {
    	return Build.MODEL;
    }

    public String getProduct()
    {
    	return Build.PRODUCT;
    }

    public String getTags()
    {
    	return Build.TAGS;
    }

    public long getTime()
    {
    	return Build.TIME;
    }
    
    public String getType()
    {
    	return Build.TYPE;
    }

    public String getUser()
    {
    	return Build.USER;
    }
}
