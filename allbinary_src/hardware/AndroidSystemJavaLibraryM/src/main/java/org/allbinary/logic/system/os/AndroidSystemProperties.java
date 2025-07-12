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

import android.content.Context;
import android.os.Build;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

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
    
    //private TelephonyManager telephonyManager;

    public static AndroidSystemProperties getInstance()
    {
        return SINGLETON;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    //private final String androidId;
    
    public AndroidSystemProperties(Context context)
    {
    	//TWB -  Need to be in manifest
        //<uses-permission android:name="android.permission.READ_PHONE_STATE" ></uses-permission>
        //telephonyManager = (TelephonyManager)
            //context.getSystemService(Context.TELEPHONY_SERVICE);
        //this.androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    //IMEI
    public String getDeviceId()
    {
        return StringUtil.getInstance().EMPTY_STRING;
        //return this.androidId;
        //return telephonyManager.getDeviceId();
    }
    
    public String getDeviceSoftwareVersion()
    {
        return commonStrings.DISABLE;
        //return telephonyManager.getDeviceSoftwareVersion();
    }

    public String getLine1Number()
    {
        return commonStrings.DISABLE;
        //return telephonyManager.getLine1Number();
    }

    public String getNetworkCountryIso()
    {
        return commonStrings.DISABLE;
        //return telephonyManager.getNetworkCountryIso();
    }

    public String getNetworkOperator()
    {
        return commonStrings.DISABLE;
        //return telephonyManager.getNetworkOperator();
    }

    public String getNetworkOperatorName()
    {
        return commonStrings.DISABLE;
        //return telephonyManager.getNetworkOperatorName();
    }

    public String getSimCountryIso()
    {
        return commonStrings.DISABLE;
        //return telephonyManager.getSimCountryIso();
    }

    public String getSimOperator()
    {
        return commonStrings.DISABLE;
        //return telephonyManager.getSimOperator();
    }

    public String getSimOperatorName()
    {
        return commonStrings.DISABLE;
        //return telephonyManager.getSimOperatorName();
    }

    public String getSimSerialNumber()
    {
        return commonStrings.DISABLE;
        //return telephonyManager.getSimSerialNumber();
    }

    public String getSubscriberId()
    {
        return commonStrings.DISABLE;
        //return telephonyManager.getSubscriberId();
    }

    public String getVoiceMailAlphaTag()
    {
        return commonStrings.DISABLE;
        //return telephonyManager.getVoiceMailAlphaTag();
    }

    public String getVoiceMailNumber()
    {
        return commonStrings.DISABLE;
        //return telephonyManager.getVoiceMailNumber();
    }

    public int getNetworkType()
    {
        return -1;
        //return telephonyManager.getNetworkType();
    }

    public int getPhoneType()
    {
        return -1;
        //return telephonyManager.getPhoneType();
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
