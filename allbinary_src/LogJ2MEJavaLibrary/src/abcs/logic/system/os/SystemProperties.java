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
package abcs.logic.system.os;

public class SystemProperties
{
     private SystemProperties()
     {         
     }

     /*
     public static String getJavaVersion()
     {
        return System.getProperty("java.version");
     }
     
     public static String getFileSep()
     {
        return System.getProperty("file.separator");        
     }
     
     public static String getLineSep()
     {
         return System.getProperty("line.separator");
     }
     
     public static String getPathSep()
     {
         return System.getProperty("path.separator");
     }
     
     public static String getClassVersion()
     {
         return System.getProperty("java.class.version");
     }
     
     public static String getVendor()
     {         
         return System.getProperty("java.vendor");
     }
     
     public static String getVendorUrl()
     {
         return System.getProperty("java.vendor.url");
     }
     */
     
     public static String getName()
     {
         return System.getProperty("os.name");
     }
     
     public static String getArch()
     {
         return System.getProperty("os.arch");
     }
     
     public static String getVersion()
     {
         return System.getProperty("os.version");
     }
}