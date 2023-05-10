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
import org.allbinary.logic.basic.string.StringMaker;

public class ResourceUtil {

    private static ClassLoader classLoader;

    private static final ResourceUtil instance = new ResourceUtil();

    public static ResourceUtil getInstance() {
        return instance;
    }

    private ResourceUtil() {
    }

//    public ClassLoader getClassLoader() {
//        return ResourceUtil.classLoader;
//    }
    public void setClassLoader(ClassLoader classLoader) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("Resource Loader: ").append(classLoader.getClass().getName()).toString(), instance, "setClassLoader"));

        ResourceUtil.classLoader = classLoader;
    }

    public InputStream getResourceAsStream(String resource)
            //, Object emulatorObject)
            throws Exception {
        InputStream inputStream = this.getResourceAsStream(resource, 2);

        if (inputStream == null) {
            inputStream = this.getResourceAsStream(resource, 1);
            if (inputStream == null) {
                throw new Exception(new StringMaker().append("Unable to obtain: ").append(resource).toString());
            }
        }
        return inputStream;
    }

    private InputStream getResourceAsStream(String resource, int startIndex)
            //, Object emulatorObject)
            throws Exception {
        final StringMaker stringMaker = new StringMaker();

        int index = resource.indexOf(CommonSeps.getInstance().COLON);
        final String resourcePath = resource.substring(index + startIndex);

        final String METHOD_NAME = "getResourceAsStream";

        stringMaker.delete(0, stringMaker.length());
        LogUtil.put(LogFactory.getInstance(stringMaker.append("Getting Resource: ").append(resourcePath).toString(), this, METHOD_NAME));
        stringMaker.delete(0, stringMaker.length());
        LogUtil.put(LogFactory.getInstance(stringMaker.append("Start Index: ").append(startIndex).toString(), this, METHOD_NAME));

        //Try getting resource with normal resource access
        InputStream inputStream = resource.getClass().getResourceAsStream(resourcePath);

        if (inputStream != null) {
            LogUtil.put(LogFactory.getInstance("Resource Found", this, METHOD_NAME));
            return inputStream;
        }

        //Try getting resource with ClassLoader
        inputStream = ResourceUtil.classLoader.getResourceAsStream(resourcePath);

        if (inputStream != null) {
            stringMaker.delete(0, stringMaker.length());
            LogUtil.put(LogFactory.getInstance(stringMaker.append("Resource Found with: ").append(ResourceUtil.classLoader.getClass().getName()).toString(), this, METHOD_NAME));

            return inputStream;
        }

        inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);

        if (inputStream != null) {
            stringMaker.delete(0, stringMaker.length());
            LogUtil.put(LogFactory.getInstance(stringMaker.append("Resource Found with ContextClassLoader: ").append(Thread.currentThread().getContextClassLoader().getClass().getName()).toString(), this, METHOD_NAME));
            return inputStream;
        }

        //Thread.currentThread().getContextClassLoader().getResource(propertyFile);
        //Get using native inputStream
        /*
      inputStream = emulatorObject.getResourceAsStream(resourcePath);
      
      if(inputStream != null)
      {
         LogUtil.put(LogFactory.getInstance("Native Resource Found", this, METHOD_NAME));

         return inputStream;
      }
         */
        //Try getting the resource from the jar again using the custom Imp
        //G:/mnt/bc/mydev/working/j2me/Deployed/AllBinaryArcade/MiniSpaceWars/MIDP/PC/applet/1.0/
        //inputStream = JarResourceUtil.getResourceAsStream(new File("MiniSpaceWars.jar"), resourcePath);

        /*
      if(inputStream != null)
      {
         LogUtil.put(LogFactory.getInstance("Resource Found In Specified Jar", this, METHOD_NAME));

         return inputStream;
      }
      
      //Try getting the resource from the jar again using the custom Imp
      //inputStream = JarResourceUtil.getResourceAsStream(resourcePath);

      if(inputStream != null)
      {
         LogUtil.put(LogFactory.getInstance("Resource Found In Jar", this, METHOD_NAME));

         return inputStream;
      }

      //Try getting the resource from a normal file outside of the jar
      //inputStream = FileResourceUtil.getResourceAsStream(".").append(resourcePath);

      if(inputStream != null)
      {
         LogUtil.put(LogFactory.getInstance("Resource Found In File", this, METHOD_NAME));

         return inputStream;
      }

      //inputStream = FileResourceUtil.getResourceAsStream(resourcePath);

      if(inputStream != null)
      {
         LogUtil.put(LogFactory.getInstance("Resource Found In File - Absolute Path", this, METHOD_NAME));
      }
         */
        return inputStream;
    }
}
