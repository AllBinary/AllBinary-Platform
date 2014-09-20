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
   private static ClassLoader classLoader;
   
   private static ResourceUtil STATIC = new ResourceUtil();
   
   private ResourceUtil()
   {
   }
  
   public static ResourceUtil getInstance()
   {
       return STATIC;
   }
   
   public static ClassLoader getClassLoader()
   {
      return ResourceUtil.classLoader;
   }

   public static void setClassLoader(ClassLoader classLoader)
   {
      LogUtil.put(LogFactory.getInstance("Resource Loader: " + classLoader.getClass().getName(), STATIC, "setClassLoader"));

      ResourceUtil.classLoader = classLoader;
   }
   
   public InputStream getResourceAsStream(String resource)
      //, Object emulatorObject)
      throws Exception
   {
      InputStream inputStream = this.getResourceAsStream(resource, 2);

      if(inputStream == null)
      {
         inputStream = this.getResourceAsStream(resource, 1);
         if(inputStream == null)
         {
            throw new Exception("Unable to obtain: " + resource);
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

      final String METHOD_NAME = "getResourceAsStream";
      
      LogUtil.put(LogFactory.getInstance("Getting Resource: " + resourcePath, this, METHOD_NAME));
      LogUtil.put(LogFactory.getInstance("Start Index: " + startIndex, this, METHOD_NAME));

      //Try getting resource with normal resource access
      InputStream inputStream = resource.getClass().getResourceAsStream(resourcePath);
      
      if(inputStream != null)
      {
         LogUtil.put(LogFactory.getInstance("Resource Found", this, METHOD_NAME));
         return inputStream;
      }

      //Try getting resource with ClassLoader
      inputStream = ResourceUtil.getClassLoader().getResourceAsStream(resourcePath);
      
      if(inputStream != null)
      {
         LogUtil.put(LogFactory.getInstance("Resource Found with: " + ResourceUtil.getClassLoader().getClass().getName(), this, METHOD_NAME));

         return inputStream;
      }

      inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);

      if(inputStream != null)
      {
         LogUtil.put(LogFactory.getInstance("Resource Found with: " + Thread.currentThread().getContextClassLoader().getClass().getName(), this, METHOD_NAME));
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
      //inputStream = FileResourceUtil.getResourceAsStream("." + resourcePath);

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
