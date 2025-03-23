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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;

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

    //private final String METHOD_NAME = "getResourceAsStream";
    //private final String GET_RESOURCE = "Getting Resource: ";

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
                    final StringMaker stringMaker = new StringMaker();
                    
                    final int index = resource.lastIndexOf('/');
                    String resourcePath = resource.substring(index + 1);
//                    LogUtil.put(LogFactory.getInstance(stringMaker.append(GET_RESOURCE).append(resourcePath).toString(), this, METHOD_NAME));
                    inputStream = resource.getClass().getResourceAsStream(resourcePath);

                    if (inputStream == null)
                    {

                        resourcePath = resource.substring(index);
//                        stringMaker.delete(0, stringMaker.length());
//                        LogUtil.put(LogFactory.getInstance(stringMaker.append(GET_RESOURCE).append(resourcePath).toString(), this, METHOD_NAME));
                        inputStream = resource.getClass().getResourceAsStream(resourcePath);

                        if (inputStream == null)
                        {

                            final String RES = "res";
                            resourcePath = stringMaker.append(RES).append(resource.substring(index)).toString();
//                            stringMaker.delete(0, stringMaker.length());
//                            LogUtil.put(LogFactory.getInstance(stringMaker.append(GET_RESOURCE).append(resourcePath).toString(), this, METHOD_NAME));
                            inputStream = resource.getClass().getResourceAsStream(resourcePath);

                            if (inputStream == null)
                            {

                                stringMaker.delete(0, stringMaker.length());
                                resourcePath = stringMaker.append("/").append(RES).append(resource.substring(index)).toString();
//                                stringMaker.delete(0, stringMaker.length());
//                                LogUtil.put(LogFactory.getInstance(stringMaker.append(GET_RESOURCE).append(resourcePath).toString(), this, METHOD_NAME));
                                inputStream = resource.getClass().getResourceAsStream(resourcePath);

                                final String COLON = CommonSeps.getInstance().COLON;

                                if (inputStream == null)
                                {

                                    stringMaker.delete(0, stringMaker.length());
                                    resourcePath = stringMaker.append(RES).append(COLON).append(resource.substring(index)).toString();
//                                    stringMaker.delete(0, stringMaker.length());
//                                    LogUtil.put(LogFactory.getInstance(stringMaker.append(GET_RESOURCE).append(resourcePath).toString(), this, METHOD_NAME));
                                    inputStream = resource.getClass().getResourceAsStream(resourcePath);

                                    if (inputStream == null)
                                    {

                                        stringMaker.delete(0, stringMaker.length());
                                        resourcePath = stringMaker.append(RES).append(COLON).append(resource.substring(index + 1)).toString();
//                                        stringMaker.delete(0, stringMaker.length());
//                                        LogUtil.put(LogFactory.getInstance(stringMaker.append(GET_RESOURCE).append(resourcePath).toString(), this, METHOD_NAME));
                                        inputStream = resource.getClass().getResourceAsStream(resourcePath);
                                        if (inputStream == null)
                                        {

                                            final String RESOURCE_STRING = "resource";
                                            stringMaker.delete(0, stringMaker.length());
                                            resourcePath = stringMaker.append(RESOURCE_STRING).append(COLON).append(resource.substring(index)).toString();
//                                            stringMaker.delete(0, stringMaker.length());
//                                            LogUtil.put(LogFactory.getInstance(stringMaker.append(GET_RESOURCE).append(resourcePath).toString(), this, METHOD_NAME));
                                            inputStream = resource.getClass().getResourceAsStream(resourcePath);

                                            if (inputStream == null)
                                            {
                                                stringMaker.delete(0, stringMaker.length());
                                                resourcePath = stringMaker.append(RESOURCE_STRING).append(COLON).append(resource.substring(index + 1)).toString();
//                                                stringMaker.delete(0, stringMaker.length());
//                                                LogUtil.put(LogFactory.getInstance(stringMaker.append(GET_RESOURCE).append(resourcePath).toString(), this, METHOD_NAME));
                                                inputStream = resource.getClass().getResourceAsStream(resourcePath);

                                                //inputStream = resource.getClass().getResourceAsStream("res:").append(resource);
                                                //inputStream = resource.getClass().getResourceAsStream("resource:").append(resource);
                                                //int index = resource.indexOf(':');
                                                //String resourcePath = resource.substring(index).append(1);
                                                //String resourcePath = "res:").append(resource.substring(index + 2);
                                                //int index = 0;
                                                //String resourcePath = resource.substring(index + 2);
                                                //String resourcePath = "res:").append(resource.substring(index + 2);
                                                //String resourcePath = resource;

                                                if (inputStream == null)
                                                {
                                                    stringMaker.delete(0, stringMaker.length());
                                                    throw new Exception(stringMaker.append("Unable to obtain: ").append(resource).toString());
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
        final StringMaker stringMaker = new StringMaker();
        
        int index = resource.indexOf(CommonSeps.getInstance().COLON);
        final String resourcePath = resource.substring(index + startIndex);

//        LogUtil.put(LogFactory.getInstance(stringMaker.append(GET_RESOURCE).append(resourcePath).toString(), this, METHOD_NAME));
//        stringMaker.delete(0, stringMaker.length());
//        LogUtil.put(LogFactory.getInstance(stringMaker.append("Start Index: ").append(startIndex).toString(), this, METHOD_NAME));

        //Try getting resource with normal resource access
        final InputStream inputStream = resource.getClass().getResourceAsStream(resourcePath);

        if (inputStream != null)
        {
//            LogUtil.put(LogFactory.getInstance("Resource Found", this, METHOD_NAME));
            return inputStream;
        }

        return inputStream;
    }
    
    public void addResource(final String resource, final Integer value)
    {
    }
    
}
