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

import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import java.io.InputStream;

public class ResourceUtil
{

    private static ResourceUtil instance = new ResourceUtil();

    public static ResourceUtil getInstance()
    {
        return instance;
    }

    private ResourceUtil()
    {
    }

    private final String METHOD_NAME = "getResourceAsStream";
    private final String GET_RESOURCE = "Getting Resource: ";

    public InputStream getResourceAsStream(String resource)
            //, Object emulatorObject)
            throws Exception
    {
        InputStream inputStream = resource.getClass().getResourceAsStream(resource);

        if (inputStream == null)
        {
            inputStream = this.getResourceAsStream(resource, 2);

            if (inputStream == null)
            {
                inputStream = this.getResourceAsStream(resource, 1);
                if (inputStream == null)
                {

                    int index = resource.lastIndexOf('/');
                    String resourcePath = resource.substring(index + 1);
                    LogUtil.put(LogFactory.getInstance(GET_RESOURCE + resourcePath, this, METHOD_NAME));
                    inputStream = resource.getClass().getResourceAsStream(resourcePath);

                    if (inputStream == null)
                    {

                        resourcePath = resource.substring(index);
                        LogUtil.put(LogFactory.getInstance(GET_RESOURCE + resourcePath, this, METHOD_NAME));
                        inputStream = resource.getClass().getResourceAsStream(resourcePath);

                        if (inputStream == null)
                        {

                            final String RES = "res";
                            resourcePath = RES + resource.substring(index);
                            LogUtil.put(LogFactory.getInstance(GET_RESOURCE + resourcePath, this, METHOD_NAME));
                            inputStream = resource.getClass().getResourceAsStream(resourcePath);

                            if (inputStream == null)
                            {

                                resourcePath = "/" + RES + resource.substring(index);
                                LogUtil.put(LogFactory.getInstance(GET_RESOURCE + resourcePath, this, METHOD_NAME));
                                inputStream = resource.getClass().getResourceAsStream(resourcePath);

                                final String COLON = CommonSeps.getInstance().COLON;

                                if (inputStream == null)
                                {

                                    resourcePath = RES + COLON + resource.substring(index);
                                    LogUtil.put(LogFactory.getInstance(GET_RESOURCE + resourcePath, this, METHOD_NAME));
                                    inputStream = resource.getClass().getResourceAsStream(resourcePath);

                                    if (inputStream == null)
                                    {

                                        resourcePath = RES + COLON + resource.substring(index + 1);
                                        LogUtil.put(LogFactory.getInstance(GET_RESOURCE + resourcePath, this, METHOD_NAME));
                                        inputStream = resource.getClass().getResourceAsStream(resourcePath);
                                        if (inputStream == null)
                                        {

                                            final String RESOURCE_STRING = "resource";
                                            resourcePath = RESOURCE_STRING + COLON + resource.substring(index);
                                            LogUtil.put(LogFactory.getInstance(GET_RESOURCE + resourcePath, this, METHOD_NAME));
                                            inputStream = resource.getClass().getResourceAsStream(resourcePath);

                                            if (inputStream == null)
                                            {

                                                resourcePath = RESOURCE_STRING + COLON + resource.substring(index + 1);
                                                LogUtil.put(LogFactory.getInstance(GET_RESOURCE + resourcePath, this, METHOD_NAME));
                                                inputStream = resource.getClass().getResourceAsStream(resourcePath);

                                                //inputStream = resource.getClass().getResourceAsStream("res:" + resource);
                                                //inputStream = resource.getClass().getResourceAsStream("resource:" + resource);
                                                //int index = resource.indexOf(':');
                                                //String resourcePath = resource.substring(index + 1);
                                                //String resourcePath = "res:" + resource.substring(index + 2);
                                                //int index = 0;
                                                //String resourcePath = resource.substring(index + 2);
                                                //String resourcePath = "res:" + resource.substring(index + 2);
                                                //String resourcePath = resource;

                                                if (inputStream == null)
                                                {
                                                    throw new Exception("Unable to obtain: " + resource);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return inputStream;
    }

    private InputStream getResourceAsStream(String resource, int startIndex)
            //, Object emulatorObject)
            throws Exception
    {
        int index = resource.indexOf(CommonSeps.getInstance().COLON);
        String resourcePath = resource.substring(index + startIndex);

        LogUtil.put(LogFactory.getInstance(GET_RESOURCE + resourcePath, this, METHOD_NAME));
        LogUtil.put(LogFactory.getInstance("Start Index: " + startIndex, this, METHOD_NAME));

        //Try getting resource with normal resource access
        InputStream inputStream = resource.getClass().getResourceAsStream(resourcePath);

        if (inputStream != null)
        {
            LogUtil.put(LogFactory.getInstance("Resource Found", this, METHOD_NAME));
            return inputStream;
        }

        return inputStream;
    }
}
