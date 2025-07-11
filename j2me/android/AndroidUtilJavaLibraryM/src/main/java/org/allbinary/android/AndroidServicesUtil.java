/*
 * AllBinary Open License Version 1
 * Copyright (c) 2019 AllBinary
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
package org.allbinary.android;

import java.util.List;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class AndroidServicesUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();


    /**
     * @return the instance
     */
    public static AndroidServicesUtil getInstance() {
        return instance;
    }
 
    private static final AndroidServicesUtil instance = new AndroidServicesUtil();
    
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final ResourceUtil resourceUtil = ResourceUtil.getInstance();
    
    private final int SERVICE_LIMIT_MAX = 1000;
    
    private final String IS_SERVICE_RUNNING = "isServiceRunning";
    //private final String SERVICE_FOUND_RUNNING = "Service found Running: ";
    private final String SERVICE_NOT_FOUND_RUNNING = "Service not found Running: ";
    
    public boolean isServiceRunning(String name) {
        final ActivityManager activityManager = (ActivityManager) resourceUtil.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningServiceInfo> runningServicesList = activityManager.getRunningServices(SERVICE_LIMIT_MAX);

        ActivityManager.RunningServiceInfo runningServiceInfo;
        ComponentName serviceComponent;
        String serviceName;
        final int size = runningServicesList.size();

        for (int index = 0; index < size; index++) {
            runningServiceInfo = runningServicesList.get(index);
            serviceComponent = runningServiceInfo.service;
            serviceName = serviceComponent.toString();
            //logUtil.put(serviceName, this, IS_SERVICE_RUNNING);
            if(serviceName.indexOf(name) >= 0) {
                //logUtil.put(SERVICE_FOUND_RUNNING + serviceName, this, IS_SERVICE_RUNNING);
                return true;
            }
        }

        logUtil.put(SERVICE_NOT_FOUND_RUNNING + name, this, IS_SERVICE_RUNNING);
        return false;
    }
    
}
