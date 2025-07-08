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

public class ResourceUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();


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
    public void setClassLoader(final ClassLoader classLoader) {
        logUtil.put(new StringMaker().append("Resource Loader: ").append(classLoader.getClass().getName()).toString(), this, "setClassLoader");

        ResourceUtil.classLoader = classLoader;
    }

    public InputStream getResourceAsStream(final String resource)
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

    //private final String RESOURCE_FOUND = "Resource Found: ";
    //private final String RESOURCE_FOUND_WITH = "Resource Found with: ";
    //private final String RESOURCE_FOUND_WITH_CONTEXT_CLASS_LOADER = "Resource Found with ContextClassLoader: ";
    //private final String METHOD_NAME = "getResourceAsStream";

    private InputStream getResourceAsStream(final String resource, final int startIndex)
            //, Object emulatorObject)
            throws Exception {
        final StringMaker stringMaker = new StringMaker();

        final CommonSeps commonSeps = CommonSeps.getInstance();
        final int index = resource.indexOf(commonSeps.COLON);
        final String resourcePath = resource.substring(index + startIndex);

        //stringMaker.delete(0, stringMaker.length());
        //logUtil.put(stringMaker.append("Getting Resource: ").append(resourcePath).toString(), this, METHOD_NAME);
        //stringMaker.delete(0, stringMaker.length());
        //logUtil.put(stringMaker.append("Start Index: ").append(startIndex).toString(), this, METHOD_NAME);

        //Try getting resource with normal resource access
        InputStream inputStream = resource.getClass().getResourceAsStream(resourcePath);

        if (inputStream != null) {
//            stringMaker.delete(0, stringMaker.length());
//            logUtil.put(stringMaker.append(RESOURCE_FOUND).append(resourcePath).toString(), this, METHOD_NAME);
            return inputStream;
        }

        //Try getting resource with ClassLoader
        inputStream = ResourceUtil.classLoader.getResourceAsStream(resourcePath);

        if (inputStream != null) {
//            stringMaker.delete(0, stringMaker.length());
//            logUtil.put(stringMaker.append(RESOURCE_FOUND_WITH).append(resourcePath).append(commonSeps.COMMA).append(ResourceUtil.classLoader.getClass().getName()).toString(), this, METHOD_NAME);

            return inputStream;
        }

        inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);

        if (inputStream != null) {
//            stringMaker.delete(0, stringMaker.length());
//            logUtil.put(stringMaker.append(RESOURCE_FOUND_WITH_CONTEXT_CLASS_LOADER).append(resourcePath).append(commonSeps.COMMA).append(Thread.currentThread().getContextClassLoader().getClass().getName()).toString(), this, METHOD_NAME);
            return inputStream;
        }

        //Thread.currentThread().getContextClassLoader().getResource(propertyFile);
        //Get using native inputStream
        /*
      inputStream = emulatorObject.getResourceAsStream(resourcePath);
      
      if(inputStream != null)
      {
         logUtil.put("Native Resource Found", this, METHOD_NAME);

         return inputStream;
      }
         */
        //Try getting the resource from the jar again using the custom Imp
        //G:/mnt/bc/mydev/working/j2me/Deployed/AllBinaryArcade/MiniSpaceWars/MIDP/PC/applet/1.0/
        //inputStream = JarResourceUtil.getResourceAsStream(new File("MiniSpaceWars.jar"), resourcePath);

        /*
      if(inputStream != null)
      {
         logUtil.put("Resource Found In Specified Jar", this, METHOD_NAME);

         return inputStream;
      }
      
      //Try getting the resource from the jar again using the custom Imp
      //inputStream = JarResourceUtil.getResourceAsStream(resourcePath);

      if(inputStream != null)
      {
         logUtil.put("Resource Found In Jar", this, METHOD_NAME);

         return inputStream;
      }

      //Try getting the resource from a normal file outside of the jar
      //inputStream = FileResourceUtil.getResourceAsStream(".").append(resourcePath);

      if(inputStream != null)
      {
         logUtil.put("Resource Found In File", this, METHOD_NAME);

         return inputStream;
      }

      //inputStream = FileResourceUtil.getResourceAsStream(resourcePath);

      if(inputStream != null)
      {
         logUtil.put("Resource Found In File - Absolute Path", this, METHOD_NAME);
      }
         */
        return inputStream;
    }
    
    public void addResource(final String resource, final Integer value)
    {
    }
    
}
