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
package org.allbinary.logic.system.loader;

import org.allbinary.logic.NullUtil;

public class WebappClassLoaderInfo
{      
   private static Object classLoader = NullUtil.getInstance().NULL_OBJECT;
   
   private WebappClassLoaderInfo()
   {
   }

   public static synchronized void setLoader(ClassLoader classLoader)
   {
      WebappClassLoaderInfo.classLoader = classLoader;
   }

   public static synchronized ClassLoader getLoader()
   {
      return (ClassLoader) classLoader;
   }
   
}
