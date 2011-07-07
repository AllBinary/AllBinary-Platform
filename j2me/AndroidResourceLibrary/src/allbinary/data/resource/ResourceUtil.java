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
package allbinary.data.resource;

import java.io.InputStream;
import java.util.HashMap;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import android.content.Context;
import android.content.res.Resources;

public class ResourceUtil
{
    private static ResourceUtil RESOURCES = new ResourceUtil();

    private Context context;
    private Resources resources;
    private HashMap hashMap = new HashMap();

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
        this.hashMap.put(resource, value);
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
