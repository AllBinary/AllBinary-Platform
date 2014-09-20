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
package org.allbinary.data.resource;

import java.io.InputStream;
import java.util.Hashtable;

import org.allbinary.util.HashtableUtil;

import org.allbinary.debug.DebugFactory;
import org.allbinary.debug.NoDebug;
import android.content.Context;
import android.content.res.Resources;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class ResourceUtil
{
    private static ResourceUtil RESOURCES = new ResourceUtil();

    private Context context;
    private Resources resources;
    private Hashtable hashMap = new Hashtable();

    private ResourceUtil()
    {
    }

    public static ResourceUtil getInstance()
    {
        return RESOURCES;
    }

    public Context getContext()
    {
        return context;
    }

    public void setContext(Context aContext)
    {
        this.context = aContext;
    }
    
    public Integer getResourceId(String resource)
    {
        return (Integer) this.hashMap.get(resource);
    }

    public void addResource(String resource, Integer value)
    {
        if(DebugFactory.getInstance() != NoDebug.getInstance())
        {
            if(this.containsDuplicate(resource, value))
            {
                ForcedLogUtil.log("Found Duplicate Resource: " + resource, this);
            }
        }        

        this.hashMap.put(resource, value);        
    }

    private boolean containsDuplicate(String resource, Integer value)
    {
        Object[] objectArray = HashtableUtil.getInstance().getKeysAsArray(hashMap);
        
        for(int index = objectArray.length; --index >= 0;)
        {
            Integer integer = (Integer) this.hashMap.get(objectArray[index]);
            
            if(resource != objectArray[index])
            {
                if(value != integer)
                {
                    if(value.intValue() == integer.intValue())
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /*
    private Resources getResources()
    {
        return this.resources;
    }
    */

    public void setResources(Resources resources)
    {
        LogUtil.put(LogFactory.getInstance("Resource Loader: " + resources.getClass().getName(), this, "setResources"));

        this.resources = resources;
    }
    
    final String METHOD_NAME = "getResourceAsStream";
    final String GETTING = "Getting Resource: ";
    final String RESOURCE = "Resource Found";

    public InputStream getResourceAsStream(String resource) // , Object emulatorObject)
        throws Exception
    {
        LogUtil.put(LogFactory.getInstance(GETTING + resource, this, METHOD_NAME));

        // Try getting resource with normal resource access
        // AssetManager assetManager = resources.getAssets();
        // InputStream inputStream = assetManager.open(resourcePath);
        Integer integer = (Integer) this.hashMap.get(resource);
        int id = integer.intValue();
        InputStream inputStream = this.resources.openRawResource(id);

        if (inputStream != null)
        {
            LogUtil.put(LogFactory.getInstance(RESOURCE, this, METHOD_NAME));

            return inputStream;
        }

        return inputStream;
    }
}
