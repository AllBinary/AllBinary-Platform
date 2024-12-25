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

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import java.io.InputStream;
import java.util.Hashtable;

import org.allbinary.util.HashtableUtil;
import org.allbinary.debug.DebugFactory;
import org.allbinary.debug.NoDebug;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;

public class ResourceUtil
{
    private static final ResourceUtil instance = new ResourceUtil();

    public static ResourceUtil getInstance()
    {
        return instance;
    }
    
    private Activity activity;
    private Context context;
    private Resources resources;
    private Hashtable hashMap = new Hashtable();

    private ResourceUtil()
    {
    }
    
    public Context getContext()
    {
        return context;
    }

    /*
    public Activity getActivity()
    {
        return activity;
    }
    */
    
    public void setContext(Activity activity)
    {
        //this.activity = activity;
        this.context = activity;
    }
    
    public void setContext(Context aContext)
    {
        this.context = aContext;
    }
    
    public Integer getResourceId(String resource)
    {
        final Integer value = (Integer) this.hashMap.get(resource);
        
        if(DebugFactory.getInstance() != NoDebug.getInstance())
        {
            PreLogUtil.put(new StringMaker().append(resource).append(CommonSeps.getInstance().COLON).append(value).toString(), this, "getResourceId");
        }        
        
        return value;
    }

    public void addResource(String resource, Integer value)
    {
        if(DebugFactory.getInstance() != NoDebug.getInstance())
        {
            PreLogUtil.put(new StringMaker().append(resource).append(CommonSeps.getInstance().COLON).append(value).toString(), this, "addResource");
            if(this.containsDuplicate(resource, value))
            {
                ForcedLogUtil.log(new StringMaker().append("Found Duplicate Resource: ").append(resource).toString(), this);
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

    public void setResources(final Resources resources)
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("Resource Loader: ").append(resources.getClass().getName()).toString(), this, "setResources"));

        this.resources = resources;
    }
    
    //final String METHOD_NAME = "getResourceAsStream";
    //final String GETTING = "Getting Resource: ";
    //final String RESOURCE = "Resource Found";

    public InputStream getResourceAsStream(final String resource) // , Object emulatorObject)
        throws Exception
    {   
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append(GETTING).append(resource).toString(), this, METHOD_NAME));

        // Try getting resource with normal resource access
        // AssetManager assetManager = resources.getAssets();
        // InputStream inputStream = assetManager.open(resourcePath);
        final Integer integer = (Integer) this.hashMap.get(resource);
        int id = integer.intValue();
        final InputStream inputStream = this.resources.openRawResource(id);

        if (inputStream != null)
        {
            //LogUtil.put(LogFactory.getInstance(RESOURCE, this, METHOD_NAME));

            return inputStream;
        }

        return inputStream;
    }
}
